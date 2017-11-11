const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID
} = require('graphql');

const DeleteTransportOutput = new GraphQLObjectType({
  name: 'DeleteTransportOutput',
  fields: {
    deleteResult: {
      type: GraphQLString
    },
  }
});

const DeleteTransportInputType = new GraphQLInputObjectType({
  name: 'DeleteTransportInput',
  fields: {
    command: {
      type: new GraphQLNonNull(GraphQLString)
    },
    transportId: {
      type: new GraphQLNonNull(GraphQLString)
    },

  }
});

var corrId = require('../../../lib/randomnumber')();

module.exports = {
  type: DeleteTransportOutput,
  args: {
    input: {
      type: new GraphQLNonNull(DeleteTransportInputType)
    }
  },
  resolve(obj, { input }, { amqp }) {

    return{ deleteResult : name(amqp, input)}
  }
}

function name(amqp, input) {
  return new Promise(function (resolve, reject) {
    amqp.connect('amqp://localhost', function (err, conn) {
      conn.createChannel(function (err, ch) {
        ch.assertQueue('', { exclusive: true }, function (err, q) {
          var corr = corrId;

          ch.consume(q.queue, function (msg) {
            if (msg.properties.correlationId == corr) {
              console.log(' Response Got %s', msg.content.toString());
              
              resolve(msg.content.toString())
              setTimeout(function () {conn.close(); }, 500);

            }
          }, { noAck: true });

          ch.publish('logistic', 'logisticCommand', new Buffer(JSON.stringify(input)), {
            correlationId: corr,
            replyTo: q.queue
          });
        });
      });
    });
  })
}
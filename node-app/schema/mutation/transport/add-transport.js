const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID
} = require('graphql');


const AddTransportOutput = new GraphQLObjectType({
  name: 'AddTransportOutput',
  fields: {
    transportId: {
      type: GraphQLID
    },
  }
});

const AddTransportInputType = new GraphQLInputObjectType({
  name: 'AddTransportInput',
  fields: {
    command: {
      type: new GraphQLNonNull(GraphQLString)
    },   
    origin: {
      type: GraphQLString
    },
    destination: {
      type: GraphQLString
    },
    loadingDate: {
      type: GraphQLString
    },
    unloadingDate: {
      type: GraphQLString
    },
    transportType: {
      type: GraphQLString
    },
  }
});
var corrId = require('../../../lib/randomnumber')();

module.exports = {
  type: AddTransportOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddTransportInputType)
    }
  },
  resolve(obj, {input}, {amqp}) {

    return name(amqp, input)
  }
}

function name(amqp, input) {
  return new Promise(function (resolve, reject) {
    amqp.connect('amqp://localhost', function (err, conn) {
      conn.createChannel(function (err, ch) {
        ch.assertQueue('', {
          exclusive: true
        }, function (err, q) {
          var corr = corrId;

          ch.consume(q.queue, function (msg) {
            if (msg.properties.correlationId == corr) {
              console.log(' Response from Logistic  Got %s', msg.content.toString());
              var objectMap = {
                transportId: msg.content.toString()
              }
              resolve(objectMap)
              setTimeout(function () {
                conn.close();
              }, 500);

            }
          }, {
            noAck: true
          });
          ch.publish('logistic', 'logisticCommand', new Buffer(JSON.stringify(input)), {
            correlationId: corr,
            replyTo: q.queue
          });
        });
      });
    });


  })
}
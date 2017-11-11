const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID
} = require('graphql');


const AddTradeOutput = new GraphQLObjectType({
  name: 'AddTradeOutput',
  fields: {
    tradeId: {
      type: GraphQLID
    },
  }
});

const AddTradeInputType = new GraphQLInputObjectType({
  name: 'AddTradeInput',
  fields: {

    command: {
      type: new GraphQLNonNull(GraphQLString)
    },   
    tradeId: {
      type: GraphQLString
    },
    side: {
      type: GraphQLString
    },
    quantity: {
      type: GraphQLString
    },
    price: {
      type: GraphQLString
    },
    tradeDate: {
      type: GraphQLString
    },
    tradeStatus: {
      type: GraphQLString
    },
    counterParty: {
      type: GraphQLString
    },
    commodity: {
      type: GraphQLString
    },
    location: {
      type: GraphQLString
    }


  }
});
var corrId = require('../../../lib/randomnumber')();

module.exports = {
  type: AddTradeOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddTradeInputType)
    }
  },
  resolve(obj, {
    input
  }, {
    amqp
  }) {

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
              console.log(' Response Got %s', msg.content.toString());
              var t = {
                tradeId: msg.content.toString()
              }
              resolve(t)
              setTimeout(function () {
                conn.close();
              }, 500);

            }
          }, {
            noAck: true
          });
          ch.publish('trade', 'tradeCommand', new Buffer(JSON.stringify(input)), {
            correlationId: corr,
            replyTo: q.queue
          });
        });
      });
    });


  })
}
const {
    GraphQLInputObjectType,
    GraphQLString,
    GraphQLNonNull,
    GraphQLObjectType,
    GraphQLID
} = require('graphql');
const UpdateTradeOutput = require('../../types/trade-object');


const UpdateTradeInputType = new GraphQLInputObjectType({
    name: 'UpdateTradeInput',
    fields: {

        command: {
            type: new GraphQLNonNull(GraphQLString)
        },
        tradeId: {
            type: new GraphQLNonNull(GraphQLString)
        },
        side: {
            type: new GraphQLNonNull(GraphQLString)
        },
        quantity: {
            type: new GraphQLNonNull(GraphQLString)
        },
        price: {
            type: new GraphQLNonNull(GraphQLString)
        },
        tradeDate: {
            type: new GraphQLNonNull(GraphQLString)
        },
        tradeStatus: {
            type: new GraphQLNonNull(GraphQLString)
        },
        counterParty: {
            type: new GraphQLNonNull(GraphQLString)
        },
        commodity: {
            type: new GraphQLNonNull(GraphQLString)
        },
        location: {
            type: new GraphQLNonNull(GraphQLString)
        },

    }
});

var corrId = require('../../../lib/randomnumber')();

module.exports = {
    type: UpdateTradeOutput,
    args: {
        input: {
            type: new GraphQLNonNull(UpdateTradeInputType)
        }
    },
    resolve(obj, { input }, { amqp }) {
        
            return name(amqp, input)}
          
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
                      
                      resolve(JSON.parse(msg.content.toString()))
                      setTimeout(function () {conn.close(); }, 500);
        
                    }
                  }, { noAck: true });
        
                  ch.publish('trade', 'tradeCommand', new Buffer(JSON.stringify(input)), {
                    correlationId: corr,
                    replyTo: q.queue
                  });
                });
              });
            });
          })


}
const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID,
  GraphQLList
} = require('graphql');
const TradeObject = require('../trade-object');



const SearchTradeInputType = new GraphQLInputObjectType({
  name: 'SearchTradeInput',
  fields: {

    command: {
      type: new  GraphQLNonNull(GraphQLString)
    },
    buySide: {
      type: GraphQLString
    },
    sellSide: {
      type: GraphQLString
    },     
    fromtradeDate: {
      type: GraphQLString
    },   
    totradeDate: {
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
    },

  }
});
var corrId = require('../../../lib/randomnumber')();

module.exports = {
  type: new GraphQLList(TradeObject),
  args: {
    input: {
      type: new GraphQLNonNull(SearchTradeInputType)
    }
  },
   
  resolve(obj, { input}, { amqp }) {

    return name(amqp, input)
  }
}

function name(amqp, input) {
  return new Promise(function (resolve, reject) {

    console.log('input')
    amqp.connect('amqp://localhost', function (err, conn) {
      conn.createChannel(function (err, ch) {
        ch.assertQueue('', {
          exclusive: true
        }, function (err, q) {
          var corr = corrId;

          ch.consume(q.queue, function (msg) {
            if (msg.properties.correlationId == corr) {
              console.log(' Response Got %s', msg.content.toString());
              resolve(JSON.parse(msg.content.toString()))
              setTimeout(function () {
                conn.close();
              }, 500);

            }
          }, {
            noAck: true
          });
          ch.publish('trade', 'tradeReqRes', new Buffer(JSON.stringify(input)), {
            correlationId: corr,
            replyTo: q.queue
          });
        });
      });
    });


  })
}
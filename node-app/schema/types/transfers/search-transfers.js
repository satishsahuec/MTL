const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID,
  GraphQLList
} = require('graphql');
const TradeObject = require('../trade-object');
const TransportObject = require('../transport-object');
const messagePubSub = require('../../../message-broker/pub-sub');
const tradeMessageConfig = require('../../../config/trade-message-config');
const transportMessageConfig = require('../../../config/transport-message-config');

const SearchTransfersInputType = new GraphQLInputObjectType({
  name: 'SearchTransfersInputType',
  fields: {
     
    fromTradeDate: {
      type: GraphQLString
    },   
    toTradeDate: {
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


const SearchTransfersOutput = new GraphQLObjectType({
  name: 'SearchTransfersOutput',
  fields: {   
    purchases: {
     type: new GraphQLList(TradeObject),  
    },
    sales: {
    type: new GraphQLList(TradeObject),   
    },
    transports: {
      type: new GraphQLList(TransportObject),   
      } 

  }
});

module.exports = {
  type: SearchTransfersOutput,
  args: {
    input: {
      type: new GraphQLNonNull(SearchTransfersInputType)
    }
  }, 
   resolve(obj, { input}, { amqp }) {
      return{
      purchases : messagePubSub(amqp, input , tradeMessageConfig.exchangeName, tradeMessageConfig.reqresQueueBinding),
      sales :  messagePubSub(amqp, input , tradeMessageConfig.exchangeName, tradeMessageConfig.reqresQueueBinding),
      transports : messagePubSub(amqp, input , transportMessageConfig.exchangeName, transportMessageConfig.reqresQueueBinding)
    }
}
}
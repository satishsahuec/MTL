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
    fromTradeDate: {
      type: GraphQLString
    },   
    toTradeDate: {
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
const messagePubSub = require('../../../message-broker/pub-sub');
const tradeMessageConfig = require('../../../config/trade-message-config');
module.exports = {
  type: new GraphQLList(TradeObject),
  args: {
    input: {
      type: new GraphQLNonNull(SearchTradeInputType)
    }
  },
   
  resolve(obj, { input}, { amqp }) {

    return messagePubSub(amqp, input , tradeMessageConfig.exchangeName, tradeMessageConfig.reqresQueueBinding)
  }
}


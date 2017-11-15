'use strict'
const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID,
  GraphQLFloat
} = require('graphql');

const AddTradeInputType = new GraphQLInputObjectType({
  name: 'AddTradeInput',
  fields: {

    command: {
      type: new GraphQLNonNull(GraphQLString)
    },

    side: {
      type: GraphQLString,
      description: 'SELL or BUY '
    },
    quantity: {
      type: GraphQLFloat
    },
    price: {
      type: GraphQLFloat
    },
    tradeDate: {
      type: GraphQLString,
      description: 'Date must be in formate yyyy-mm-dd'
    },
    tradeStatus: {
      type: GraphQLString,
      description: ' OPEN '
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


const AddTradeOutput = new GraphQLObjectType({
  name: 'AddTradeOutput',
  fields: {
    tradeId: {
      type: GraphQLID
    },
  }
});


const messagePubSub = require('../../../message-broker/pub-sub');
const tradeMessageConfig = require('../../../config/trade-message-config');

module.exports = {
  type: AddTradeOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddTradeInputType)
    }
  },
  resolve(obj, {input}, { amqp }) {

    return {
      tradeId: messagePubSub(amqp, input, tradeMessageConfig.exchangeName, tradeMessageConfig.commandQueueBinding)
    }
  }
}
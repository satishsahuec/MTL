const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID
} = require('graphql');

const DeleteTradeOutput = new GraphQLObjectType({
  name: 'DeleteTradeOutput',
  fields: {
    deleteResult: {
      type: GraphQLString
    },
  }
});

const DeleteTradeInputType = new GraphQLInputObjectType({
  name: 'DeleteTradeInput',
  fields: {
    command: {
      type: new GraphQLNonNull(GraphQLString)
    },
    tradeId: {
      type: new GraphQLNonNull(GraphQLID)
    },

  }
});

const messagePubSub = require('../../../message-broker/pub-sub');
const tradeMessageConfig = require('../../../config/trade-message-config');
module.exports = {
  type: DeleteTradeOutput,
  args: {
    input: {
      type: new GraphQLNonNull(DeleteTradeInputType)
    }
  },
  resolve(obj, { input }, { amqp }) {

    return{ deleteResult : (amqp, input ,tradeMessageConfig.exchangeName, tradeMessageConfig.commandQueueBinding)}
  }
}


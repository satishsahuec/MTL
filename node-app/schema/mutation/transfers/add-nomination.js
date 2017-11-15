'use strict'
const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID,
  GraphQLFloat
} = require('graphql');

const AddNomination = new GraphQLInputObjectType({
  name: 'AddNomination',
  fields: {

    command: {
      type: new GraphQLNonNull(GraphQLString)
    },
    buyTradeId: {
      type: new GraphQLNonNull(GraphQLString)
          },
    sellTradeId: {
      type: new GraphQLNonNull(GraphQLString)
    },
    transportId: {
      type: new GraphQLNonNull(GraphQLString)
    },
    
  }
});


const NominationOutput = new GraphQLObjectType({
  name: 'NominationOutput',
  fields: {
    nominationStatus: {
      type: GraphQLString
    },
  }
});

const messagePubSub = require('../../../message-broker/pub-sub');
const transportMessageConfig = require('../../../config/transport-message-config');

module.exports = {
  type: NominationOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddNomination)
    }
  },
  resolve(obj, {input}, { amqp }) {

    return {
      nominationStatus: messagePubSub(amqp, input, transportMessageConfig.exchangeName, transportMessageConfig.commandQueueBinding)
    }
  }
}
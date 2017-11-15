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
      type: new GraphQLNonNull(GraphQLID)
    },

  }
});

const messagePubSub = require('../../../message-broker/pub-sub');
const transportMessageConfig = require('../../../config/transport-message-config');

module.exports = {
  type: DeleteTransportOutput,
  args: {
    input: {
      type: new GraphQLNonNull(DeleteTransportInputType)
    }
  },
  resolve(obj, { input }, { amqp }) {

    return{ deleteResult : messagePubSub(amqp, input, transportMessageConfig.exchangeName ,transportMessageConfig.commandQueueBinding)}
  }
}


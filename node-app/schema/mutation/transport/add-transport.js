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
      type: new GraphQLNonNull(GraphQLString)
    },
    destination: {
      type: new GraphQLNonNull(GraphQLString)
    },
    loadingDate: {
      type: new GraphQLNonNull(GraphQLString)
    },
    unloadingDate: {
      type: new GraphQLNonNull(GraphQLString)
    },
    transportType: {
      type: new GraphQLNonNull(GraphQLString)
    },
  }
});
const messagePubSub = require('../../../message-broker/pub-sub');
const transportMessageConfig = require('../../../config/transport-message-config');

module.exports = {
  type: AddTransportOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddTransportInputType)
    }
  },
  resolve(obj, {input}, {amqp}) {
    console.log(transportMessageConfig.exchangeName)
    console.log(transportMessageConfig.commandQueueBinding)
    return {transportId : messagePubSub(amqp, input, transportMessageConfig.exchangeName ,transportMessageConfig.commandQueueBinding)}
  }
}


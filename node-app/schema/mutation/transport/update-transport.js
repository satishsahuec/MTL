const {
    GraphQLInputObjectType,
    GraphQLString,
    GraphQLNonNull,
    GraphQLObjectType,
    GraphQLID
} = require('graphql');
const UpdateTransportOutput = require('../../types/transport-object');


const UpdateTransportInputType = new GraphQLInputObjectType({
    name: 'UpdateTransportInput',
    fields: {

        command: {
            type: new GraphQLNonNull(GraphQLString)
        },
        transportId: {
            type: new GraphQLNonNull(GraphQLID)
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
        }

    }
});

const messagePubSub = require('../../../message-broker/pub-sub');
const transportMessageConfig = require('../../../config/transport-message-config');
module.exports = {
    type: UpdateTransportOutput,
    args: {
        input: {
            type: new GraphQLNonNull(UpdateTransportInputType)
        }
    },
    resolve(obj, { input }, { amqp }) {        
            return messagePubSub(amqp, input, transportMessageConfig.exchangeName ,transportMessageConfig.commandQueueBinding)}
          
        }
        
        
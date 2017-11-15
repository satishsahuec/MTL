const {
    GraphQLInputObjectType,
    GraphQLString,
    GraphQLNonNull,
    GraphQLObjectType,
    GraphQLID,
    GraphQLFloat
} = require('graphql');
const UpdateTradeOutput = require('../../types/trade-object');


const UpdateTradeInputType = new GraphQLInputObjectType({
    name: 'UpdateTradeInput',
    fields: {

        command: {
            type: new GraphQLNonNull(GraphQLString)
        },
        tradeId: {
            type: new GraphQLNonNull(GraphQLID)
        },
        side: {
            type: new GraphQLNonNull(GraphQLString),
            description : 'SELL or BUY '
        },
        quantity: {
            type: new GraphQLNonNull(GraphQLFloat)
        },
        price: {
            type: new GraphQLNonNull(GraphQLFloat)
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
const messagePubSub = require('../../../message-broker/pub-sub');
const tradeMessageConfig = require('../../../config/trade-message-config');
module.exports = {
    type: UpdateTradeOutput,
    args: {
        input: {
            type: new GraphQLNonNull(UpdateTradeInputType)
        }
    },
    resolve(obj, { input }, { amqp }) {
        
            return messagePubSub(amqp, input ,tradeMessageConfig.exchangeName, tradeMessageConfig.commandQueueBinding) } 
          
        }
        
const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID,
  GraphQLList
} = require('graphql');
const TransportObject = require('../transport-object');



const SearchTransportInputType = new GraphQLInputObjectType({
  name: 'SearchTransportInput',
  description : 'Date must be in yyyy-mm-dd formate',
  fields: {
    
    command: {
      type: new  GraphQLNonNull(GraphQLString)
    },
    origin: {
      type: GraphQLString
    },
    destination: {
      type: GraphQLString
    },     
    fromLoadingDate: {
      type: GraphQLString
    },   
    toLoadingDate: {
      type: GraphQLString
    }, 
    fromUnloadingDate: {
      type: GraphQLString
    },
    toUnloadingDate: {
      type: GraphQLString
    },
    transportType: {
      type: GraphQLString
    },

  }
});
const messagePubSub = require('../../../message-broker/pub-sub');
const transportMessageConfig = require('../../../config/transport-message-config');

module.exports = {
  type: new GraphQLList(TransportObject),
  args: {
    input: {
      type: new GraphQLNonNull(SearchTransportInputType)
    }
  },
   
  resolve(obj, { input}, { amqp }) {

    return messagePubSub(amqp, input ,transportMessageConfig.exchangeName, transportMessageConfig.reqresQueueBinding)
  }
}

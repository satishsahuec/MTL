const {
  GraphQLObjectType,
  GraphQLNonNull,
  GraphQLString,
  GraphQLID
} = require('graphql');


module.exports = new GraphQLObjectType({
  name: 'TransportObject',
  fields: () => {

    return {
      transportId: {
        type: GraphQLString
      },
      origin: {
        type: GraphQLString
      },
      destination: {
        type: GraphQLString
      },
      loadingDate: {
        type: GraphQLString
      },
      unloadingDate: {
        type: GraphQLString
      },
      transportType: {
        type: GraphQLString
      }
    };
  }
});
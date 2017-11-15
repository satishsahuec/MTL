const {
  GraphQLObjectType,
  GraphQLNonNull,
  GraphQLString,
  GraphQLID,
  GraphQLFloat
} = require('graphql');


module.exports = new GraphQLObjectType({
  name: 'TradeObject',
  fields: () => {

    return {
      tradeId: {
        type: GraphQLString
      },
      side: {
        type: GraphQLString
      },
      quantity: {
        type: GraphQLFloat
      },
      price: {
        type: GraphQLFloat
      },
      tradeDate: {
        type: GraphQLString
      },
      tradeStatus: {
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
      }


    };
  }
});
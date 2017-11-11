const {
  GraphQLObjectType,
  GraphQLNonNull,
  GraphQLString,
  GraphQLID
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
        type: GraphQLString
      },
      price: {
        type: GraphQLString
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
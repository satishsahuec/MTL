// Import type helpers from graphql-js
const {
  GraphQLSchema,
  GraphQLObjectType,
  GraphQLString,
  GraphQLNonNull,

} = require('graphql');

const RootQueryType = new GraphQLObjectType({
  name: 'RootQuery', //name exposed in graphiql

  fields: () => ({
    SearchTrade: SearchTradeQuery
  })
});

//Trade 
const AddTradeMutation = require('./mutation/trade/add-trade');
const DeleteTradeMutation = require('./mutation/trade/delete-trade');
const UpdateTradeMutation = require('./mutation/trade/update-trade');
const SearchTradeQuery = require('./types/trade/search-trade');

//Transport
const AddTransportMutation = require('./mutation/transport/add-transport');
const DeleteTransportMutation = require('./mutation/transport/delete-transport');
const UpdateTransportMutation = require('./mutation/transport/update-transport');
const RootMutationType = new GraphQLObjectType({
  name: 'RootMutationType',
  fields: () => ({
    AddTrade: AddTradeMutation,
    UpdateTrade: UpdateTradeMutation,
    DeleteTrade: DeleteTradeMutation,

    AddTransport: AddTransportMutation,
    UpdateTransport: UpdateTransportMutation,
    DeleteTransport: DeleteTransportMutation,

    })
})
const ncSchema = new GraphQLSchema({
  query: RootQueryType,
  // mutation: ...
  mutation: RootMutationType
});

module.exports = ncSchema;
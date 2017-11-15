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
    SearchTrade: SearchTradeQuery,
    SearchTransport: SearchTransportQuery,
    SearchTransfers : SearchTradetransportQuery,

  })
});
//Transfers
const SearchTradetransportQuery = require('./types/transfers/search-transfers');
const AddNominationMutation = require('./mutation/transfers/add-nomination');

//Trade 
const AddTradeMutation = require('./mutation/trade/add-trade');
const DeleteTradeMutation = require('./mutation/trade/delete-trade');
const UpdateTradeMutation = require('./mutation/trade/update-trade');
const SearchTradeQuery = require('./types/trade/search-trade');

//Transport
const AddTransportMutation = require('./mutation/transport/add-transport');
const DeleteTransportMutation = require('./mutation/transport/delete-transport');
const UpdateTransportMutation = require('./mutation/transport/update-transport');
const SearchTransportQuery = require('./types/transport/search-transport');
const RootMutationType = new GraphQLObjectType({
  name: 'RootMutationType',
  fields: () => ({
    //trade mutation
    AddTrade: AddTradeMutation,
    UpdateTrade: UpdateTradeMutation,
    DeleteTrade: DeleteTradeMutation,

    // Transport mutation
    AddTransport: AddTransportMutation,
    UpdateTransport: UpdateTransportMutation,
    DeleteTransport: DeleteTransportMutation,
    //transfer mutation
    AddNomination: AddNominationMutation,

    })
})
const ncSchema = new GraphQLSchema({
  query: RootQueryType,
  // mutation: ...
  mutation: RootMutationType
});

module.exports = ncSchema;
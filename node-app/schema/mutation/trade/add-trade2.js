const {
  GraphQLInputObjectType,
  GraphQLString,
  GraphQLNonNull,
  GraphQLObjectType,
  GraphQLID
} = require('graphql');


const AddTradeOutput = new GraphQLObjectType({
  name: 'AddTradeOutput',
  fields: {
    tradeId: {
      type: GraphQLID
    },
  }
});

const AddTradeInputType = new GraphQLInputObjectType({
  name: 'AddTradeInput',
  fields: {

    command: {
      type: new GraphQLNonNull(GraphQLString)
    },
    side: {
      type: new GraphQLNonNull(GraphQLString)
    },
    quantity: {
      type: new GraphQLNonNull(GraphQLString)
    },
    price: {
      type: new GraphQLNonNull(GraphQLString)
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
var responseValue = {
  id: '-1'
};
var corrId = require('../../../lib/randomnumber')();

module.exports = {
  type: AddTradeOutput,
  args: {
    input: {
      type: new GraphQLNonNull(AddTradeInputType)
    }
  },
  resolve(obj, {input}, {Connection
  }) { 
  async function main() {
    var quote = await name(Connection, input);
    console.log('1------ '+quote);
   return quote;
  //  return {
  //    tradeId : 123
  //  }
  } 
    // return {
    //  // tradeId: out.then(result => result.id)
    //    tradeId : main()
    // }

    return main()
  }
}
 function name(Connection, input) {
return new Promise(function (resolve,reject) { 
   
   Connection.then(function (conn) {    
    return conn.createChannel();
  }).then(function (ch) {
    ch.publish('trade', 'tradeCommand', new Buffer(JSON.stringify(input)), {
      correlationId: corrId, replyTo: 'reply_trade' });

    ch.consume('reply_trade', function (msg) {
      if (msg.properties.correlationId == corrId) {
        console.log(' Response  Got %s', msg.content.toString());
        responseValue = {tradeId: msg.content.toString() };       
        resolve(responseValue);  
      }
       })
  })   
   
  
  })
}


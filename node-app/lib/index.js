const {
  nodeEnv
} = require('./util');
console.log(`Running in ${nodeEnv} mode...`);
const app = require('express')();
var amqp = require('amqplib/callback_api');
//const Connection = require('amqplib').connect('amqp://localhost');

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
  console.log('server running on PORT ' + PORT);
  console.log('Hit the URL at  http://localhost:4000/metallica');
});

const ncSchema = require('../schema');

const graphqlHTTP = require('express-graphql');

app.use('/metallica', graphqlHTTP({
  schema: ncSchema,
  graphiql: true,
  context: {
    amqp
  }
}))
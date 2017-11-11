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
            type: new GraphQLNonNull(GraphQLString)
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

var corrId = require('../../../lib/randomnumber')();

module.exports = {
    type: UpdateTransportOutput,
    args: {
        input: {
            type: new GraphQLNonNull(UpdateTransportInputType)
        }
    },
    resolve(obj, { input }, { amqp }) {        
            return name(amqp, input)}
          
        }
        
        function name(amqp, input) {
          return new Promise(function (resolve, reject) {
            amqp.connect('amqp://localhost', function (err, conn) {
              conn.createChannel(function (err, ch) {
                ch.assertQueue('', { exclusive: true }, function (err, q) {
                  var corr = corrId;
        
                  ch.consume(q.queue, function (msg) {
                    if (msg.properties.correlationId == corr) {
                      console.log(' Response Got %s', msg.content.toString());
                      
                      resolve(JSON.parse(msg.content.toString()))
                      setTimeout(function () {conn.close(); }, 500);
        
                    }
                  }, { noAck: true });
        
                  ch.publish('logistic', 'logisticCommand', new Buffer(JSON.stringify(input)), {
                    correlationId: corr,
                    replyTo: q.queue
                  });
                });
              });
            });
          })


}
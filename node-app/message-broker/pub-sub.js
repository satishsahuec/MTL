const corrId = require('../lib/randomnumber')();
module.exports = function messagePubSub(amqp, input, exchange, bindingKey) {
  return new Promise(function (resolve, reject) {

    console.log('input data = ' + JSON.stringify(input))
    amqp.connect('amqp://localhost', function (err, conn) {
      conn.createChannel(function (err, ch) {
        ch.assertQueue('', {
          exclusive: true
        }, function (err, q) {
          var corr = corrId;

          ch.consume(q.queue, function (msg) {
            if (msg.properties.correlationId == corr) {
              console.log(' Response Got %s', msg.content.toString());

              resolve(msg.content.toString())
              setTimeout(function () {
                conn.close();
              }, 500);

            }
          }, {
            noAck: true
          });
          ch.publish(exchange, bindingKey, new Buffer(JSON.stringify(input)), {
            correlationId: corr,
            replyTo: q.queue
          });
        });
      });
    });


  })
}
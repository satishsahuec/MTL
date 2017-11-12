# sample Project to integrate graphqp amqplib mongodb rabbitmq springboot 

#   for windows only 


#copy and paste below command in terminal inside git repo 

cd trade
mvn clean
mvn package
call START /B "service1" java -jar target\trade-0.0.1-SNAPSHOT.jar

cd ../refdata
mvn clean
mvn package
call START /B "service2" java -jar target\refdata-0.0.1-SNAPSHOT.jar

cd ../logistics
mvn clean
mvn package
call START /B "service3" java -jar target\logistics-0.0.1-SNAPSHOT.jar


cd ../node-app
npm install
call START /B "service4" node lib\index.js



#All services is up and running now 
#fire request from browser using graphql
#http://localhost:4000/metallica

#follow graphql.txt for mutation and query type input 



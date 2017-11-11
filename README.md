# MTL

for windows 

cd trade
mvn clean
mvn package
call START /B "m1" java -jar target\trade-0.0.1-SNAPSHOT.jar

cd ../refdata
mvn clean
mvn package
call START /B "m2" java -jar target\refdata-0.0.1-SNAPSHOT.jar

cd ../logistics
mvn clean
mvn package
call START /B "m3" java -jar target\logistics-0.0.1-SNAPSHOT.jar


cd ../node-app
npm install
call START /B "m3" node lib\index.js

rm -rf bin/*.class

javac -d bin src/*.java

java -cp bin Number  

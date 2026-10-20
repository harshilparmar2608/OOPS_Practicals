# MiniBank

Compile:

javac -d out $(find src -name "*.java")

Run:

java -cp out MiniBank

Create the runnable JAR:

jar cfe minibank.jar MiniBank -C out .

Run:

java -jar minibank.jar

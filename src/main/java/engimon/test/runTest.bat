ECHO "Test Engimon"
cd ../../../..
javac -d ../bin main/java/engimon/test/Test_Engimon.java
java -cp "../bin" main.java.engimon.test.Test_Engimon 
PAUSE
ECHO "Test Breeding"
javac -d ../bin main/java/engimon/test/Test_Breeding.java
java -cp "../bin" main.java.engimon.test.Test_Breeding 
PAUSE
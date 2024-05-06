cd src/main
javac Main.java -classpath ../classes/ -d ../../build/

cd ../../build
time java Main $1 ../res/$2

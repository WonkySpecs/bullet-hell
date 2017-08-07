find -name "*.java" > sources.txt
javac -d "%cd%\bin" @sources.txt
java -cp "%cd%\bin" src.GameWindow

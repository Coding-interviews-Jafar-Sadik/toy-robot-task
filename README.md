# WIP

https://sdkman.io
sdk install java 11.0.5-open

Github CI pipeline runs tests after every commit

The project uses Lombok to reduce boilerplate code. Please install Lombok plugin for your favourite IDE and enable 'annotation processing' option.

Notes:  
- Currently ToyRobot class implements state pattern to simplify branching logic and avoid checking boolean flag every time. 
Alternatively state update could be executed directly in command.execute() method. This would allow to test commands independently, 
implement undo functionality but comes with a greater code complexity which, I believe, is an overkill for this task. 
- the code has been checked with Sonar Lint and therefore some false warnings were suppressed with @SuppressWarnings
- the application will fail in an absence of a valid file name
- in order to save some time I decided not to test `org.toyrobot.runtime.SystemRuntime`

This is a solution to 'Toy Robot' task: 
https://zone.github.io/backend/toy-robot

Build & run
```bash
./gradlew build
java -jar build/libs/toy-robot-1.0-SNAPSHOT.jar scripts/script1.txt
java -jar build/libs/toy-robot-1.0-SNAPSHOT.jar scripts/script2.txt
java -jar build/libs/toy-robot-1.0-SNAPSHOT.jar scripts/script3.txt
java -jar build/libs/toy-robot-1.0-SNAPSHOT.jar scripts/script4.txt
java -jar build/libs/toy-robot-1.0-SNAPSHOT.jar scripts/script5.txt
```


Requirements: 
jdk11+
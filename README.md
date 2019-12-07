## Toy Robot Task  

The solution to [Toy Robot Task](https://zone.github.io/backend/toy-robot) is implemented in `java 11` and built
with `gradle 5.4`. It uses `lombok` to reduce boilerplate code and some standard testing libraries including
`junit` and `assertj`. [Github CI](https://github.com/JafarSadik/toy-robot-task/commits/master) pipeline is configured
to execute tests after every commit.  The required `jdk11+`, can be easily installed with [SDKMan!](https://sdkman.io/install) 
on linux or mac: `sdk install java 11.0.5-open`

### Build & run the project
Execute the following command on mac or linux: 
```bash
./gradlew build
```
It's very similar for windows:
```bash
gradlew.bat build
```
The application comes with 5 scripts containing toy robot commands. Run them as follows: 
```bash
java -jar build/libs/toy-robot-1.0.jar scripts/script1.txt
java -jar build/libs/toy-robot-1.0.jar scripts/script2.txt
java -jar build/libs/toy-robot-1.0.jar scripts/script3.txt
java -jar build/libs/toy-robot-1.0.jar scripts/script4.txt
java -jar build/libs/toy-robot-1.0.jar scripts/script5.txt
```

### Notes     
- the code has been checked with Sonar Lint and therefore some false warnings were suppressed with `@SuppressWarnings`
- in order to save some time I decided not to test `org.toyrobot.runtime.SystemRuntime`
- the application will fail in an absence of a valid file name 

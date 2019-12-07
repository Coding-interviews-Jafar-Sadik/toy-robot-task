# WIP

https://sdkman.io
sdk install java 11.0.5-open

Github CI pipeline runs tests after every commit

The project uses Lombok to reduce boilerplate code. Please install Lombok plugin for your favourite IDE and enable 'annotation processing' option.

Notes:
- ToyRobot could implement a state pattern to simplify branching logic and avoid checking 'robotOnTable' flag every time.  
- alternatively state update could be executed directly in command.execute() method. This would allow to test commands independently, 
implement undo functionality but comes with a greater code complexity which is an overkill for this task. 
- the code has been checked with Sonar Lint and therefore some false warnings were suppressed with @SuppressWarnings

This is a solution to 'Toy Robot' task: 
https://zone.github.io/backend/toy-robot
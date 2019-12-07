package org.toyrobot;

import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.commands.CommandParser;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;
import org.toyrobot.runtime.DefaultRuntime;
import org.toyrobot.runtime.Runtime;

import static org.toyrobot.commands.CommandType.*;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobot {
    private final CommandParser commandParser = new CommandParser();
    private final Runtime runtime;

    private boolean robotOnTable = false;
    private Point2D position = point2d(0, 0);
    private Direction direction = Direction.UNKNOWN;

    public ToyRobot() {
        this(new DefaultRuntime());
    }

    public ToyRobot(Runtime runtime) {
        this.runtime = runtime;
    }

    public void execute(String command) {
        execute(commandParser.parse(command));
    }

    private void execute(AbstractCommand command) {
        if (command.getType() == PLACE) {
            PlaceCommand placeCommand = (PlaceCommand) command;
            update(placeCommand.getPosition(), placeCommand.getDirection());
        } else if (robotOnTable) {
            if (command.getType() == MOVE) {
                updatePosition(position.add(direction.vector()));
            } else if (command.getType() == LEFT) {
                updateDirection(direction.rotateAntiClockwise());
            } else if (command.getType() == RIGHT) {
                updateDirection(direction.rotateClockwise());
            } else if (command.getType() == REPORT) {
                String report = String.format("=> Output: %d,%d,%s", position.getX(), position.getY(), direction.name());
                runtime.print(report);
            }
        }
    }

    public Point2D getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean inBounds(Point2D point) {
        final int tableSize = 5;
        return point.getX() >= 0 && point.getY() >= 0 && point.getX() < tableSize && point.getY() < tableSize;
    }

    private void updateDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    private void updatePosition(Point2D newPosition) {
        if (inBounds(newPosition)) {
            this.position = newPosition;
        }
    }

    private void update(Point2D newPosition, Direction newDirection) {
        if (inBounds(newPosition) && newDirection != Direction.UNKNOWN) {
            this.position = newPosition;
            this.direction = newDirection;
            this.robotOnTable = true;
        }
    }
}

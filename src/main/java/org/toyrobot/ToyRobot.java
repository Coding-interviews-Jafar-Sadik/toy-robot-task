package org.toyrobot;

import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.commands.CommandParser;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

import static org.toyrobot.commands.CommandType.*;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobot {
    private final CommandParser commandParser = new CommandParser();

    private boolean robotOnTable = false;
    private Point2D position = point2d(0, 0);
    private Direction direction = Direction.UNKNOWN;

    public void execute(String command) {
        execute(commandParser.parse(command));
    }

    private void execute(AbstractCommand command) {
        if (command.getType() == PLACE) {
            PlaceCommand placeCommand = (PlaceCommand) command;
            if (inBounds(placeCommand.getPosition()) && placeCommand.getDirection() != Direction.UNKNOWN) {
                this.position = placeCommand.getPosition();
                this.direction = placeCommand.getDirection();
                this.robotOnTable = true;
            }
        } else if (robotOnTable) {
            if (command.getType() == MOVE) {
                Point2D newPosition = position.add(direction.vector());
                if (inBounds(newPosition)) {
                    this.position = newPosition;
                }
            } else if (command.getType() == LEFT) {
                direction = direction.rotateAntiClockwise();
            } else if (command.getType() == RIGHT) {
                direction = direction.rotateClockwise();
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
}

package org.toyrobot;

import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.commands.CommandParser;
import org.toyrobot.commands.CommandType;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

import java.util.Optional;

import static java.lang.String.format;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobot {
    private final CommandParser commandParser = new CommandParser();

    private State currentState = new RobotNotYetOnTable();
    private Point2D position = point2d(0, 0);
    private Direction direction = Direction.UNKNOWN;

    public Optional<String> execute(String commandString) {
        var command = commandParser.parse(commandString);
        return currentState.execute(command);
    }

    public Point2D getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    private class RobotNotYetOnTable implements State {
        @Override
        public Optional<String> execute(AbstractCommand command) {
            if (command.getType() == CommandType.PLACE) {
                var placeCommand = (PlaceCommand) command;
                if (update(placeCommand.getPosition(), placeCommand.getDirection())) {
                    currentState = new RobotOnTable();
                }
            }
            return Optional.empty();
        }
    }

    private class RobotOnTable implements State {
        @Override
        public Optional<String> execute(AbstractCommand command) {
            String message = null;
            switch (command.getType()) {
                case PLACE:
                    PlaceCommand placeCommand = (PlaceCommand) command;
                    update(placeCommand.getPosition(), placeCommand.getDirection());
                    break;

                case MOVE:
                    updatePosition(position.add(direction.vector()));
                    break;

                case LEFT:
                    updateDirection(direction.rotateAntiClockwise());
                    break;

                case RIGHT:
                    updateDirection(direction.rotateClockwise());
                    break;

                case REPORT:
                    message = format("Output: %d,%d,%s", position.getX(), position.getY(), direction.name());
                    break;

                default:
            }
            return Optional.ofNullable(message);
        }

        private void updateDirection(Direction newDirection) {
            direction = newDirection;
        }

        private void updatePosition(Point2D newPosition) {
            if (inBounds(newPosition)) {
                position = newPosition;
            }
        }
    }

    private interface State {
        Optional<String> execute(AbstractCommand command);
    }

    private boolean inBounds(Point2D point) {
        final int tableSize = 5;
        return point.getX() >= 0 && point.getY() >= 0 && point.getX() < tableSize && point.getY() < tableSize;
    }

    private boolean update(Point2D newPosition, Direction newDirection) {
        boolean updated = false;
        if (inBounds(newPosition) && newDirection != Direction.UNKNOWN) {
            this.position = newPosition;
            this.direction = newDirection;
            updated = true;
        }
        return updated;
    }
}

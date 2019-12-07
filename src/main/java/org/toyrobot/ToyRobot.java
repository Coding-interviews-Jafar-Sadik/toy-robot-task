package org.toyrobot;

import org.toyrobot.commands.AbstractCommand;
import org.toyrobot.commands.CommandParser;
import org.toyrobot.commands.CommandType;
import org.toyrobot.commands.PlaceCommand;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;
import org.toyrobot.runtime.Runtime;

import static org.toyrobot.math.Point2D.point2d;

public class ToyRobot {
    private final CommandParser commandParser = new CommandParser();
    private final Runtime runtime;

    private State currentState = new RobotNotYetOnTable();
    private Point2D position = point2d(0, 0);
    private Direction direction = Direction.UNKNOWN;

    public ToyRobot(Runtime runtime) {
        this.runtime = runtime;
    }

    public void execute(String command) {
        currentState.execute(commandParser.parse(command));
    }

    public Point2D getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    private class RobotNotYetOnTable implements State {
        @Override
        public void execute(AbstractCommand command) {
            if (command.getType() == CommandType.PLACE) {
                PlaceCommand placeCommand = (PlaceCommand) command;
                if (update(placeCommand.getPosition(), placeCommand.getDirection())) {
                    currentState = new RobotOnTable();
                }
            }
        }
    }

    private class RobotOnTable implements State {
        @Override
        public void execute(AbstractCommand command) {
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
                    String report = String.format("=> Output: %d,%d,%s", position.getX(), position.getY(), direction.name());
                    runtime.print(report);
                    break;

                default:
            }
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
        void execute(AbstractCommand command);
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

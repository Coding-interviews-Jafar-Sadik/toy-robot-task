package org.toyrobot;

import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.toyrobot.math.Point2D.point2d;

public class ToyRobot {
    private static final Pattern placeCommandRegex = Pattern.compile("place\\s+(\\d)\\s*,\\s*(\\d)\\s*,\\s*(\\w+)");

    private boolean robotOnTable = false;
    private Point2D coords = point2d(0, 0);
    private Direction direction;

    public boolean execute(String command) {
        command = command.trim().toLowerCase();

        if (command.startsWith("place")) {
            final Matcher matcher = placeCommandRegex.matcher(command);
            if (matcher.matches()) {
                Point2D newCoords = point2d(parseInt(matcher.group(1)), parseInt(matcher.group(2)));
                Direction newDirection = Direction.parse(matcher.group(3));

                if (outsideTable(newCoords) || newDirection == Direction.UNKNOWN) {
                    return false;
                }

                this.coords = newCoords;
                this.direction = newDirection;
                this.robotOnTable = true;
            }
        } else if (robotOnTable) {
            if (command.startsWith("move")) {
                Point2D newCoords = coords.add(direction.vector());
                if (outsideTable(newCoords)) {
                    return false;
                }
                this.coords = newCoords;
            } else if (command.startsWith("right")) {
                direction = direction.rotateClockwise();
            } else if (command.startsWith("left")) {
                direction = direction.rotateAntiClockwise();
            } else {
                return false;
            }
        }
        return robotOnTable;
    }

    public Point2D getPosition() {
        return coords;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean outsideTable(Point2D point) {
        final int tableSize = 5;
        return point.getX() < 0 || point.getY() < 0 || point.getX() >= tableSize || point.getY() >= tableSize;
    }
}

package org.toyrobot;

import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToyRobot {
    private static final Pattern commandPattern = Pattern.compile("place\\s+(\\d)\\s*,\\s*(\\d)\\s*,\\s*(\\w+)");
    public static final int TABLE_SIZE = 5;

    private boolean robotOnTable = false;
    private int x = 0;
    private int y = 0;
    private Direction direction;

    public boolean execute(String command) {
        command = command.trim().toLowerCase();

        if (command.startsWith("place")) {
            final Matcher matcher = commandPattern.matcher(command);
            if (matcher.matches()) {
                int newX = Integer.parseInt(matcher.group(1));
                int newY = Integer.parseInt(matcher.group(2));
                Direction newDirection = Direction.parse(matcher.group(3));

                if (!inBounds(newX, newY) || newDirection == Direction.UNKNOWN) {
                    return false;
                }

                this.x = newX;
                this.y = newY;
                this.direction = newDirection;
                this.robotOnTable = true;
            }
        } else if (robotOnTable) {
            if (command.startsWith("move")) {
                int newX = x;
                int newY = y;
                switch (direction) {
                    case NORTH:
                        newY++;
                        break;

                    case SOUTH:
                        newY--;
                        break;

                    case EAST:
                        newX++;
                        break;

                    case WEST:
                        newX--;
                        break;
                }

                if (!inBounds(newX, newY)) {
                    return false;
                }
                this.x = newX;
                this.y = newY;
            } else if (command.startsWith("left")) {

            } else {
                return false;
            }
        }
        return robotOnTable;
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < TABLE_SIZE && y < TABLE_SIZE;
    }
}

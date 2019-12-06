package org.toyrobot;

import org.toyrobot.math.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToyRobot {
    private static final Pattern commandPattern = Pattern.compile("place\\s+(\\d)\\s*,\\s*(\\d)\\s*,\\s*\\w+");
    public static final int TABLE_SIZE = 5;

    private boolean robotOnTable = false;
    private int robotX = 0;
    private int robotY = 0;

    public boolean execute(String command) {
        command = command.trim().toLowerCase();

        if (command.startsWith("place")) {
            final Matcher matcher = commandPattern.matcher(command);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));

                if (!inBounds(x, y)) {
                    return false;
                }

                this.robotX = x;
                this.robotY = y;
                this.robotOnTable = true;
            }
        } else if (command.startsWith("move")) {
            robotY++;
        } else if (command.startsWith("left")) {

        } else {
            return false;
        }

        return robotOnTable;
    }

    public Point2D getPosition() {
        return new Point2D(robotX, robotY);
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < TABLE_SIZE && y < TABLE_SIZE;
    }
}

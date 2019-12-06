package org.toyrobot;

import org.toyrobot.math.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToyRobot {
    private static final Pattern commandPattern = Pattern.compile("place\\s(\\d),(\\d),\\w+");
    private int x = 0;
    private int y = 0;

    public void execute(String command) {
        final Matcher matcher = commandPattern.matcher(command);

        if (matcher.matches()) {
            x = Integer.parseInt(matcher.group(1));
            y = Integer.parseInt(matcher.group(2));
        }
    }

    public Point2D getPosition() {
        return new Point2D(x, y);
    }
}

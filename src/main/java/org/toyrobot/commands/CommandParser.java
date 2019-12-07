package org.toyrobot.commands;

import org.toyrobot.math.Direction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.toyrobot.math.Point2D.point2d;

public class CommandParser {
    private static final Pattern placeCommandRegex = Pattern.compile("place\\s+(\\d)\\s*,\\s*(\\d)\\s*,\\s*(\\w+)");

    public AbstractCommand parse(String command) {
        command = command.trim().toLowerCase();

        if (command.startsWith("place")) {
            final Matcher matcher = placeCommandRegex.matcher(command);
            if (matcher.matches()) return new PlaceCommand(
                    point2d(parseInt(matcher.group(1)), parseInt(matcher.group(2))),
                    Direction.parse(matcher.group(3))
            );
        } else if (command.startsWith("move")) {
            return new MoveCommand();
        } else if (command.startsWith("left")) {
            return new LeftCommand();
        } else if (command.startsWith("right")) {
            return new RightCommand();
        } else if (command.startsWith("report")) {
            return new ReportCommand();
        }

        return new UnknownCommand();
    }
}

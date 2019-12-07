package org.toyrobot.commands;

import lombok.Getter;
import org.toyrobot.math.Direction;
import org.toyrobot.math.Point2D;

@Getter
public class PlaceCommand extends AbstractCommand {
    private final Point2D position;
    private final Direction direction;

    protected PlaceCommand(Point2D position, Direction direction) {
        super(CommandType.PLACE);
        this.position = position;
        this.direction = direction;
    }
}

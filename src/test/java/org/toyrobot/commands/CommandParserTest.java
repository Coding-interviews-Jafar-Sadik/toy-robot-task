package org.toyrobot.commands;

import org.junit.Test;
import org.toyrobot.math.Direction;

import static org.junit.Assert.assertEquals;
import static org.toyrobot.commands.CommandType.LEFT;
import static org.toyrobot.commands.CommandType.MOVE;
import static org.toyrobot.commands.CommandType.PLACE;
import static org.toyrobot.commands.CommandType.RIGHT;
import static org.toyrobot.commands.CommandType.UNKNOWN;
import static org.toyrobot.math.Point2D.point2d;

public class CommandParserTest {
    private CommandParser commandParser = new CommandParser();

    @Test
    public void shouldParsePlaceCommand() {
        AbstractCommand command = parseCommand("place 2,3,west");
        assertEquals(PLACE, command.getType());
        assertEquals(point2d(2, 3), ((PlaceCommand) command).getPosition());
        assertEquals(Direction.WEST, ((PlaceCommand) command).getDirection());
    }

    @Test
    public void shouldParseMoveCommand() {
        assertEquals(MOVE, parseCommand("move").getType());
    }

    @Test
    public void shouldParseLeftCommand() {
        assertEquals(LEFT, parseCommand("left").getType());
    }

    @Test
    public void shouldParseRightCommand() {
        assertEquals(RIGHT, parseCommand("right").getType());
    }

    @Test
    public void shouldParseUnknownCommand() {
        assertEquals(UNKNOWN, parseCommand("jump").getType());
    }

    @Test
    public void expectUnknownCommandForWrongNumberOfParameters() {
        assertEquals(UNKNOWN, parseCommand("place").getType());
        assertEquals(UNKNOWN, parseCommand("place 2").getType());
        assertEquals(UNKNOWN, parseCommand("place 2,3").getType());
        assertEquals(UNKNOWN, parseCommand("place 2,3,west,east").getType());
    }

    @Test
    public void expectUnknownCommandForWrongParameters() {
        assertEquals(UNKNOWN, parseCommand("place a,2,north").getType());
    }

    @Test
    public void shouldAllowWhitespacesInCommand() {
        assertEquals(PLACE, parseCommand("   place    3  ,  3 , south  ").getType());
        assertEquals(MOVE, parseCommand("   move   ").getType());
    }

    @Test
    public void shouldAllowCommandsWithCapitalLetters() {
        assertEquals(PLACE, parseCommand("   PLACE    3  ,  3 , SOUTH  ").getType());
        assertEquals(MOVE, parseCommand("   MOVE   ").getType());
    }

    private AbstractCommand parseCommand(String command) {
        return commandParser.parse(command);
    }
}
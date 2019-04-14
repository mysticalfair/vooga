package test;

import gameengine.GameFactory;
import gameengine.IGameDefinition;
import gameengine.ILevelDefinition;

public class Test {

    public static void main(String args[]) {
        GameFactory g = new GameFactory();
        ILevelDefinition level = g.createLevel();
        IGameDefinition game = g.createGame();
        game.addLevel(level);
    }

}

package nz.willcox.games.tetris.model.game;

import nz.willcox.games.tetris.model.EventListener;

public class Level extends EventListener {

    private static final int INITIAL_LEVEL = 0;

    private int level;

    public Level() {
        level = INITIAL_LEVEL;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

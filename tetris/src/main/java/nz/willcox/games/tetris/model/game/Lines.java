package nz.willcox.games.tetris.model.game;

import nz.willcox.games.tetris.model.EventListener;

public class Lines extends EventListener {

    private static final int INITIAL_LINES = 0;

    private int lines;

    public Lines() {
        lines = INITIAL_LINES;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
        this.triggerListeners();
    }
}

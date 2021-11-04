package nz.willcox.games.tetris.model.game;

import nz.willcox.games.tetris.model.EventListener;

public class Score extends EventListener {

    private static final int INITIAL_SCORE = 0;

    private int score;

    public Score() {
        score = INITIAL_SCORE;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

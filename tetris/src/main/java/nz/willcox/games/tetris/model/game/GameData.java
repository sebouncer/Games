package nz.willcox.games.tetris.model.game;

import nz.willcox.games.tetris.model.game.shape.CurrentShape;

import java.util.List;

public class GameData {

    private final List<Row> rowData;
    private CurrentShape currentShape;
    private int score;

    private GameData(Builder builder) {
        this.rowData = builder.rowData;
        this.currentShape = builder.currentShape;
        this.score = builder.score;
    }

    public List<Row> getRowData() {
        return rowData;
    }

    public CurrentShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(CurrentShape currentShape) {
        this.currentShape = currentShape;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static class Builder {
        private List<Row> rowData;
        private CurrentShape currentShape;
        private int score;

        public Builder rowData(List<Row> rowData) {
            this.rowData = rowData;
            return this;
        }

        public Builder currentShape(CurrentShape currentShape) {
            this.currentShape = currentShape;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public GameData build() {
            return new GameData(this);
        }
    }
}

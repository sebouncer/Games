package nz.willcox.games.tetris.model.game;

import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.NextShape;

import java.util.List;

public class GameData {

    private final List<Row> rowData;
    private CurrentShape currentShape;
    private NextShape nextShape;
    private final Score score;
    private final Lines lines;
    private final Level level;

    private GameData(Builder builder) {
        this.rowData = builder.rowData;
        this.currentShape = builder.currentShape;
        this.nextShape = builder.nextShape;
        this.score = builder.score;
        this.lines = builder.lines;
        this.level = builder.level;
    }

    public List<Row> getRowData() {
        return rowData;
    }

    public CurrentShape getCurrentShape() {
        return currentShape;
    }

    public NextShape getNextShape() {
        return nextShape;
    }

    public void setCurrentShape(CurrentShape currentShape) {
        this.currentShape = currentShape;
    }

    public Score getScore() {
        return score;
    }

    public Lines getLines() {
        return lines;
    }

    public Level getLevel() {
        return level;
    }

    public static class Builder {
        private List<Row> rowData;
        private CurrentShape currentShape;
        private NextShape nextShape;
        private Score score;
        private Lines lines;
        private Level level;

        public Builder rowData(List<Row> rowData) {
            this.rowData = rowData;
            return this;
        }

        public Builder currentShape(CurrentShape currentShape) {
            this.currentShape = currentShape;
            return this;
        }

        public Builder nextShape(NextShape nextShape) {
            this.nextShape = nextShape;
            return this;
        }

        public Builder score(Score score) {
            this.score = score;
            return this;
        }

        public Builder lines(Lines lines) {
            this.lines = lines;
            return this;
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public GameData build() {
            return new GameData(this);
        }
    }
}

package nz.willcox.games.tetris.model;

import java.util.List;

public class GameData {

    private int score;
    private List<Row> rowData;

    private GameData(Builder builder) {
        this.score = builder.score;
        this.rowData = builder.rowData;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Row> getRowData() {
        return rowData;
    }

    public static class Builder {
        private int score;
        private List<Row> rowData;

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder rowData(List<Row> rowData) {
            this.rowData = rowData;
            return this;
        }

        public GameData build() {
            return new GameData(this);
        }
    }
}

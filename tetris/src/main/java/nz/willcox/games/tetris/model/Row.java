package nz.willcox.games.tetris.model;

import java.util.List;

public class Row {

    private List<Block> blocks;

    private Row(Builder builder) {
        this.blocks = builder.blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public static class Builder {
        private List<Block> blocks;

        public Builder blocks(List<Block> blocks) {
            this.blocks = blocks;
            return this;
        }

        public Row build() {
            return new Row(this);
        }
    }
}

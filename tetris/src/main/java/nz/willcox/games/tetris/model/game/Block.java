package nz.willcox.games.tetris.model.game;

public class Block {

    private final int colourNumber;

    private Block(Builder builder) {
        this.colourNumber = builder.colourNumber;
    }

    public int getColourNumber() {
        return colourNumber;
    }

    public static class Builder {
        private int colourNumber;

        public Builder colourNumber(int colourNumber) {
            this.colourNumber = colourNumber;
            return this;
        }

        public Block build() {
            return new Block(this);
        }
    }
}

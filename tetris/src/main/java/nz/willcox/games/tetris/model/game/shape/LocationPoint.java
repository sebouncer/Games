package nz.willcox.games.tetris.model.game.shape;

public class LocationPoint {

    private int topX;
    private int topY;

    private LocationPoint(Builder builder) {
        this.topX = builder.topX;
        this.topY = builder.topY;
    }

    public int getTopX() {
        return topX;
    }

    public void setTopX(int topX) {
        this.topX = topX;
    }

    public int getTopY() {
        return topY;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public static class Builder {
        private int topX;
        private int topY;

        public Builder topX(int topX) {
            this.topX = topX;
            return this;
        }

        public Builder topY(int topY) {
            this.topY = topY;
            return this;
        }

        public LocationPoint build() {
            return new LocationPoint(this);
        }
    }
}

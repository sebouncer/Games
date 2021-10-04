package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.game.Block;

public class ShapeBlock {

    private Block block;
    private LocationPoint locationPoint;

    private ShapeBlock(Builder builder) {
        this.block = builder.block;
        this.locationPoint = builder.locationPoint;;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    public void setLocationPoint(LocationPoint locationPoint) {
        this.locationPoint = locationPoint;
    }

    public static class Builder {
        private Block block;
        private LocationPoint locationPoint;

        public Builder block(Block block) {
            this.block = block;
            return this;
        }

        public Builder locationPoint(LocationPoint locationPoint) {
            this.locationPoint = locationPoint;
            return this;
        }

        public ShapeBlock build() {
            return new ShapeBlock(this);
        }
    }
}

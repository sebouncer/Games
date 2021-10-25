package nz.willcox.games.tetris.model.game.shape;

import java.util.List;

public class TetrisShape {

    private final List<ShapeBlock> shapeBlocks;
    private LocationPoint rotateLocationPoint;

    public TetrisShape(Builder builder) {
        this.shapeBlocks = builder.shapeBlocks;
        this.rotateLocationPoint = builder.rotateLocationPoint;
    }

    public TetrisShape(List<ShapeBlock> shapeBlocks, LocationPoint rotateLocationPoint) {
        this.shapeBlocks = shapeBlocks;
        this.rotateLocationPoint = rotateLocationPoint;
    }

    public List<ShapeBlock> getShapeBlocks() {
        return shapeBlocks;
    }

    public LocationPoint getRotateLocationPoint() {
        return rotateLocationPoint;
    }

    public void setRotateLocationPoint(LocationPoint rotateLocationPoint) {
        this.rotateLocationPoint = rotateLocationPoint;
    }

    public static class Builder {
        private List<ShapeBlock> shapeBlocks;
        private LocationPoint rotateLocationPoint;

        public Builder shapeBlocks(List<ShapeBlock> shapeBlocks) {
            this.shapeBlocks = shapeBlocks;
            return this;
        }

        public Builder rotateLocationPoint(LocationPoint rotateLocationPoint) {
            this.rotateLocationPoint = rotateLocationPoint;
            return this;
        }

        public TetrisShape build() {
            return new TetrisShape(this);
        }
    }
}

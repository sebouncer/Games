package nz.willcox.games.tetris.model.game.shape;

import nz.willcox.games.tetris.model.game.Block;

public class ShapeBlock {

    private Block block;
    private LocationPoint locationPoint;

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
}

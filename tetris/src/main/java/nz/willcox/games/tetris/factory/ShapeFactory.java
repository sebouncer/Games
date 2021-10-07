package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;

public class ShapeFactory {

    @Inject
    public ShapeFactory() {}

    public LocationPoint blockLeftOf(LocationPoint locationPoint) {
        return new LocationPoint.Builder()
                .topX(locationPoint.getTopX() - Constants.BLOCK_WIDTH)
                .topY(locationPoint.getTopY())
                .build();
    }

    public LocationPoint blockRightOf(LocationPoint locationPoint) {
        return new LocationPoint.Builder()
                .topX(locationPoint.getTopX() + Constants.BLOCK_WIDTH)
                .topY(locationPoint.getTopY())
                .build();
    }

    public LocationPoint blockAboveOf(LocationPoint locationPoint) {
        return new LocationPoint.Builder()
                .topX(locationPoint.getTopX())
                .topY(locationPoint.getTopY() - Constants.BLOCK_HEIGHT)
                .build();
    }

    public ShapeBlock createShapeBlock(LocationPoint locationPoint, Block block) {
        return new ShapeBlock.Builder()
                .block(block)
                .locationPoint(locationPoint)
                .build();
    }
}

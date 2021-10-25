package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ZFactory implements ShapeFactory {

    private final ShapePlacement shapePlacement;

    @Inject
    public ZFactory(ShapePlacement shapePlacement) {
        this.shapePlacement = shapePlacement;
    }

    @Override
    public List<ShapeBlock> create(LocationPoint startMidLocation) {
        final Block block = BlockColours.RED_BLOCK;
        final ArrayList<ShapeBlock> shapeBlocks = new ArrayList<>();
        shapeBlocks.add(shapePlacement.createShapeBlock(startMidLocation, block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockRightOf(startMidLocation), block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockAboveOf(startMidLocation), block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockAboveOf(shapePlacement.blockLeftOf(startMidLocation)), block));
        return shapeBlocks;
    }

    @Override
    public TetrisShape createTetrisShape(LocationPoint startMidLocation) {
        return new TetrisShape(create(startMidLocation), createRotateLocationPoint(startMidLocation));
    }

    private LocationPoint createRotateLocationPoint(LocationPoint startMidLocation) {
        return new LocationPoint.Builder()
                .topX(startMidLocation.getTopX())
                .topY(startMidLocation.getTopY())
                .build();
    }
}

package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SquareFactory implements ShapeFactory {

    private final ShapePlacement shapePlacement;

    @Inject
    public SquareFactory(ShapePlacement shapePlacement) {
        this.shapePlacement = shapePlacement;
    }

    @Override
    public List<ShapeBlock> create(LocationPoint startMidLocation) {
        final Block block = BlockColours.YELLOW_BLOCK;
        final ArrayList<ShapeBlock> shapeBlocks = new ArrayList<>();
        shapeBlocks.add(shapePlacement.createShapeBlock(startMidLocation, block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockRightOf(startMidLocation), block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockAboveOf(startMidLocation), block));
        shapeBlocks.add(shapePlacement.createShapeBlock(shapePlacement.blockAboveOf(shapePlacement.blockRightOf(startMidLocation)), block));
        return shapeBlocks;
    }
}

package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class BackLFactory {

    private final ShapeFactory shapeFactory;

    @Inject
    public BackLFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public List<ShapeBlock> create(LocationPoint startMidLocation) {
        final Block block = BlockColours.DARK_BLUE_BLOCK;
        final ArrayList<ShapeBlock> shapeBlocks = new ArrayList<>();
        shapeBlocks.add(shapeFactory.createShapeBlock(startMidLocation, block));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockLeftOf(startMidLocation), block));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockAboveOf(startMidLocation), block));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockAboveOf(shapeFactory.blockAboveOf(startMidLocation)), block));
        return shapeBlocks;
    }
}

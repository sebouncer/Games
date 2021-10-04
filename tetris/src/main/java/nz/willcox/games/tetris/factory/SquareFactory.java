package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SquareFactory {

    private final ShapeFactory shapeFactory;

    @Inject
    public SquareFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public List<ShapeBlock> create(LocationPoint startMidLocation) {
        final ArrayList<ShapeBlock> shapeBlocks = new ArrayList<>();
        shapeBlocks.add(shapeFactory.createShapeBlock(startMidLocation));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockRightOf(startMidLocation)));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockAboveOf(startMidLocation)));
        shapeBlocks.add(shapeFactory.createShapeBlock(shapeFactory.blockAboveOf(shapeFactory.blockRightOf(startMidLocation))));
        return shapeBlocks;
    }
}

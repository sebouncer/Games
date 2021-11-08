package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CloneTetrisShapeService {

    @Inject
    public CloneTetrisShapeService() {}

    public TetrisShape cloneTetrisShape(TetrisShape tetrisShape) {
        return new TetrisShape.Builder()
                .rotateLocationPoint(tetrisShape.getRotateLocationPoint())
                .shapeBlocks(cloneShapeBlocks(tetrisShape.getShapeBlocks()))
                .build();
    }

    private List<ShapeBlock> cloneShapeBlocks(List<ShapeBlock> shapeBlocks) {
        return shapeBlocks.stream()
                .map(this::cloneShapeBlock)
                .collect(Collectors.toList());
    }

    private ShapeBlock cloneShapeBlock(ShapeBlock shapeBlock) {
        return new ShapeBlock.Builder()
                .locationPoint(cloneLocationPoint(shapeBlock.getLocationPoint()))
                .block(shapeBlock.getBlock())
                .build();
    }

    private LocationPoint cloneLocationPoint(LocationPoint locationPoint) {
        return new LocationPoint.Builder()
                .topX(locationPoint.getTopX())
                .topY(locationPoint.getTopY())
                .build();
    }
}

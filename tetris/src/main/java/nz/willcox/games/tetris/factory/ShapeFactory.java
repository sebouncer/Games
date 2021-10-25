package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import java.util.List;

public interface ShapeFactory {

    List<ShapeBlock> create(LocationPoint startMidLocation);
    TetrisShape createTetrisShape(LocationPoint startMidLocation);
}

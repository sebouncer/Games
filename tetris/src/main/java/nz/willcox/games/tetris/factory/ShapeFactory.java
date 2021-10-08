package nz.willcox.games.tetris.factory;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import java.util.List;

public interface ShapeFactory {

    List<ShapeBlock> create(LocationPoint startMidLocation);
}

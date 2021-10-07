package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.factory.SquareFactory;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;

public class RandomNextShapeService {

    private final SquareFactory squareFactory;

    @Inject
    public RandomNextShapeService(
            SquareFactory squareFactory
    ) {
        this.squareFactory = squareFactory;
    }

    public List<ShapeBlock> getRandomNextShapeBlocks() {
        return squareFactory.create(createStartMidLocation());
    }

    private LocationPoint createStartMidLocation() {
        return new LocationPoint.Builder()
                .topX((NUM_COLUMNS -1)/2 * Constants.BLOCK_WIDTH)
                .topY(0 - Constants.BLOCK_HEIGHT)
                .build();
    }
}

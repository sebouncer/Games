package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.factory.FrontLFactory;
import nz.willcox.games.tetris.factory.SquareFactory;
import nz.willcox.games.tetris.factory.ZFactory;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;
import java.util.List;

import static nz.willcox.games.tetris.Constants.NUM_COLUMNS;

public class RandomNextShapeService {

    private final SquareFactory squareFactory;
    private final FrontLFactory frontLFactory;
    private final ZFactory zFactory;

    @Inject
    public RandomNextShapeService(
            SquareFactory squareFactory,
            FrontLFactory frontLFactory,
            ZFactory zFactory
    ) {
        this.squareFactory = squareFactory;
        this.frontLFactory = frontLFactory;
        this.zFactory = zFactory;
    }

    public List<ShapeBlock> getRandomNextShapeBlocks() {

        return zFactory.create(createStartMidLocation());
    }

    private LocationPoint createStartMidLocation() {
        return new LocationPoint.Builder()
                .topX((NUM_COLUMNS -1)/2 * Constants.BLOCK_WIDTH)
                .topY(0 - Constants.BLOCK_HEIGHT)
                .build();
    }
}

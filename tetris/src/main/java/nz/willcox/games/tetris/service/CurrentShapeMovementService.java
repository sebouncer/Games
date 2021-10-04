package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.inject.Inject;

public class CurrentShapeMovementService {

    private static final int DOWNWARDS_MOVEMENT = Constants.BLOCK_HEIGHT/4;

    @Inject
    public CurrentShapeMovementService() {}

    public void moveDown(CurrentShape currentShape) {
        for (ShapeBlock shapeBlock : currentShape.getShapeBlocks()) {
            final LocationPoint locationPoint = shapeBlock.getLocationPoint();
            final int newLocationYPoint = locationPoint.getTopY() + DOWNWARDS_MOVEMENT;
            boolean collision = checkCollisions(newLocationYPoint);
            if (!collision) {
                locationPoint.setTopY(newLocationYPoint);
            }
        }
        currentShape.triggerListeners();
    }

    private boolean checkCollisions(int newLocationYPoint) {
        return false;
    }
}

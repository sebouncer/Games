package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RotateService {

    @Inject
    public RotateService() {}

    public void rotate(TetrisShape tetrisShape) {
        final LocationPoint rotateLocationPoint = tetrisShape.getRotateLocationPoint();
        for (ShapeBlock shapeBlock : tetrisShape.getShapeBlocks()) {
            rotateBlock(shapeBlock, rotateLocationPoint);
        }
    }

    private void rotateBlock(
            ShapeBlock shapeBlock,
            LocationPoint rotateLocationPoint
    ) {
        final LocationPoint locationPoint = shapeBlock.getLocationPoint();
        rotateBlock(locationPoint, rotateLocationPoint);
    }

    public void rotateBlock(
            LocationPoint locationPoint,
            LocationPoint rotateLocationPoint
    ) {
        final int deltaX = rotateLocationPoint.getTopX() - locationPoint.getTopX();
        final int deltaY = rotateLocationPoint.getTopY() - locationPoint.getTopY();

        final int newX = rotateLocationPoint.getTopX() + deltaY;
        final int newY = rotateLocationPoint.getTopY() + (deltaX * -1);
        locationPoint.setTopX(newX);
        locationPoint.setTopY(newY);
    }
}

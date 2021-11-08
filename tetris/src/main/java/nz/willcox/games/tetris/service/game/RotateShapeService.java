package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RotateShapeService {

    private final ShapeCollisionService shapeCollisionService;
    private final CloneTetrisShapeService cloneTetrisShapeService;
    private final RotateService rotateService;

    @Inject
    public RotateShapeService(
            ShapeCollisionService shapeCollisionService,
            CloneTetrisShapeService cloneTetrisShapeService,
            RotateService rotateService
    ) {
        this.shapeCollisionService = shapeCollisionService;
        this.cloneTetrisShapeService = cloneTetrisShapeService;
        this.rotateService = rotateService;
    }

    public void rotateCurrentShape(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        final TetrisShape clonedTetrisShape = cloneTetrisShapeService.cloneTetrisShape(currentShape.getTetrisShape());
        rotateService.rotate(clonedTetrisShape);
        if (!shapeCollisionService.doesShapeCollide(gameData, clonedTetrisShape)) {
            currentShape.setTetrisShape(clonedTetrisShape);
        }
    }
}

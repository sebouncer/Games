package nz.willcox.games.tetris.service.game;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.service.CurrentShapeMovementService;
import nz.willcox.games.tetris.service.ShapeMovementService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DropBlockService {

    private final ShapeMovementService shapeMovementService;
    private final CurrentShapeMovementService currentShapeMovementService;

    @Inject
    public DropBlockService(
            ShapeMovementService shapeMovementService,
            CurrentShapeMovementService currentShapeMovementService
    ) {
        this.shapeMovementService = shapeMovementService;
        this.currentShapeMovementService = currentShapeMovementService;
    }

    public void dropBlock(GameData gameData) {
        final CurrentShape currentShape = gameData.getCurrentShape();
        final List<Row> rowData = gameData.getRowData();
        while (!shapeMovementService.willCollideOnMoveDown(currentShape, rowData)) {
            shapeMovementService.moveDown(currentShape);
        }
        currentShapeMovementService.moveCurrentShapeDown(gameData);
    }
}

package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.service.game.DropBlockService;
import nz.willcox.games.tetris.view.controls.PlayerEventListener;

public class GamePlayerControlService implements PlayerEventListener {

    private static final int LEFT = -1;
    private static final int RIGHT = 1;

    private final GameData gameData;
    private final ShapeMovementService shapeMovementService;
    private final RotateShapeService rotateShapeService;
    private final CurrentShapeMovementService currentShapeMovementService;
    private final DropBlockService dropBlockService;

    public GamePlayerControlService(
            GameData gameData,
            ShapeMovementService shapeMovementService,
            RotateShapeService rotateShapeService,
            CurrentShapeMovementService currentShapeMovementService,
            DropBlockService dropBlockService
    ) {
        this.gameData = gameData;
        this.shapeMovementService = shapeMovementService;
        this.rotateShapeService = rotateShapeService;
        this.currentShapeMovementService = currentShapeMovementService;
        this.dropBlockService = dropBlockService;
    }

    @Override
    public void leftAction() {
        moveDirection(LEFT);
    }

    private void moveDirection(int direction) {
        boolean collision = shapeMovementService.willCollideOnMoveSideways(gameData, direction);
        if (!collision) {
            shapeMovementService.moveSidewaysDirection(gameData, direction);
        }
    }

    @Override
    public void downAction() {
        currentShapeMovementService.moveCurrentShapeDown(gameData);
        currentShapeMovementService.moveCurrentShapeDown(gameData);
        currentShapeMovementService.moveCurrentShapeDown(gameData);
    }

    @Override
    public void downActionReleased() {
        System.out.println("DOWN IS RELEASE!!!");
    }

    @Override
    public void upAction() {
        rotateShapeService.rotateCurrentShape(gameData);
    }

    @Override
    public void rightAction() {
        moveDirection(RIGHT);
    }

    @Override
    public void buttonStart() {

    }

    @Override
    public void buttonOne() {
        rotateShapeService.rotateCurrentShape(gameData);
    }

    @Override
    public void buttonTwo() {
        dropBlockService.dropBlock(gameData);
    }

    public static class Factory {
        public GamePlayerControlService create(
                GameData gameData,
                ShapeMovementService shapeMovementService,
                RotateShapeService rotateShapeService,
                CurrentShapeMovementService currentShapeMovementService,
                DropBlockService dropBlockService
        ) {
            return new GamePlayerControlService(gameData, shapeMovementService, rotateShapeService, currentShapeMovementService, dropBlockService);
        }
    }
}

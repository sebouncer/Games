package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.view.controls.PlayerEventListener;

public class GamePlayerControlService implements PlayerEventListener {

    private static final int LEFT = -1;
    private static final int RIGHT = 1;

    private final GameData gameData;
    private final ShapeMovementService shapeMovementService;
    private final RotateShapeService rotateShapeService;

    public GamePlayerControlService(
            GameData gameData,
            ShapeMovementService shapeMovementService
    ) {
        this.gameData = gameData;
        this.shapeMovementService = shapeMovementService;
        this.rotateShapeService = new RotateShapeService(
                new ShapeCollisionService(),
                new CloneTetrisShapeService(),
                new RotateService()
        );
    }

    @Override
    public void leftAction() {
        System.out.println("Left!!!");
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
        System.out.println("DOWN!!!");
    }

    @Override
    public void upAction() {
        System.out.println("Up and Rotate!!!");
        rotateShapeService.rotateCurrentShape(gameData);
    }

    @Override
    public void rightAction() {
        System.out.println("Right!!!");
        moveDirection(RIGHT);
    }

    @Override
    public void buttonStart() {

    }

    @Override
    public void buttonOne() {
        System.out.println("Button 1 and Rotate!!!");
        rotateShapeService.rotateCurrentShape(gameData);
    }

    @Override
    public void buttonTwo() {

    }
}

package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.BlockColours;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.Row;
import nz.willcox.games.tetris.model.game.shape.CurrentShape;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.NextShape;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;
import nz.willcox.games.tetris.model.game.shape.TetrisShape;
import nz.willcox.games.tetris.view.controls.PlayerOneControls;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

    private final GameCreator gameCreator;
    private final CurrentShapeMovementService currentShapeMovementService;
    private final ShapeMovementService shapeMovementService;
    private final RandomNextShapeService randomNextShapeService;
    private final PlayerOneControls playerOneControls;

    private GameData playerOneGameData;

    @Inject
    public GameRunner(
            GameCreator gameCreator,
            CurrentShapeMovementService currentShapeMovementService,
            ShapeMovementService shapeMovementService,
            RandomNextShapeService randomNextShapeService,
            PlayerOneControls playerOneControls
    ) {
        this.gameCreator = gameCreator;
        this.currentShapeMovementService = currentShapeMovementService;
        this.shapeMovementService = shapeMovementService;
        this.randomNextShapeService = randomNextShapeService;
        this.playerOneControls = playerOneControls;
    }

    public void initialiseOnePlayer() {
        playerOneGameData = gameCreator.createGame();

        final GamePlayerControlService playerEventListener = new GamePlayerControlService(playerOneGameData, shapeMovementService);
        playerOneControls.addListener(playerEventListener);

        new Timer().scheduleAtFixedRate(new GameActionTimerTask(), 1000, 100);
    }

    public GameData getPlayerOneGameData() {
        return playerOneGameData;
    }

    private class GameActionTimerTask extends TimerTask {

        @Override
        public void run() {

            // check all collisions
            // if collided, move next shape to current
            // move shape
            currentShapeMovementService.moveCurrentShapeDown(playerOneGameData);
        }
    }
}

package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.GameData;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

    private final GameCreator gameCreator;
    private final CurrentShapeMovementService currentShapeMovementService;

    private GameData playerOneGameData;

    @Inject
    public GameRunner(
            GameCreator gameCreator,
            CurrentShapeMovementService currentShapeMovementService
    ) {
        this.gameCreator = gameCreator;
        this.currentShapeMovementService = currentShapeMovementService;
    }

    public void initialiseOnePlayer() {
        playerOneGameData = gameCreator.createGame();

        new Timer().scheduleAtFixedRate(new GameActionTimerTask(), 3000, 500);
    }

    public GameData getPlayerOneGameData() {
        return playerOneGameData;
    }

    private class GameActionTimerTask extends TimerTask {

        @Override
        public void run() {
            currentShapeMovementService.moveDown(playerOneGameData.getCurrentShape());
        }
    }
}

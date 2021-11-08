package nz.willcox.games.tetris.service;

import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.service.game.CurrentShapeMovementService;
import nz.willcox.games.tetris.service.game.DropBlockService;
import nz.willcox.games.tetris.service.game.GamePlayerControlService;
import nz.willcox.games.tetris.service.game.RotateShapeService;
import nz.willcox.games.tetris.service.game.ShapeMovementService;
import nz.willcox.games.tetris.view.controls.PlayerOneControls;

import javax.inject.Inject;
import java.util.Timer;
import java.util.TimerTask;

public class GameRunner {

    private final GameCreator gameCreator;
    private final CurrentShapeMovementService currentShapeMovementService;
    private final ShapeMovementService shapeMovementService;
    private final PlayerOneControls playerOneControls;
    private final DropBlockService dropBlockService;
    private final RotateShapeService rotateShapeService;

    private GameData playerOneGameData;

    @Inject
    public GameRunner(
            GameCreator gameCreator,
            CurrentShapeMovementService currentShapeMovementService,
            ShapeMovementService shapeMovementService,
            PlayerOneControls playerOneControls,
            RotateShapeService rotateShapeService,
            DropBlockService dropBlockService
    ) {
        this.gameCreator = gameCreator;
        this.currentShapeMovementService = currentShapeMovementService;
        this.shapeMovementService = shapeMovementService;
        this.playerOneControls = playerOneControls;
        this.dropBlockService = dropBlockService;
        this.rotateShapeService = rotateShapeService;
    }

    public void initialiseOnePlayer() {
        playerOneGameData = gameCreator.createGame();

        final GamePlayerControlService gamePlayerControlService = new GamePlayerControlService.Factory().create(
                playerOneGameData,
                shapeMovementService,
                rotateShapeService,
                currentShapeMovementService,
                dropBlockService
        );
        playerOneControls.addListener(gamePlayerControlService);

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new GameActionTimerTask(), 1000, 100);
    }

    public GameData getPlayerOneGameData() {
        return playerOneGameData;
    }

    private class GameActionTimerTask extends TimerTask {

        @Override
        public void run() {
            currentShapeMovementService.moveCurrentShapeDown(playerOneGameData);
        }
    }
}

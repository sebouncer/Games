package nz.willcox.games.tetris;

import nz.willcox.games.tetris.model.page.CurrentPage;
import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.page.PageEnum;
import nz.willcox.games.tetris.service.GameCreator;
import nz.willcox.games.tetris.view.game.GamePanel;
import nz.willcox.games.tetris.view.menu.MenuPanel;
import nz.willcox.games.tetris.view.options.OptionsPanel;

import javax.inject.Inject;

public class Controller implements Listener {

    private final TetrisFrame tetrisFrame;
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final OptionsPanel optionsPanel;
    private final GameCreator gameCreator;
    private final CurrentPage currentPage;

    @Inject
    public Controller(
            TetrisFrame tetrisFrame,
            MenuPanel menuPanel,
            GamePanel gamePanel,
            OptionsPanel optionsPanel,
            GameCreator gameCreator,
            CurrentPage currentPage
    ) {
        this.tetrisFrame = tetrisFrame;
        this.menuPanel = menuPanel;
        this.gamePanel = gamePanel;
        this.gameCreator = gameCreator;
        this.currentPage = currentPage;
        this.optionsPanel = optionsPanel;

        currentPage.addListener(this);
    }

    public void initialise() {
        tetrisFrame.initialise();

        currentPage.setPage(PageEnum.MENU);

//        final GameData gameData = gameCreator.createGame();
//        gamePanel.setGameData(gameData);

//        TetrisPanel newPanel = menuPanel;
//
//        tetrisFrame.switchPanel(newPanel);
//        eventTrigger();
    }

    @Override
    public void eventTrigger() {
        final PageEnum page = currentPage.getPage();
        switch (page) {
            case MENU:
                tetrisFrame.switchPanel(menuPanel);
                break;
            case GAME:
                tetrisFrame.switchPanel(gamePanel);
                break;
            case OPTIONS:
                tetrisFrame.switchPanel(optionsPanel);
                break;
        }
    }
}

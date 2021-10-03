package nz.willcox.games.tetris.view.menu;

import nz.willcox.games.tetris.model.page.CurrentPage;
import nz.willcox.games.tetris.model.page.GameConfig;
import nz.willcox.games.tetris.model.page.PageEnum;
import nz.willcox.games.tetris.view.component.MenuButton;
import nz.willcox.games.tetris.view.controls.PlayerEventListener;
import nz.willcox.games.tetris.view.controls.PlayerOneControls;
import nz.willcox.games.tetris.view.controls.PlayerTwoControls;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonPanel extends JPanel implements PlayerEventListener {

    public static final int BUTTON_PANEL_WIDTH = 140;
    public static final int BUTTON_PANEL_HEIGHT = 100;

    private static final int BUTTON_Y_GAP = 30;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_X = BUTTON_PANEL_WIDTH/2 - BUTTON_WIDTH/2;
    private static final int BUTTON_Y = 10;
    private static final int INITIAL_BUTTON_TO_SELECT = 0;
    private static final String ONE_PLAYER = "One Player";
    private static final String TWO_PLAYER = "Two Player";
    private static final String OPTIONS = "Options";

    private final GameConfig gameConfig;
    private final PlayerOneControls playerOneControls;
    private final PlayerTwoControls playerTwoControls;
    private final CurrentPage currentPage;

    private MenuButton playGameOnePlayerButton;
    private MenuButton playGameTwoPlayerButton;
    private MenuButton optionsButton;
    private List<MenuButton> menuButtons;
    private int menuButtonsSize;
    private int selectedButton;

    @Inject
    public ButtonPanel(
            GameConfig gameConfig,
            PlayerOneControls playerOneControls,
            PlayerTwoControls playerTwoControls,
            CurrentPage currentPage
    ) {
        this.gameConfig = gameConfig;
        this.playerOneControls = playerOneControls;
        this.playerTwoControls = playerTwoControls;
        this.currentPage = currentPage;
        setSize(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        playGameOnePlayerButton = createPlayGameOnePlayerButton();
        playGameTwoPlayerButton = createPlayGameTwoPlayerButton();
        optionsButton = createOptionsButton();

        menuButtons = new ArrayList<>();
        menuButtons.add(playGameOnePlayerButton);
        menuButtons.add(playGameTwoPlayerButton);
        menuButtons.add(optionsButton);
        menuButtonsSize = menuButtons.size();
        setLayout(null);
    }

    private MenuButton createPlayGameOnePlayerButton() {
        return new MenuButton.MenuButtonBuilder()
                .withText(ONE_PLAYER)
                .withAction(this::switchToOnePlayerGame)
                .withBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build();
    }

    private MenuButton createPlayGameTwoPlayerButton() {
        return new MenuButton.MenuButtonBuilder()
                .withText(TWO_PLAYER)
                .withAction(this::switchToTwoPlayerGame)
                .withBounds(BUTTON_X, BUTTON_Y + BUTTON_Y_GAP, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build();
    }

    private MenuButton createOptionsButton() {
        return new MenuButton.MenuButtonBuilder()
                .withText(OPTIONS)
                .withAction(this::switchToOptionsPanel)
                .withBounds(BUTTON_X, BUTTON_Y + 2*BUTTON_Y_GAP, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build();
    }

    private void switchToOnePlayerGame() {
        gameConfig.setNumberOfPlayers(1);
        currentPage.setPage(PageEnum.GAME);
    }

    private void switchToTwoPlayerGame() {
        gameConfig.setNumberOfPlayers(2);
        currentPage.setPage(PageEnum.GAME);
    }

    private void switchToOptionsPanel() {
        gameConfig.setNumberOfPlayers(0);
        currentPage.setPage(PageEnum.OPTIONS);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        playGameOnePlayerButton.paintComponent(g);
        playGameTwoPlayerButton.paintComponent(g);
        optionsButton.paintComponent(g);
    }

    @Override
    public void leftAction() {}

    @Override
    public void downAction() {
        setSelectedButtonNotSelected();
        incrementSelectedButtonNumber();
        setSelectedButtonSelected();
        repaint();
    }

    @Override
    public void upAction() {
        setSelectedButtonNotSelected();
        decrementSelectedButtonNumber();
        setSelectedButtonSelected();
        repaint();
    }

    @Override
    public void rightAction() {}

    @Override
    public void buttonStart() {
        actionSelectedButton();
        repaint();
    }

    @Override
    public void buttonOne() {}

    @Override
    public void buttonTwo() {}

    public void initialise() {
        resetButtonsSelected();
        selectedButton = INITIAL_BUTTON_TO_SELECT;
        setSelectedButtonSelected();
        addKeyListener(playerOneControls);
        addKeyListener(playerTwoControls);
        playerOneControls.addListener(this);
        playerTwoControls.addListener(this);
        this.setFocusable(true);
    }

    private void setSelectedButtonSelected() {
        getSelectedButton().setButtonSelected();
    }

    private void setSelectedButtonNotSelected() {
        getSelectedButton().setButtonNotSelected();
    }

    private void incrementSelectedButtonNumber() {
        selectedButton = ++selectedButton%menuButtonsSize;
    }

    private void decrementSelectedButtonNumber() {
        selectedButton = (--selectedButton+menuButtonsSize)%menuButtonsSize;
    }

    private void actionSelectedButton() {
        getSelectedButton().getAction().run();
    }

    private MenuButton getSelectedButton() {
        return menuButtons.get(selectedButton);
    }

    private void resetButtonsSelected() {
        for (MenuButton menuButton : menuButtons) {
            menuButton.setButtonNotSelected();
        }
    }

    public void destroy() {
        this.removeKeyListener(playerOneControls);
        this.removeKeyListener(playerTwoControls);
        playerOneControls.removeListener(this);
        playerTwoControls.removeListener(this);
    }
}

package nz.willcox.games.tetris.view.game;

import nz.willcox.games.tetris.Constants;
import nz.willcox.games.tetris.model.Listener;
import nz.willcox.games.tetris.model.game.Block;
import nz.willcox.games.tetris.model.game.GameData;
import nz.willcox.games.tetris.model.game.shape.LocationPoint;
import nz.willcox.games.tetris.model.game.shape.NextShape;
import nz.willcox.games.tetris.model.game.shape.ShapeBlock;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static nz.willcox.games.tetris.Constants.MID_TOP_X;
import static nz.willcox.games.tetris.Constants.MID_TOP_Y;

public class PlayerSideNextShapePanel extends JPanel implements Listener {

    public static final int WIDTH = PlayerSidePanelBase.WIDTH;
    public static final int HEIGHT = PlayerSidePanelBase.HEIGHT;

    private static final String TITLE = "NEXT SHAPE";
    private static final int BLOCK_HEIGHT = 15;
    private static final int BLOCK_WIDTH = 15;

    private final GameData gameData;
    private final PlayerBlockColours playerBlockColours;
    private final PlayerSidePanelBase playerSidePanelBase;

    public PlayerSideNextShapePanel(
            GameData gameData,
            PlayerBlockColours playerBlockColours,
            PlayerSidePanelBase playerSidePanelBase
    ) {
        this.gameData = gameData;
        this.playerBlockColours = playerBlockColours;
        this.playerSidePanelBase = playerSidePanelBase;

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        initialise();
    }

    public void initialise() {
        gameData.getNextShape().addListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerSidePanelBase.drawBorder(g);
        playerSidePanelBase.drawTitle(g, TITLE);
        drawNextShape(g);
    }

    private void drawNextShape(Graphics g) {

        final NextShape nextShape = gameData.getNextShape();
        final List<ShapeBlock> shapeBlocks = nextShape.getTetrisShape().getShapeBlocks();
        for (ShapeBlock shapeBlock : shapeBlocks) {
            final LocationPoint locationPoint = shapeBlock.getLocationPoint();
            final LocationPoint blockPositionLocationPoint = new LocationPoint.Builder()
                    .topX(getNewX(locationPoint))
                    .topY(getNewY(locationPoint))
                    .build();
            drawBlock(g, shapeBlock.getBlock(), blockPositionLocationPoint, BLOCK_WIDTH, BLOCK_HEIGHT, 1);
        }
    }

    private int getNewX(LocationPoint locationPoint) {
        return (locationPoint.getTopX() - MID_TOP_X) / Constants.BLOCK_WIDTH * BLOCK_WIDTH + getMidOfThisPanel() - BLOCK_WIDTH/2;
    }

    private int getNewY(LocationPoint locationPoint) {
        return (locationPoint.getTopY() - MID_TOP_Y) / Constants.BLOCK_HEIGHT * BLOCK_HEIGHT + playerSidePanelBase.getMidHeightOfThisPanel();
    }

    private int getMidOfThisPanel() {
        return WIDTH/2;
    }

    private void drawBlock(
            Graphics g,
            Block block,
            LocationPoint locationPoint,
            int blockWidth,
            int blockHeight,
            int blockBorderWidth
    ) {
        g.setColor(playerBlockColours.getBlockBorderColour(block));
        g.fillRect(
                locationPoint.getTopX() - blockBorderWidth,
                locationPoint.getTopY() - blockBorderWidth,
                blockWidth + (2*blockBorderWidth),
                blockHeight + (2*blockBorderWidth)
        );
        g.setColor(playerBlockColours.getBlockColour(block));
        g.fillRect(
                locationPoint.getTopX() + blockBorderWidth,
                locationPoint.getTopY() + blockBorderWidth,
                blockWidth - (2*blockBorderWidth),
                blockHeight -(2*blockBorderWidth)
        );
    }

    @Override
    public void eventTrigger() {
        repaint();
    }

    public static class Factory {
        public PlayerSideNextShapePanel create(
                GameData gameData
        ) {
            final PlayerBlockColours playerBlockColours = new PlayerBlockColours();
            final PlayerSidePanelBase playerSidePanelBase = new PlayerSidePanelBase();
            return new PlayerSideNextShapePanel(gameData, playerBlockColours, playerSidePanelBase);
        }
    }
}

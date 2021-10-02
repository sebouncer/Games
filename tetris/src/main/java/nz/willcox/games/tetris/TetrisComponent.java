package nz.willcox.games.tetris;

import dagger.Component;
import nz.willcox.games.tetris.view.menu.MenuPanel;

import javax.inject.Singleton;

@Singleton
@Component(modules = TetrisModule.class)
public interface TetrisComponent {
    Application getApplication();
    TetrisFrame getTetrisFrame();
    MenuPanel getGamePanel();
}

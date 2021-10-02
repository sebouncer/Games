package nz.willcox.games.tetris.model;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CurrentPage extends EventListener {

    private PageEnum page;

    @Inject
    public CurrentPage() {}

    public PageEnum getPage() {
        return page;
    }

    public void setPage(PageEnum page) {
        this.page = page;
        triggerListeners();
    }
}

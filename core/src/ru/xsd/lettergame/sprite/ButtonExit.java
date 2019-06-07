package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.xsd.lettergame.base.TouchButton;
import ru.xsd.lettergame.math.Rect;
import ru.xsd.lettergame.screen.MenuScreen;

public class ButtonExit extends TouchButton {

    private Game game;

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    public ButtonExit(TextureAtlas atlas, Game game){
        super(atlas.findRegion("btExit"));
        this.game = game;
    }

    @Override
    public void action() {
        if (this.game == null) {
            Gdx.app.exit();
        } else {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.10f);
        setRight(worldBounds.getRight() - 0.02f);
        setTop(worldBounds.getTop() - 0.02f);
    }
}

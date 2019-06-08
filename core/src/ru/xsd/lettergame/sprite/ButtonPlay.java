package ru.xsd.lettergame.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.xsd.lettergame.base.TouchButton;
import ru.xsd.lettergame.screen.GameScreen;

public class ButtonPlay extends TouchButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
        setHeightProportion(0.5f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen(game));
    }
}

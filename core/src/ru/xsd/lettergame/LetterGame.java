package ru.xsd.lettergame;

import com.badlogic.gdx.Game;

import ru.xsd.lettergame.screen.MenuScreen;

public class LetterGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen());
    }
}

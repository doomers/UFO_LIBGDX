package in.doomers.rahul.ufo;

import com.badlogic.gdx.Game;

import in.doomers.rahul.ufo.core.MainMenu;
public class MyGdxGame extends Game {
    MainMenu mainMenu;
    @Override
    public void create() {
        mainMenu=new MainMenu();
        setScreen(mainMenu) ;
        mainMenu=null;
    }

    @Override
    public void dispose() {
        super.dispose();
        mainMenu.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }



}

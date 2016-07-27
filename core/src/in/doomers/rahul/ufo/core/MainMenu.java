package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;


/**
 * Created by Rahul on 11/28/2015.
 */
public class MainMenu implements Screen {
    TweenManager tweenManager;
    Texture animhd;
    Sprite animhdsprite;
    SpriteBatch batch;
    Random rand;
    StartMenu startMenu;
    @Override
    public void show() {
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        animhd = new Texture("ufo/doomers.png");
        animhdsprite = new Sprite(animhd);
        animhdsprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Tween.set(animhdsprite, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(animhdsprite, SpriteAccessor.ALPHA, 1).target(1).repeatYoyo(1,1).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                startMenu=new StartMenu();
                ((Game) Gdx.app.getApplicationListener()).setScreen(startMenu);
            }
        }).start(tweenManager);
       rand=new Random();

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(rand.nextInt(2),rand.nextInt(2) , rand.nextInt(2), rand.nextInt(2));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tweenManager.update(delta);
        batch.begin();
        animhdsprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        animhd.dispose();
        startMenu.dispose();
    }
}

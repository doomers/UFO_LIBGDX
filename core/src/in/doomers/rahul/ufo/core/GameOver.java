package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Rahul on 12/30/2015.
 */
public class GameOver implements Screen{
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay,buttonExit;
    private BitmapFont white,black;
    private Label heading;
    private GameScreen gameScreen;
    private TweenManager tweenManager;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas =new TextureAtlas("ui/atlas.pack");
        skin =new Skin(atlas);

        table =new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        white= new BitmapFont(Gdx.files.internal("font/whitefont.fnt"),false);
        black= new BitmapFont(Gdx.files.internal("font/blackfont.fnt"),false);
        TextButton.TextButtonStyle textButtonStyle =new TextButton.TextButtonStyle();
        textButtonStyle.up=skin.getDrawable("button.up");
        textButtonStyle.down=skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX=-5;
        textButtonStyle.pressedOffsetY=-5;
        textButtonStyle.font=white;

        buttonPlay =new TextButton("PLAY AGAIN",textButtonStyle);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen= new GameScreen();
                ((Game) Gdx.app.getApplicationListener()).setScreen(gameScreen);
                gameScreen=null;
            }
        });
        buttonPlay.pad(50);
        buttonPlay.setColor(Color.GREEN);
        buttonPlay.setSize(Gdx.graphics.getHeight() / 9, Gdx.graphics.getHeight() / 15);
        // buttonPlay.setColor(Color.GOLD);




        buttonExit = new TextButton("EXIT", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonExit.pad(50);
        buttonExit.setSize(Gdx.graphics.getHeight() / 9, Gdx.graphics.getHeight() / 15);
        buttonExit.setColor(Color.RED);
        // buttonExit.setColor(Color.GREEN);


        heading =new Label("GAME OVER",new Label.LabelStyle(white, Color.GOLDENROD));
        heading.setFontScale(5);


        table.add(heading).left();
        table.getCell(heading).spaceBottom(Gdx.graphics.getHeight() / 10);
        table.getCell(heading).spaceLeft(200);
        table.row();
        table.padBottom(Gdx.graphics.getHeight() / 7);
        table.add(buttonPlay).maxSize(300, 150);
        table.row();
        table.getCell(buttonPlay).spaceBottom(15);
        table.add(buttonExit).maxSize(300, 150);
       // table.debug();
        stage.addActor(table) ;

        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccesor());

        //heading and button animations
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay,ActorAccesor.ALPHA).target(0))
                .push(Tween.set(buttonExit,ActorAccesor.ALPHA).target(0))
                .push(Tween.from(heading,ActorAccesor.ALPHA,.5f).target(0))
                .push(Tween.to(buttonPlay,ActorAccesor.ALPHA,.5f).target(1))
                .push(Tween.to(buttonExit,ActorAccesor.ALPHA,.5f).target(1))
                .end().start(tweenManager);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // tweenManager.update(delta);
        stage.act(delta);
        stage.draw();


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
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        white.dispose();
        gameScreen.dispose();
        black.dispose();


    }
}

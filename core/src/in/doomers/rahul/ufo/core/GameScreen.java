package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class GameScreen implements Screen,InputProcessor {

    SpriteBatch batch;

    Texture backgroundtexture,playerTexture;
    TextureRegion playerregion;
    Sprite player,backgroundsprite;
    Vector2 playerposition;
    Rectangle playerbounds;
    Sound ufoflying;
    Sound ufocrash;
    Explosion explosion;
    GameScreen gamescreen;
  ShapeRenderer shapeRenderer;

    //private Enemy enemy;
    ArrayList<Enemy> enemy;
    Iterator<Enemy> enemyiterator;
    ArrayList<EnemyS2>senemy;
    Iterator<EnemyS2>enemysiterator;
    ArrayList<BulletMan>bulletMans;
    Iterator<BulletMan>bulletManIterator;
    ArrayList<Bullets>bullets;
    Iterator<Bullets>bulletsiterator;
    ArrayList<ScreenrotSprite>screenrotators;
    Iterator<ScreenrotSprite>screenrotIterator;
    ArrayList<Shield>shields;
    Iterator<Shield>shieldIterator;
    ArrayList<ScoreMultiplier>multiplier;
    Iterator<ScoreMultiplier>multiplierIterator;
    ArrayList<Randomalien>randomalien;
    Iterator<Randomalien>alienIterator;
    Vector2 bulletmanposition;
    OrtCamera camera;
    Random rand;
    int repeatime;
    int y=0,y1;
    long rot=400;
    boolean shield=false;
    float a=480;
   //integrating box2d with gamescreen
   private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton Pause,sound;
    private BitmapFont white,black;
    private Boolean pause=false,music=true;
    //HIGHSCORE STUFF
    private int score=0;
    Preferences prefs=Gdx.app.getPreferences("HIGHSCORE");
    int highscore=prefs.getInteger("HIGHSCORE");
    private int mfactor=1;
    private Boolean crash=false;


    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Xanadu.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    BitmapFont font36,font50;
     // font size 12 pixels
   //render time variables
   float deltatime=Gdx.graphics.getDeltaTime();
    float timelapsed=0;


    @Override
    public void show() {

        shapeRenderer = new ShapeRenderer();
       // intializiing spritebatch--->is basically paper to draw things onto it.
        batch =new SpriteBatch();
        playerTexture=new Texture("ufo/ufo.png");
        playerTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        player=new Sprite(playerTexture);
        player.setPosition(100,100);
        player.setSize(80,40);
        //initilizing camera&&background texture which orthographic camera is going to be used.
        backgroundtexture= new Texture("darkback.png");
        backgroundtexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        backgroundsprite=new Sprite(backgroundtexture);


        camera = new OrtCamera(backgroundtexture);

        //initiating sounds
        ufoflying=  Gdx.audio.newSound(Gdx.files.internal("sounds/ufoMobileBattle.mp3"));
        long ufoflyingId =ufoflying.play();
        ufoflying.setPitch(ufoflyingId,0.3f);
        ufoflying.setVolume(ufoflyingId,0.1f);
        ufocrash=Gdx.audio.newSound(Gdx.files.internal("sounds/ufocrash.mp3"));
        long ufocrashId =ufocrash.play();
        ufocrash.setPitch(ufocrashId,1.5f);

        //initializing player position
        playerposition= new Vector2(player.getX(),player.getY());

        playerbounds =new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());


        //explosion initialization
        explosion=new Explosion(playerposition);


       //bitmapfont generator
        stagestuff();


        //initializing enemies ArrayList
        enemy= new ArrayList<Enemy>();
        senemy=new ArrayList<EnemyS2>();
        bulletMans=new ArrayList<BulletMan>();
        bullets=new ArrayList<Bullets>();
        shields=new ArrayList<Shield>();
        screenrotators =new ArrayList<ScreenrotSprite>();
        multiplier= new ArrayList<ScoreMultiplier>();
        randomalien= new ArrayList<Randomalien>();
        rand =new Random();

            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    if(!pause){
                    repeatime = rand.nextInt(10);
                    y = rand.nextInt(480);
                    enemy.add(new Enemy(new Vector2(playerposition.x +800, y)));
                   score++;}
                }

            },5, 13 + repeatime, 1000);
            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    if(!pause) {
                        repeatime = rand.nextInt(10);
                        y = rand.nextInt(480);
                        screenrotators.add(new ScreenrotSprite(new Vector2(playerposition.x + 800, y)));
                    }
                }

            },8, 10 + repeatime, 1000);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(!pause) {
                        repeatime = rand.nextInt(10);
                        senemy.add(new EnemyS2(new Vector2(playerposition.x + 800, 480 / 2)));
                        score += mfactor;
                        ufoflying.play();
                    }
                }
            }, 2, 2 + repeatime, 1000);


            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    if(!pause){
                    y1 = rand.nextInt(480);
                    bulletmanposition = new Vector2(playerposition.x + 800, y1);
                    bulletMans.add(new BulletMan(bulletmanposition));
                     score+=mfactor;}
                     bullets.add(new Bullets(new Vector2(bulletmanposition.x-150,bulletmanposition.y)));
                     bullets.add(new Bullets(new Vector2(bulletmanposition.x-100,bulletmanposition.y)));
                     bullets.add(new Bullets(new Vector2(bulletmanposition.x-50,bulletmanposition.y)));
                }

            }, 13, 12, 1000);
            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    if(!pause){
                    bullets.add(new Bullets(new Vector2(bulletmanposition.x - 150, bulletmanposition.y)));

                        score += mfactor;
                    }
                }

            }, 13, 12, 1000);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!pause) {
                    repeatime = rand.nextInt(40);
                    y1 = rand.nextInt(480);
                    shields.add(new Shield(new Vector2(playerposition.x + 800 , y1)));
                    multiplier.add(new ScoreMultiplier(new Vector2(playerposition.x + 800, y1)));

                }
            }
        }, 7, 7 + repeatime, 1000);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!pause) {
                    repeatime = rand.nextInt(5);
                    y1 = rand.nextInt(480);
                    multiplier.add(new ScoreMultiplier(new Vector2(playerposition.x + 800, y1)));

                }
            }
        }, 3, 2 + repeatime, 1000);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!pause) {
                    repeatime = rand.nextInt(5);
                    y1 = rand.nextInt(480);
                    randomalien.add(new Randomalien(new Vector2(playerposition.x+ 800,y1)));
                System.out.print("random Alien");
                }

            }
        },3,3+repeatime,2000);



        //player.setBounds(playerposition.x,playerposition.y,player.getWidth(),player.getHeight());
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);


    }








    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        String yourscore= "Score"+"   "+score;


        playerposition.y-=Gdx.input.getAccelerometerX()*(1+score/50);
        //0playerposition.x-=Gdx.input.getAccelerometerY()*(1+score/50);
        //System.out.println("acc.y"+"   "+Gdx.input.getAccelerometerX());
       /* if(crash==true){
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                     gameover= new GameOver();
                    if(crash==true) ((Game)Gdx.app.getApplicationListener()).setScreen(gameover);
                    crash=false;
                    ufocrash.stop();
                    ufoflying.stop();
                }
            },(float)2.5);
        }*/
        if(crash==true){
            timelapsed+=deltatime;
            if(timelapsed>0.5){
                gamescreen=new GameScreen();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
                timelapsed=0;
                gamescreen=null;
            }
        }

        if(pause==false) {
            float deltatime = Gdx.graphics.getDeltaTime();
            camera.update(5+(float)score/10, 0);
            if (shield == true) {
                timelapsed+=deltatime;
                if(timelapsed>1){
                    shield=false;
                    timelapsed=0;
                }
            }



           //  playerposition.y -= 5;
            playerposition.x += 5+(float)score/10;

            if (playerposition.x >= a) {
                camera.setloop(a - 800 / 20);
                a += 800;
            }
System.out.println("p.x"+playerposition.x+"p.y"+playerposition.y);

            if (playerposition.y < 0 - player.getHeight())
                playerposition.y = 480 - player.getHeight();
            if (playerposition.y > 480) playerposition.y = 0;
            playerbounds.setPosition(playerposition.x, playerposition.y);

            // enemy.position.x--;
            // enemy.update();
            batch.begin();


            //orhtographic camera-
            camera.draw(batch);

           if(crash) explosion.draw(batch);
            //player animation
            // timepassed += Gdx.graphics.getDeltaTime();
            //batch.draw(animation.getKeyFrame(timepassed, true), playerposition.x, playerposition.y);
            if(crash==false){
            player.draw(batch);
                player.setPosition(playerposition.x,playerposition.y);
                }
            int x1 = rand.nextInt(15);
            //Enemy Iterator
            enemyiterator = enemy.iterator();
            while (enemyiterator.hasNext()) {

               Enemy curr = enemyiterator.next();
                curr.draw(batch);
                curr.update();
                curr.position.y += (float) 0.3;
                curr.position.x -= 20 - x1;
                if (curr.bounds.overlaps(playerbounds) && !shield &&!crash) {
                    System.out.println("boooommmmm ");
                    enemyiterator.remove();
                    ufoflying.pause();
                    ufocrash.play();
                    crash=true;
                }
                if(curr.position.x<playerposition.x-100) {
                    enemyiterator.remove();
                    curr.dispose();
                }
            }

            //Bulletman iterator goes here
            bulletManIterator = bulletMans.iterator();
            while (bulletManIterator.hasNext()) {

                BulletMan bulletMan = bulletManIterator.next();
                bulletMan.draw(batch);
                bulletMan.update();
                bulletMan.position.y += (float) 0.06;
                bulletMan.position.x -= 16 - x1;
                if (bulletMan.bounds.overlaps(playerbounds) && !shield && !crash) {
                    bulletManIterator.remove();
                    ufocrash.play();
                    ufoflying.pause();
                    crash=true;
                }
                if(bulletMan.position.x<playerposition.x-100){bulletManIterator.remove();
                bulletMan.dispose();}

            }
            //bullets goes here
            bulletsiterator = bullets.iterator();
            while (bulletsiterator.hasNext()) {

                Bullets bullets = bulletsiterator.next();
                bullets.draw(batch);
                bullets.update();
                bullets.position.y += (float) 0.06;
                bullets.position.x -= 30 - x1;
                if (bullets.bounds.overlaps(playerbounds) && !shield && !crash) {
                    bulletsiterator.remove();
                    ufocrash.play();
                    ufoflying.pause();
                    crash=true;
                }
                if(bullets.position.x<playerposition.x-100){bulletsiterator.remove();
                    bullets.dispose();}

            }
            //Enemy Sprite iterator
            enemysiterator = senemy.iterator();
            while (enemysiterator.hasNext())

            {
                EnemyS2 cur = enemysiterator.next();
                cur.draw(batch);
                cur.update();

                cur.position.y -= (float) 0.8;
                cur.position.x -= 60 * deltatime;
                if (cur.getEnemysprite().getBoundingRectangle().overlaps(playerbounds) && !shield &&!crash) {
                    enemysiterator.remove();
                    ufoflying.pause();
                    ufocrash.play();
                    crash=true;
                }
               if(cur.position.x<playerposition.x-100){enemysiterator.remove();
                cur.dispose();}
            }
            //ScreenrotSprite iterates here
            screenrotIterator = screenrotators.iterator();
            while (screenrotIterator.hasNext())

            {
                ScreenrotSprite rot = screenrotIterator.next();
                rot.draw(batch);
                int direction = rand.nextInt(2);
                int movement = rand.nextInt(20);
                Boolean up = false;
                if (direction == 0) up = false;
                if (direction == 1) up = true;

                rot.position.x -= 60 * deltatime;
           /* if(rot.position.y>Gdx.graphics.getHeight()){
                while(rot.position.y>1)rot.position.y+=3;}
            if(rot.position.y<1){
                while(rot.position.y<Gdx.graphics.getHeight())rot.position.y-=3;}*/
                if (rot.position.y > Gdx.graphics.getHeight()) {
                    up = false;
                }
                if (rot.position.y <= 10) {
                    up = true;
                }
                if (up) rot.position.y += movement;
                else rot.position.y -= movement;
                rot.update();
                if (rot.bounds.overlaps(playerbounds) && !shield  && !crash) {
                    screenrotIterator.remove();
                    ufoflying.pause();
                    ufocrash.play();
                    crash=true;
                }
                if(rot.position.x<playerposition.x-100) {
                    screenrotIterator.remove();
                    rot.dispose();
                }
            }
            shieldIterator = shields.iterator();
            while (shieldIterator.hasNext()) {

                Shield shi = shieldIterator.next();
                shi.draw(batch);
                shi.update();
                shi.position.y += (float) 0.06;
                shi.position.x -= 16 - x1;

                if (shi.bounds.overlaps(playerbounds)&&!crash) {
                    shieldIterator.remove();
                    shield = true;
                }
                if(shi.position.x<playerposition.x-100) {
                    shieldIterator.remove();
                    shi.dispose();
                }

            }
            multiplierIterator =multiplier.iterator();
           while(multiplierIterator.hasNext()){
               ScoreMultiplier mul =multiplierIterator.next();
               mul.draw(batch);
               mul.update();
               mul.position.y += (float) 0.04;
               mul.position.x-= 15-x1;
               if(mul.sprite.getBoundingRectangle().overlaps(playerbounds)&&!crash) {
                   multiplierIterator.remove();
                   mfactor*=mfactor;
               }
              if(mul.position.x<playerposition.x-100) {
                  multiplierIterator.remove();
                  mul.dispose();
              }
           }
            alienIterator=randomalien.iterator();
            while(alienIterator.hasNext()){
                Randomalien ralien =alienIterator.next();
                ralien.draw(batch);
                ralien.update();
                ralien.position.y+=(float)0.1;
                ralien.position.x-=15-x1;
                if(ralien.sprite.getBoundingRectangle().overlaps(playerbounds)&&!crash&&!shield){
                    alienIterator.remove();
                }
              if(ralien.position.x<playerposition.x-100){alienIterator.remove();
                  ralien.dispose();}

            }
            if(!crash) font36.draw(batch, yourscore, playerposition.x +200,50);
            if(crash){
                if(score>highscore){
                    prefs.putInteger("HIGHSCORE",score);
                    prefs.flush();
                }
                parameter.size=50;
                parameter.color=Color.YELLOW;
                font50.draw(batch, "HIGHSCORE"+"  " +highscore, playerposition.x +100,320);
                font50.draw(batch,"GAMEOVER",playerposition.x +100,240);
                font50.draw(batch, yourscore, playerposition.x +100,160);

            }
            //font12.draw(batch,yourscore,playerposition.x-Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/2);
            if(shield){
                font50.draw(batch,"SHIELD ACTIVATED",playerposition.x +100,325);
            }
            batch.end();
        }
        stage.act(delta);
        stage.draw();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(100,100,50,50);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

        camera.viewport.update(width,height);
        camera.camera.position.set(camera.camera.viewportWidth/2,camera.camera.viewportHeight/2,0);
    }

    public void stagestuff(){
        parameter.size = 36;
        parameter.color= Color.MAGENTA;
        parameter.borderColor=Color.GOLDENROD;
        parameter.shadowColor=Color.BLUE;
        parameter.shadowOffsetX=-1;
        parameter.shadowOffsetY=-1;
        font36 = generator.generateFont(parameter);

        parameter.size=60;
        parameter.color=Color.CORAL;
        parameter.borderColor=Color.GOLDENROD;
        font50 =generator.generateFont(parameter);





        //box2d section to make a pause button
        stage = new Stage();


        //Gdx.input.setInputProcessor(this);
        atlas =new TextureAtlas("ui/atlas.pack");
        skin =new Skin(atlas);

        table =new Table(skin);
        table.setBounds(700,400,800,480);

        white= new BitmapFont(Gdx.files.internal("font/whitefont.fnt"),false);
        black= new BitmapFont(Gdx.files.internal("font/blackfont.fnt"),false);
        TextButton.TextButtonStyle textButtonStyle =new TextButton.TextButtonStyle();
        textButtonStyle.up=skin.getDrawable("button.up");
        textButtonStyle.down=skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX=-1;
        textButtonStyle.pressedOffsetY=-1;
        textButtonStyle.font=white;

        Pause =new TextButton("PAUSE",textButtonStyle);
        Pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause = !pause;
            }
        });
        Pause.pad(5);
        Pause.setColor(Color.ROYAL);

        sound =new TextButton("SOUND",textButtonStyle);
        sound.addListener(new ClickListener() {


            @Override
            public void clicked(InputEvent event, float x, float y) {

                ufoflying.stop();
                ufocrash.stop();



            }
        });
        sound.pad(5);
        sound.setColor(Color.CHARTREUSE);

        table.add(Pause).spaceBottom(10);
        table.row();
        table.add(sound).spaceBottom(10);
        //table.debug();
        stage.addActor(table);

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
        gamescreen.dispose();
        font36.dispose();
        font50.dispose();
        camera.dispose();
        playerTexture.dispose();
        generator.dispose();
        backgroundtexture.dispose();
        batch.dispose();
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        while(bulletManIterator.hasNext())
        bulletManIterator.next().dispose();
        while(enemyiterator.hasNext())
        enemyiterator.next().dispose();
        while(enemysiterator.hasNext())
        enemysiterator.next().dispose();
        while(screenrotIterator.hasNext())
        screenrotIterator.next().dispose();
        while(bulletsiterator.hasNext())
        bulletsiterator.next().dispose();
        while(bulletManIterator.hasNext())
        bulletManIterator.next().dispose();
        while(shieldIterator.hasNext())
        shieldIterator.next().dispose();
        while(multiplierIterator.hasNext())
        multiplierIterator.next().dispose();
        while(alienIterator.hasNext())
        alienIterator.next().dispose();
        explosion.dispose();
        white.dispose();
        black.dispose();
        ufocrash.dispose();
        ufoflying.dispose();
        camera.texture.dispose();


    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      //  playerposition.y+=150;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      //  playerposition.y+=30;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
      //  playerposition.x++;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

}

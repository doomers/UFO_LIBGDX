package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    SpriteBatch batch;
    Vector2 position;
    Rectangle bounds;
    Texture enemytexture;
    TextureAtlas rubberAtlas;
    private Animation animation;
    private float timepassed;

   //Random rand =new Random();
   // int a=rand.nextInt(0);

   public  Enemy(Vector2 position){
   this.position=position;

           enemytexture=new Texture("Atlas/rotatoryatlas/enem2.png");
           rubberAtlas= new TextureAtlas(Gdx.files.internal("Atlas/rotatoryatlas/rotatingrings.pack"),true);
           animation =new Animation(1/60f,rubberAtlas.getRegions());


       bounds =new Rectangle(position.x,position.y,enemytexture.getWidth()/3,enemytexture.getHeight()/2);

   }
    public void update(){
    bounds.setPosition(position.x, position.y);
    }
    public void draw(SpriteBatch batch){
        timepassed+= Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timepassed,true),position.x,position.y);


       }


     public Texture getTexture(){
        return enemytexture;
     }
    public void dispose(){
        enemytexture.dispose();
        rubberAtlas.dispose();
    }

}

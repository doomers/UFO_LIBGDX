package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rahul on 12/27/2015.
 */
public class ScreenrotSprite {
    public Vector2 position;
    public Texture scrtexture;
    public TextureAtlas screenrotAtlas;
    public Animation animation;
    public float timepassed;
    public Rectangle bounds;




    public ScreenrotSprite(Vector2 position){
        this.position=position;
        scrtexture= new Texture("Atlas/circle_animations/enemy.png");
        screenrotAtlas= new TextureAtlas(Gdx.files.internal("Atlas/circle_animations/circle.pack"));
        animation =new Animation(1/3f,screenrotAtlas.getRegions());
        bounds =new Rectangle(position.x,position.y,scrtexture.getWidth(),scrtexture.getHeight());

    }
    public void update(){
        bounds.setPosition(position.x,position.y);
    }
    public void draw(SpriteBatch batch){
        timepassed+= Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timepassed,true),position.x,position.y);
    }
    public void dispose(){
        scrtexture.dispose();
        screenrotAtlas.dispose();
    }



}

package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rahul on 12/26/2015.
 */
public class Bullets {
    public Vector2 position;
    public Texture bulletexture;
    public Sprite bullets;
    public Rectangle bounds;
    public Bullets(Vector2 position){
        this.position=position;
        bulletexture=new Texture("Enimies/btstarblue.png");
        bullets =new Sprite(bulletexture);
        bounds=new Rectangle(position.x,position.y,bulletexture.getWidth(),bulletexture.getHeight());
    }
    public void update(){
        bounds.setPosition(position.x,position.y);
        bullets.setSize(position.x,position.y);
    }
    public void draw(SpriteBatch batch){
        bullets.draw(batch);
    }
    public void dispose(){
        bulletexture.dispose();
    }
}

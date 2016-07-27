package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rahul on 12/26/2015.
 */
public class BulletMan {
    public Vector2 position;
    public Texture bulletmanTexture;
    public Sprite bulletman;
    public Rectangle bounds;
    public BulletMan(Vector2 position){
        this.position=position;
        bulletmanTexture= new Texture("Enimies/bulletman1.png");
        bulletman = new Sprite(bulletmanTexture);
        bulletman.setSize(100,80);
        bounds=new Rectangle(position.x,position.y,bulletmanTexture.getWidth(),bulletmanTexture.getHeight());

    }
    public void update(){
        bounds.setPosition(position.x,position.y);
        bulletman.setPosition(position.x,position.y);
    }
    public void draw(SpriteBatch batch){
        bulletman.draw(batch);
    }
    public void dispose(){
        bulletmanTexture.dispose();    }

}

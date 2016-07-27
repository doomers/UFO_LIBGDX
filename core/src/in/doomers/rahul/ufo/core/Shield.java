package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rahul on 12/28/2015.
 */
public class Shield {
    public Vector2 position;
    public Texture texture;
    public Sprite shield;

    public Rectangle bounds;
    public Shield(Vector2 position){
        this.position=position;
        texture = new Texture("Shield/shield.png");
        shield = new Sprite(texture);
        shield.setSize(150,100);
        bounds=new Rectangle(position.x,position.y, texture.getWidth(), texture.getHeight());

    }
    public void update(){
        bounds.setPosition(position.x,position.y);
        shield.setPosition(position.x,position.y);
    }
    public void draw(SpriteBatch batch){
        shield.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }


}

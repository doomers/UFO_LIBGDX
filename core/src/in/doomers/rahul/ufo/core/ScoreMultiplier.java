package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Rahul on 12/28/2015.
 */
public class ScoreMultiplier {
    public Texture texture;
    public Sprite sprite;
    public Vector2 position;
    public int rotation;
    Random rand =new Random();
    public ScoreMultiplier(Vector2 position){
        this.position=position;
        texture= new Texture("x2/2x.png");
        sprite= new Sprite(texture);
        sprite.setSize(120,120);
    }
    public void update(){
        int a= rand.nextInt(10);
        rotation=rand.nextInt(30);
        sprite.setPosition(position.x-a,position.y+a);
        sprite.setRotation(rotation);

    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }
}

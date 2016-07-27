package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Rahul on 12/30/2015.
 */
public class Randomalien {
    public Texture texture;
    public Sprite sprite;
    public Vector2 position;
    public int rotation;
    Random rand =new Random();
    public Randomalien(Vector2 position){
        this.position=position;
        texture= new Texture("Enimies/randomalien.png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region =
                new TextureRegion(texture, 0, 0, 200, 200);
        sprite= new Sprite(region);

        sprite.setSize(70,100);


    }
    public void update(){
        int a= rand.nextInt(10);
        rotation=rand.nextInt(30);
        sprite.setPosition(position.x-a,position.y+a);
        sprite.setRotation(rotation);
        //sprite.setSize(Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/8);
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }
}

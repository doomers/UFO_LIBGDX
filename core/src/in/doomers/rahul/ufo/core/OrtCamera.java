package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Rahul on 11/25/2015.
 */
public class OrtCamera {
    SpriteBatch batch;
    Texture texture;
    Sprite backgroundsprite;

    OrthographicCamera camera;
    Viewport viewport;
    public OrtCamera(Texture texture){
        this.texture=texture;
        backgroundsprite =new Sprite(texture);

        camera =new OrthographicCamera();
        viewport = new FillViewport(800,480,camera);

        backgroundsprite.setSize(1600,480);

        viewport.apply();
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
        camera.update();
    }
    public void  update(float x,float y){
        camera.translate(x,y);
        camera.update();
    }
    public void setloop(float x){
        backgroundsprite.setPosition(x, 0);
    }
    public void draw(SpriteBatch batch){
        this.batch=batch;
        batch.setProjectionMatrix(camera.combined);
        backgroundsprite.draw(batch);
    }
    public void dispose(){
        texture.dispose();
    }

}

package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Rahul on 12/26/2015.
 */
public class EnemyS2  {


    public Vector2 position;
    private Texture enemyTexture;
    private Sprite enemysprite;

    private int rot=100;
    Random rand =new Random();


        public EnemyS2(Vector2 position) {
         int a = rand.nextInt(3);
         this.position = position;
         if (a == 0) enemyTexture = new Texture("Enimies/senemy.png");
         else if (a == 1) enemyTexture = new Texture("Enimies/enemy1.png");
         else if (a == 2) enemyTexture = new Texture("Enimies/enemyufo.png");

            /*TextureRegion textureRegion =new TextureRegion(enemyTexture,position.x,position.y,enemyTexture.getWidth(),enemyTexture.getHeight());
*/
         enemysprite = new Sprite(enemyTexture);
            enemysprite.setSize(150,150);
       }


      public Sprite getEnemysprite() {
        return enemysprite;
      }

    public void update(){
        enemysprite.setPosition(position.x,position.y);
        enemysprite.setRotation(rot+=4);
       }
    public void draw(SpriteBatch batch){
        enemysprite.draw(batch);

    }
    public void dispose(){
        enemyTexture.dispose();
    }


}

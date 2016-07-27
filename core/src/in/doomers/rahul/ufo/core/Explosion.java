package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Explosion {
    public Vector2 position;
    private static final int        FRAME_COLS = 5;         // #1
    private static final int        FRAME_ROWS = 5;         // #2

    Animation walkAnimation;          // #3
    Texture walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5

    TextureRegion                   currentFrame;           // #7

    float stateTime;                                        // #8


    public Explosion(Vector2 position){
        this.position=position;
        walkSheet = new Texture(Gdx.files.internal("explosion/explosion.png")); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.11f, walkFrames);      // #11

        stateTime = 0f;

    }
public void draw(SpriteBatch batch){
    stateTime += Gdx.graphics.getDeltaTime();           // #15
    currentFrame = walkAnimation.getKeyFrame(stateTime, true);  // #16
    batch.draw(currentFrame, position.x, position.y);             // #17

}
    public void dispose(){
        walkSheet.dispose();

    }
}

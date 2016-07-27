package in.doomers.rahul.ufo.core;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Rahul on 12/30/2015.
 */
public class ActorAccesor implements TweenAccessor<Actor> {
    public static final int ALPHA=1;


    @Override
    public int getValues(Actor target, int tweenType, float[] returnValues) {
        switch (tweenType){
            case ALPHA:
                returnValues[0]=target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor target, int tweenType, float[] newValues) {
        switch(tweenType){
            case ALPHA:
                target.setColor(target.getColor().r,target.getColor().g,target.getColor().b,newValues[0]);
                break;
            default:
                assert false;
        }
    }
}

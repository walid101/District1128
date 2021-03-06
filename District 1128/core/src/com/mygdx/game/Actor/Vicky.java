package com.mygdx.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Vicky {
    public Rectangle full;
    public Sprite sprite;
    public Texture texture;
    public final float velocity=100f;
    public Animation<TextureRegion> leftWalk;
    public Animation<TextureRegion> rightWalk;
    public Animation<TextureRegion> leftRun;
    public Animation<TextureRegion> rightRun;
    public int actionCode;

    public Vicky(float x,float y)
    {
        full=new Rectangle(x,y,41,112);
        texture=new Texture(Gdx.files.internal("Vicky/Vicky.png"));
        sprite=new Sprite(texture,0,0,128,128);
        actionCode=0;

        //Making animations here
        TextureAtlas leftWalkAtlas=new TextureAtlas(Gdx.files.internal("Vicky/VickyLeft.atlas"));
        leftWalk=new Animation<TextureRegion>(1/6f,leftWalkAtlas.findRegions("V"), Animation.PlayMode.LOOP);

        TextureAtlas rightWalkAtlas=new TextureAtlas(Gdx.files.internal("Vicky/VickyRight.atlas"));
        rightWalk=new Animation<TextureRegion>(1/6f,rightWalkAtlas.findRegions("V"), Animation.PlayMode.LOOP);

        setPosition(x,y);
    }

    public int hit(Rectangle anotherR)
    {
        if(full.overlaps(anotherR))
        {
            return 1;
        }

        return -1;
    }

    public void draw(SpriteBatch batch)
    {
        batch.draw(sprite.getTexture(),full.x,full.y);

        setActionCode(0);
    }

    public void setPosition(float x,float y)
    {
        full.x=x;
        full.y=y;
        sprite.setPosition(x,y);
    }

    public Rectangle getHitBox()
    {
        return full;
    }

    public void moveLeft(float delta)
    {
        full.x-=velocity*delta;

        setPosition(full.x,full.y);

        setActionCode(-1);
    }

    public void moveRight(float delta)
    {
        full.x+=velocity*delta;

        setPosition(full.x,full.y);

        setActionCode(1);
    }

    public void drawLeftWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(leftWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public void drawRightWalkAnimation(float stateTime,SpriteBatch batch)
    {
        batch.draw(rightWalk.getKeyFrame(stateTime),full.x,full.y);
    }

    public int getActionCode()
    {
        return actionCode;
    }

    public void setActionCode(int code)
    {
        actionCode=code;
    }
}

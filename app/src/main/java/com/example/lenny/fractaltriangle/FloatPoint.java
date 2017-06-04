package com.example.lenny.fractaltriangle;

/**
 * Created by lenny on 6/4/17.
 */

public class FloatPoint {
    float x;
    float y;

    FloatPoint(float x,float y){
        this.x = x;
        this.y = y;
    }
    FloatPoint(FloatPoint fp){
        this.x = fp.x;
        this.y = fp.y;
    }


}

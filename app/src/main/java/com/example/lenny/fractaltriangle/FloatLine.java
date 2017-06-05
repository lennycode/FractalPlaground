package com.example.lenny.fractaltriangle;

/**
 * Created by lenny on 6/4/17.
 */

public class FloatLine {
    FloatPoint start;
    FloatPoint end;
    FloatLine fx;

    FloatLine(FloatPoint start, FloatPoint end){
        this.start = start;
        this.end = end;
    }


    public FloatPoint midPoint(){
        return new FloatPoint((start.x+end.x)/2,(start.y+end.y)/2 );
    }
}

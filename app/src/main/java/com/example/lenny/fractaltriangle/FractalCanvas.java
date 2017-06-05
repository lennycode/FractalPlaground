package com.example.lenny.fractaltriangle;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by lenny on 6/4/17.
 */

public class FractalCanvas extends View {

    Paint paint;
    Path path;


    public FractalCanvas(Context context) {
        super(context);
        init();
    }

    public FractalCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FractalCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        inscribeTriangle(canvas, new FloatPoint(Utilities.convertDpToPixel(10f, this.getContext()), Utilities.convertDpToPixel(10f, this.getContext())), Utilities.convertDpToPixel(800f, this.getContext()), 4);
    }

    void inscribeTriangle(Canvas canvas, FloatPoint origin, float size, int iterations) {
        //Construct Equilateral Triangle
        FloatPoint vertex1 = (origin);
        FloatPoint vertex2 = new FloatPoint(origin.x + size, origin.y);
        FloatPoint vertex3 = new FloatPoint(origin.x + size / 2, origin.y + (size * (float) Math.sin(3.141592f / 3f)));
        ArrayList<FloatLine> floatLines = new ArrayList<FloatLine>();
        Triangle tx = new Triangle(new FloatLine(vertex1, vertex2), new FloatLine(vertex2, vertex3), new FloatLine(vertex3, vertex1));
        fractalTriangle(canvas, vertex1, vertex2, vertex3, iterations);


    }

    //Alternate Triangle Renderer.
    void renderTriangle(Canvas canvas, ArrayList<FloatLine> triangle) {
        for (FloatLine tl : triangle) {
            canvas.drawLine(tl.start.x, tl.start.y, tl.end.x, tl.end.y, paint);

        }
    }

    void renderTriangle(Canvas canvas, FloatPoint a, FloatPoint b, FloatPoint c) {
        canvas.drawLine(a.x, a.y, b.x, b.y, paint);
        canvas.drawLine(b.x, b.y, c.x, c.y, paint);
        canvas.drawLine(c.x, c.y, a.x, a.y, paint);
    }


    void fractalTriangle(Canvas canvas, FloatPoint p1, FloatPoint p2, FloatPoint p3, int iterations) {
        FloatPoint mp1 = new FloatLine(p1, p2).midPoint();
        FloatPoint mp2 = new FloatLine(p2, p3).midPoint();
        FloatPoint mp3 = new FloatLine(p3, p1).midPoint();
        if (iterations == 1) {
            renderTriangle(canvas, p1, mp1, mp3);
            renderTriangle(canvas, p2, mp2, mp1);
            renderTriangle(canvas, p3, mp3, mp2);
        } else {
//Which do you want?

//            fractalTriangle(canvas,p1,mp1, mp3, iterations - 1);
//            fractalTriangle(canvas, p2, mp2, mp1, iterations - 1);
//            fractalTriangle( canvas , p3, mp3, mp2, iterations - 1);

            fractalTriangle(canvas, p1, mp1, mp3, iterations - 1);
            fractalTriangle(canvas, p2, mp2, mp3, iterations - 1);
            fractalTriangle(canvas, p3, mp3, mp2, iterations - 1);

//            fractalTriangle(canvas,p1,mp1, mp1, iterations - 1);
//            fractalTriangle(canvas, p2, mp2, mp3, iterations - 1);
//            fractalTriangle( canvas , p3, mp3, mp2, iterations - 1);
        }
    }


    /*
    Alternate way of doing things, not used for now.
     */
    void fractalTriangle(Canvas canvas, Triangle tx, int iterations) {

        if (iterations == 1) {
            renderTriangle(canvas, tx.l1.start, tx.l1.midPoint(), tx.l1.midPoint());
            renderTriangle(canvas, tx.l2.start, tx.l2.midPoint(), tx.l2.midPoint());
            renderTriangle(canvas, tx.l3.start, tx.l3.midPoint(), tx.l3.midPoint());

//            renderTriangle(canvas, tx.l1.start, tx.l2.start, tx.l3.end);
//            renderTriangle(canvas, tx.l2.start, tx.l2.midPoint(), tx.l2.midPoint());
//            renderTriangle(canvas, tx.l3.start, tx.l3.midPoint(), tx.l3.midPoint());

        } else {
            fractalTriangle(canvas, new Triangle(new FloatLine(tx.l1.start, tx.l1.end),
                    new FloatLine(tx.l1.midPoint(), tx.l2.midPoint()),
                    new FloatLine(tx.l3.midPoint(), tx.l1.midPoint())), iterations - 1);

            fractalTriangle(canvas, new Triangle(new FloatLine(tx.l2.start, tx.l2.end),
                    new FloatLine(tx.l2.midPoint(), tx.l3.midPoint()),
                    new FloatLine(tx.l3.midPoint(), tx.l1.midPoint())), iterations - 1);

            fractalTriangle(canvas, new Triangle(new FloatLine(tx.l3.start, tx.l3.end),
                    new FloatLine(tx.l3.midPoint(), tx.l1.midPoint()),
                    new FloatLine(tx.l1.midPoint(), tx.l2.midPoint())), iterations - 1);
        }


    }


}

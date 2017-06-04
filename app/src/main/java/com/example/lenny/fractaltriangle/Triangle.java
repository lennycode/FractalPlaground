package com.example.lenny.fractaltriangle;

import java.util.ArrayList;

/**
 * Created by lenny on 6/4/17.
 */



public class Triangle {
    public FloatLine l1;
    public FloatLine l2;
    public FloatLine l3;
    public ArrayList <FloatLine> triList = new ArrayList<>();

    public Triangle(FloatLine l1, FloatLine l2, FloatLine l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        triList.add(l1);
        triList.add(l2);
        triList.add(l3);
    }


}

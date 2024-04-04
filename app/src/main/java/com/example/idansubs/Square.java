package com.example.idansubs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Square {
    float x, y, w, h;//top left
    Paint p;
    boolean isClicked;
    boolean doesShow=false;
    boolean isSub=false;
    public Square(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        p = new Paint();
        this.w = w;
        this.h = h;
    }
    public void changeVisi(boolean b)
    {
        doesShow=b;
    }
    public void drawStroke(Canvas canvas) {
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4);
        p.setColor(Color.BLACK);
        canvas.drawRect(x, y, x + w, y + h, p);
    }
    public void setSub()
    {
        isSub=true;
    }
    public boolean GetIsSub()
    {
        return isSub;
    }
    public boolean GetdoesShow()
    {
        return doesShow;
    }
    public void setIsClicked()
    {
        isClicked=true;
    }
    public boolean GetIsClicked()
    {
        return isClicked;
    }
    public void draw(Canvas canvas) {
        p.setStyle(Paint.Style.FILL);
        if(isClicked==true)
        {
            if(isSub)p.setColor(Color.argb(250,147,112,219));
            else p.setColor(Color.BLUE);}
        else if(isSub==true&&doesShow==true) p.setColor(Color.RED);
        else p.setColor(Color.GREEN);
        canvas.drawRect(x, y, x + w, y + h, p);
        drawStroke(canvas);
    }
}
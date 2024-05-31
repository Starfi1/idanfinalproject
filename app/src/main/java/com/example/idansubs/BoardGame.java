package com.example.idansubs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class BoardGame extends View {
    final int size = 10;
    Square[][] squaresForDisplay;
    Context context;
    Square[][] squaresForChanges;
    int w;
    int h;
    int tryCounter=0;
    int hitCounter=0;
    public BoardGame(Context context) {
        super(context);
        this.context = context;
        squaresForDisplay = new Square[size][size];
        squaresForChanges = new Square[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squaresForChanges[i][j] = new Square(1, 1, 4, 4); ////x and y don't matter

            }
        }
        ////Rect submarine:
        int random1 = new Random().nextInt(9);
        int random2 = new Random().nextInt(9);
        for (int i = random1; i <= random1 + 1; i++) {
            for (int j = random2; j <= random2 + 1; j++) {
                squaresForChanges[i][j].setSub();
            }
        }



        ////column sub
        random1 = new Random().nextInt(7);
        random2 = new Random().nextInt(10);
        while (CheckColumn(random1, random2) == true) {
            random1 = new Random().nextInt(7);
            random2 = new Random().nextInt(10);
        }
        for (int j = random1; j < random1 + 4; j++) {
            squaresForChanges[j][random2].setSub();
        }


        //////Line sub
        random1 = new Random().nextInt(10);
        random2 = new Random().nextInt(7);
        while (CheckLine(random1, random2) == true) {
            random1 = new Random().nextInt(10);
            random2 = new Random().nextInt(7);
        }
        for (int j = random2; j < random2 + 4; j++) {
            squaresForChanges[random1][j].setSub();
        }

    }
    public int getTryCounter(){
        return tryCounter;
    }
    public int getHitCounter(){
        return hitCounter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        h = canvas.getWidth() / size;
        w = canvas.getWidth() / size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squaresForDisplay[i][j] = new Square(j * w, i * h, w, h);
            }
        }
        drawBoard(canvas);
        Paint brush=new Paint();
        brush.setColor(Color.BLACK);
        brush.setTextAlign(Paint.Align.CENTER);
        brush.setTextSize(70);
        canvas.drawText("Your tries:",canvas.getWidth()/2,canvas.getHeight()-120,brush);
        String tempText=Integer.toString(tryCounter);
        canvas.drawText(tempText,canvas.getWidth()/2,canvas.getHeight()-30,brush);
        canvas.drawText("Your hits:",canvas.getWidth()/2,canvas.getHeight()-270,brush);
        tempText=Integer.toString(hitCounter);
        canvas.drawText(tempText,canvas.getWidth()/2,canvas.getHeight()-185,brush);

        brush=new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
        brush.setColor(Color.BLACK);
        brush.setTextAlign(Paint.Align.CENTER);
        brush.setTextSize(180);
       
        if(hitCounter==12)
        {
            canvas.drawText("You've won",canvas.getWidth()/2,canvas.getHeight()-450,brush);
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(hitCounter!=12) {
                int Xsquarepos = (int) Math.floor(event.getX() / (double) w);
                int Ysquarepos = (int) Math.floor(event.getY() / (double) h);
                if (squaresForChanges[Ysquarepos][Xsquarepos].GetIsClicked() == false) tryCounter++;
                if (squaresForChanges[Ysquarepos][Xsquarepos].GetIsSub() == true&&squaresForChanges[Ysquarepos][Xsquarepos].GetIsClicked() == false){ hitCounter++; }
                squaresForChanges[Ysquarepos][Xsquarepos].setIsClicked();
                invalidate();
            }
        }
        return true;
    }

    public boolean CheckLine(int random1, int random2) {
        boolean check=false;
        for (int j = random2; j < random2 + 4; j++) {
            if (squaresForChanges[random1][j].GetIsSub()==true)check= true;
        }
        return check;
    }
    public boolean CheckColumn(int random1, int random2) {
        boolean check=false;
        for (int j = random1; j < random1 + 4; j++) {
            if(squaresForChanges[j][random2].GetIsSub()==true)check= true;
        }
        return check;
    }

    public void drawBoard(Canvas canvas) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(squaresForChanges[i][j].GetIsSub()==true)
                    squaresForDisplay[i][j].setSub();
                if(squaresForChanges[i][j].GetIsClicked()==true)
                    squaresForDisplay[i][j].setIsClicked();
                squaresForDisplay[i][j].draw(canvas);

            }
        }
    }}
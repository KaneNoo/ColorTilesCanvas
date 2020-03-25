package com.example.colortilescanvas;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Tile {

    Paint paint = new Paint();

    int color;
    float x, y;
    final float width = 200;
    final float height = 300;


    public Tile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Tile() {
    }

    public void draw(Canvas canvas){

        paint.setColor(color);
        canvas.drawRect(x, y, x + width, y + height, paint);

    }


}

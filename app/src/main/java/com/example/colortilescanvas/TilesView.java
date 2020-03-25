package com.example.colortilescanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;


public class TilesView extends View {

    static Tile[][] tiles = new Tile[4][4];
    final int darkColor = Color.GRAY;
    final int brightColor = Color.YELLOW;
    final int distance = 50;



    public TilesView(Context context) {
        super(context);
    }

    public TilesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Random random = new Random();

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){

                //tiles[i][j] = new Tile(j*tiles[i][j].width + j*distance, i*tiles[i][j].height + i*distance);
                tiles[i][j] = new Tile();
                tiles[i][j].x = j*tiles[i][j].width + j*distance;
                tiles[i][j].y = i*tiles[i][j].height + i*distance;

                if(random.nextFloat() <= 0.5){
                    tiles[i][j].color = darkColor;
                }
                else{
                    tiles[i][j].color = brightColor;
                }

            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){

                tiles[i][j].draw(canvas);

            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int touchX = (int) event.getX();
        int touchY = (int) event.getY();


        if(event.getAction() == MotionEvent.ACTION_DOWN){

            int[] touchedTile = getTouchedTile(touchX, touchY);

            for (int i = 0; i < tiles.length; i++){
                changeColor(tiles[touchedTile[0]][i]);
                changeColor(tiles[i][touchedTile[1]]);
            }
            changeColor(tiles[touchedTile[0]][touchedTile[1]]);


            if(gameOver()){
                Toast toast = Toast.makeText(getContext(), "Congratulations!", Toast.LENGTH_LONG);
                toast.show();
            }




        }

        invalidate();
        return true;
    }


    public int[] getTouchedTile(int touchX, int touchY){

        int[] touchedTile = new int[2];

        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){

                if (touchX >= tiles[i][j].x && touchX <= (tiles[i][j].x + tiles[i][j].width) &&
                        touchY >= tiles[i][j].y && touchY <= (tiles[i][j].y + tiles[i][j].height)){

                    touchedTile[0] = i;
                    touchedTile[1] = j;
                    break;
                }

            }
        }

        return touchedTile;
    }

    public void changeColor(Tile tile){

        if(tile.color == darkColor) tile.color = brightColor;
        else tile.color = darkColor;

    }


    public boolean gameOver(){

        int darkColorsCount = 0;
        int brightColorsCount = 0;

        for(int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                    if(tiles[i][j].color == darkColor) darkColorsCount++;
                    if(tiles[i][j].color == brightColor) brightColorsCount++;
            }
        }

        if(darkColorsCount == tiles.length*tiles[0].length) return true;
        if(brightColorsCount == tiles.length*tiles[0].length) return true;

        return false;
    }


}

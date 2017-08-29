package com.kallumchumber.memorytestgame;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Handler;

public class Game2 extends AppCompatActivity implements View.OnClickListener{

    private int numberOfElements;
    private MemoryButton[]buttons;
    private int[] buttonLocations;
    private int[] buttonGraphics;
    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;
    private boolean isBusy = false;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);


        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout_game2);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];

        buttonGraphics = new int[numberOfElements/2];

        buttonGraphics[0] = R.drawable.button1;
        buttonGraphics[1] = R.drawable.button2;
        buttonGraphics[2] = R.drawable.button3;
        buttonGraphics[3] = R.drawable.button4;
        buttonGraphics[4] = R.drawable.button5;
        buttonGraphics[5] = R.drawable.button6;
        buttonGraphics[6] = R.drawable.button7;
        buttonGraphics[7] = R.drawable.button8;
        buttonGraphics[8] = R.drawable.button9;
        buttonGraphics[9] = R.drawable.button10;
        buttonGraphics[10] = R.drawable.button11;
        buttonGraphics[11] = R.drawable.button12;
        buttonGraphics[12] = R.drawable.button13;
        buttonGraphics[13] = R.drawable.button14;
        buttonGraphics[14] = R.drawable.button15;
        buttonGraphics[15] = R.drawable.button16;
        buttonGraphics[16] = R.drawable.button17;
        buttonGraphics[17] = R.drawable.button18;


        buttonLocations = new int[numberOfElements];

        shuffleButtonGraphics();

        for(int r=0; r< numRows; r++)
        {
            for(int c =0; c< numColumns; c++)
            {
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonLocations[r* numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r* numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }

    }

    protected void  shuffleButtonGraphics()
    {
        Random rand = new Random();

        for(int i=0; i < numberOfElements; i++)
        {
            buttonLocations[i] = i % (numberOfElements / 2);
        }
        for (int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonLocations[i];

            int swapIndex = rand.nextInt(36);

            buttonLocations[i] = buttonLocations[swapIndex];

            buttonLocations[swapIndex] = temp;
        }


    }


    @Override
    public void onClick(View view) {

        if(isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

        if(button.isMatched)
            return;

        if(selectedButton1 == null)
        {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }

        if(selectedButton1.getId() == button.getId())
        {
            return;
        }

        score=score+1;
        {
            Toast.makeText(getApplicationContext(),"score = "+score, Toast.LENGTH_LONG).show();
        }
        if(selectedButton1.getFrontDrawableId() == button.getFrontDrawableId())
        {
            button.flip();

            button.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1 = null;

            return;
        }
        else
        {
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy=true;

            final android.os.Handler handler = new android.os.Handler();

            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy=false;

                }
            } ,500);
        }


    }

}





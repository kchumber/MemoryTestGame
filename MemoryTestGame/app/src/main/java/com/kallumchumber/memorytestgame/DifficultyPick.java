package com.kallumchumber.memorytestgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultyPick extends AppCompatActivity {

    private Button buttonplayeasy;
    private Button buttonplayhard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_pick);

        buttonplayeasy = (Button)findViewById(R.id.button_play_easy);

        buttonplayeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyPick.this,Game.class);
                startActivity(intent);

            }
        });

        buttonplayhard = (Button)findViewById(R.id.button_play_hard);

        buttonplayhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyPick.this,Game2.class);
                startActivity(intent);
            }
        });

    }
}

package com.example.fengxinlin.nanodegreep2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int teamA_score = 0;
    int teamB_score = 0;
    int teamA_foul = 0;
    int teamB_foul = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_score_A = (Button) findViewById(R.id.goal_btn1);
        final Button btn_score_B = (Button) findViewById(R.id.goal_btn2);
        final Button btn_foul_A = (Button) findViewById(R.id.foul_btn1);
        final Button btn_foul_B = (Button) findViewById(R.id.foul_btn2);
        final Button btn_reset = (Button) findViewById(R.id.reset);

        btn_score_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamA_score += 1;
                displayScoreA(teamA_score);
            }
        });
        btn_score_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamB_score += 1;
                displayScoreB(teamB_score);
            }
        });
        btn_foul_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamA_foul += 1;
                displayFoulA(teamA_foul);
            }
        });
        btn_foul_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamB_foul += 1;
                displayFoulB(teamB_foul);
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScore(view);
            }
        });
    }



    public void displayScoreA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayScoreB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayFoulA(int foul) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_foul);
        scoreView.setText(String.valueOf(foul));
    }

    public void displayFoulB(int foul) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_foul);
        scoreView.setText(String.valueOf(foul));
    }

    public void resetScore(View v) {
        teamA_score = 0;
        teamA_foul = 0;
        teamB_score = 0;
        teamB_foul = 0;
        displayScoreA(teamA_score);
        displayScoreB(teamB_score);
        displayFoulA(teamA_foul);
        displayFoulB(teamB_foul);
    }
}

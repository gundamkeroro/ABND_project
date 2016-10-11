package com.example.fengxinlin.nanodegreep3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText_p1;
    private RadioGroup radioGroup_p2;
    private RadioGroup radioGroup_p3;
    private RadioGroup radioGroup_p5;
    private CheckBox checkBox_p4_1;
    private CheckBox checkBox_p4_2;
    private CheckBox checkBox_p4_3;
    private CheckBox checkBox_p4_4;


    private boolean is_p1_scored;
    private boolean is_p2_scored;
    private boolean is_p3_scored;
    private boolean is_p4_scored;
    private boolean is_p5_scored;

    private Button resultButton;
    private Button resetButton;

    Context context;

    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.layout);
        initViews(view);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextQuestions();
                RadioGroupListeners();
                checkboxQuestions();
                Toast.makeText(context, "Your total score is: " + score, Toast.LENGTH_SHORT).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initText();
                initialCheckedBox();
                initialRadiobutton();
                initViews(view);
            }
        });
    }

    //initialize
    private void initViews(View view) {
        score = 0;
        context = view.getContext();

        editText_p1 = (EditText) findViewById(R.id.p1);
        radioGroup_p2 = (RadioGroup) findViewById(R.id.p2);
        radioGroup_p3 = (RadioGroup) findViewById(R.id.p3);
        radioGroup_p5 = (RadioGroup) findViewById(R.id.p5);
        checkBox_p4_1 = (CheckBox) findViewById(R.id.problem4_c1);
        checkBox_p4_2 = (CheckBox) findViewById(R.id.problem4_c2);
        checkBox_p4_3 = (CheckBox) findViewById(R.id.problem4_c3);
        checkBox_p4_4 = (CheckBox) findViewById(R.id.problem4_c4);
        resetButton = (Button) findViewById(R.id.reset);
        resultButton = (Button) findViewById(R.id.result);

        is_p1_scored  = false;
        is_p2_scored  = false;
        is_p3_scored  = false;
        is_p4_scored  = false;
        is_p5_scored  = false;

    }

    private void initText() {
        editText_p1.setText("");
    }

    private void initialRadiobutton() {
        radioGroup_p2.clearCheck();
        radioGroup_p3.clearCheck();
        radioGroup_p5.clearCheck();
    }

    private void initialCheckedBox() {
        if (checkBox_p4_1.isChecked()) {
            checkBox_p4_1.toggle();
        }
        if (checkBox_p4_2.isChecked()) {
            checkBox_p4_2.toggle();
        }
        if (checkBox_p4_3.isChecked()) {
            checkBox_p4_3.toggle();
        }
        if (checkBox_p4_4.isChecked()) {
            checkBox_p4_4.toggle();
        }
    }
    //this method for P1:
    private void edittextQuestions() {
        String edittext_p1 = editText_p1.getText().toString().toLowerCase();


        if (!is_p1_scored) {
            if (edittext_p1 != null && !edittext_p1.equals("")) {
                String answer = getResources().getString(R.string.answer1).toLowerCase();
                if (edittext_p1.equals(answer)) {
                    score++;
                    is_p1_scored = true;
                }
            }
        }
    }
    //this method initialize P2, P3, P5
    private void RadioGroupListeners() {
        radioGroup_p2.setOnCheckedChangeListener(mRadioGroupListener);
        radioGroup_p3.setOnCheckedChangeListener(mRadioGroupListener);
        radioGroup_p5.setOnCheckedChangeListener(mRadioGroupListener);
    }

    private RadioGroup.OnCheckedChangeListener mRadioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.problem2_r1:
                    if (!is_p2_scored) {
                        score += 1;
                        is_p2_scored = true;
                        break;
                    } else {
                        break;
                    }
                case R.id.problem3_r2:
                    if (!is_p3_scored) {
                        score += 1;
                        is_p3_scored = true;
                    }
                    break;
                case R.id.problem5_r1:
                    if (!is_p5_scored) {
                        score += 1;
                        is_p5_scored = true;
                    }
                    break;
            }
        }
    };


    //this method is for Q4:
    private void checkboxQuestions() {
        if ((!checkBox_p4_1.isChecked() &&
                !checkBox_p4_2.isChecked() &&
                checkBox_p4_3.isChecked() &&
                checkBox_p4_4.isChecked() &&
                !is_p4_scored)) {
            score++;
            is_p4_scored = true;
        }
    }
}

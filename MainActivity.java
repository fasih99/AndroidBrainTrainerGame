package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    int locationOfCorrect;
    TextView result;
    int correct=0;
    int questions=0;
    TextView score;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timer;
    TextView playAgain;
    ConstraintLayout gamelayout;

    public void start(View view)
    {
        goButton.setVisibility(view.INVISIBLE);
        playAgain(findViewById(R.id.timer));
        gamelayout.setVisibility(View.VISIBLE);
    }

    public void answer(View view)
    {

        Log.i("Tag", ""+view.getTag().toString());

        if(Integer.toString(locationOfCorrect).equals(view.getTag().toString()))
        {
            result.setText("Correct!");
            correct++;
        }
        else
         {
            result.setText("Wrong!");
         }
        questions++;

        newQuestion();

    }

    public void newQuestion()
    {
        Random rand = new Random();
        int a = 1 + rand.nextInt(21);
        int b = 1 + rand.nextInt(21);


        TextView add = findViewById(R.id.sum);
        add.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrect = 0 + rand.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++)
        {
            if(i==locationOfCorrect){
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer = 0 + rand.nextInt(41);
                while(wrongAnswer == a+b)
                {
                    answers.add(rand.nextInt(41));
                }
                answers.add(wrongAnswer);
            }

        }

        score.setText(Integer.toString(correct)+"/"+Integer.toString(questions));

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view)
    {
        newQuestion();
        correct=0;
        questions = 0;
        score.setText(Integer.toString(correct)+"/"+Integer.toString(questions));
        timer.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(10100, 1000)
        {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                result.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button5);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        button3 = findViewById(R.id.button8);
        goButton = findViewById(R.id.gobutton);
        result = findViewById(R.id.result);
        score = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        playAgain = findViewById(R.id.playAgain);
        gamelayout = findViewById(R.id.gamelayout);

        goButton.setVisibility(View.VISIBLE);
        gamelayout.setVisibility(View.INVISIBLE);


    }
}
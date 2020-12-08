package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int random1,random2,randoma,randomb,randomc,randomd,randomTag;
    Button[] option = new Button[4];
    CountDownTimer countDownTimer;
    TextView textViewTimer;
    int totalQuestion=0 ,correctQuestions = 0;


    public void start(View  view){
    randomNumberGenerater();
    QuestionSetter();
    optionGenerator();
    SetTimer();
    }


    @SuppressLint("SetTextI18n")
    public  void optionGenerator(){
        int[] option_button = {randoma , randomb , randomc , randomd};

        for (int i=0;i<4;i++){
            if (randomTag == i)
                option[i].setText(Integer.toString((random1 + random2)));
            else
                option[i].setText(Integer.toString(option_button[i]));

        }
    }



    @SuppressLint("SetTextI18n")
    public  void QuestionSetter(){
        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewQuestion.setText(random1 + "  +  " + random2);
    }

    public void randomNumberGenerater(){
        random1 = new Random().nextInt(40) + 10;
        random2 = new Random().nextInt(40) + 10;

        randoma = new Random().nextInt(40) + 10;
        randomb = new Random().nextInt(40) + 10;
        randomc = new Random().nextInt(40) + 10;
        randomd = new Random().nextInt(40) + 10;

        randomTag = new Random().nextInt(4) ;
    }

    public void SetTimer(){
        textViewTimer.setText("00:30");
        countDownTimer  = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                int second = (int) l/1000;
                textViewTimer.setText(Integer.toString(second));
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }

    public  void  counter(){
        TextView textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        textViewCounter.setText(Integer.toString(correctQuestions) + " / " + Integer.toString(totalQuestion));
    }

    public void Reset(){
        counter();
        countDownTimer.cancel();
        randomNumberGenerater();
        QuestionSetter();
        optionGenerator();
        SetTimer();

    }



    public void onClick(View view){
        IsCorrectAnswer();
    }

    public void IsCorrectAnswer() {

        TextView textViewAnswer = findViewById(R.id.textViewAnswer);

       for (int i=0;i<4;i++){
        if (Integer.parseInt(option[i].getText().toString())  == (random1 + random2)){
            textViewAnswer.setText("Answer is Correct");
            totalQuestion++;
            correctQuestions++;
            break;

        }
        else {textViewAnswer.setText("Answer is Wrong");
            totalQuestion++;

        } }
        if (totalQuestion > 20)
            textViewAnswer.setText("END     Of");
       Reset();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option[0] = (Button) findViewById(R.id.buttona);
        option[1] = (Button) findViewById(R.id.buttonb);
        option[2] = (Button) findViewById(R.id.buttonc);
        option[3] = (Button) findViewById(R.id.buttond);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);



    }
}
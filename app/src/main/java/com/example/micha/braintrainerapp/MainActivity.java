package com.example.micha.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int count = 0, correctAnswerCount = 0;
    String[] questions = {"2 + 4", "8 + 3", "5 + 1", "10 + 15", "11 + 4", "12 + 8", "8 + 5", "14 + 3", "17 + 1", "6 + 13", "15 + 11", "3  + 5", " 22 + 7", "10 + 21", "7 + 9", "17 + 5"};
    String[] answers = {"6", "11", "6", "25", "15", "20", "13", "17", "18", "19", "26", "8", " 29", "31", "16", "22"};
    String[][] options = {
            {"8", "6", "4", "1"}, {"11", "14", "18", "22"}, {"3", "8", "7", "6"}, {"25", "18", "60", "100"}, {"1", "15", "2", "0"}, {"10", "30", "20", "21"}, {"13", "55", "10", "17"}, {"32", "98", "84", "17"},
            {"8", "10", "18", "7"}, {"7", "15", "20", "19"}, {"2", "26", "15", "21"}, {"11", "7", "54", "8"}, {"45", "21", "29", "74"}, {"34", "31", "38", "39"}, {"16", "8", "17", "11"}, {"22", "75", "57", "14"}
    };
    LinearLayout layout;
    CountDownTimer countDown;
    TextView timerView, questionView, scoreView, optionOneView, optionTwoView, optionThreeView, optionFourView, resultsView, startQuiz;

    public void selectAnswer(View view) {

        if(count < 16) {
            int id = view.getId();
            ceckAnswers(id);


            count++;
            setOptions();
            if (count >= 16) {
                quizOver();
                countDown.cancel();
            }
        }
    }
    public void quizOver(){
        resultsView.setText("Score : " + correctAnswerCount + " / " + count);
        layout.setVisibility(View.VISIBLE);
        layout.bringToFront();
        timerView.setText("00:00");
    }
    public void setOptions(){
        if(count < 16) {
            optionOneView.setText(options[count][0]);
            optionTwoView.setText(options[count][1]);
            optionThreeView.setText(options[count][2]);
            optionFourView.setText(options[count][3]);
            questionView.setText(questions[count]);
            scoreView.setText(correctAnswerCount + "/" + count );
        }
    }

    public  void retakeQuiz(View view){
        count = 0;
        correctAnswerCount = 0;
        setOptions();
        resultsView.setText("");
        startQuiz(view);
        layout.setVisibility(View.INVISIBLE);

    }

    public void ceckAnswers(int id){
        TextView answer = findViewById(id);
        String strAnswer =  answer.getText().toString();
        if(strAnswer.equals(answers[count])){
            correctAnswerCount++;
            resultsView.setText("Correcct!");
        }else{

            resultsView.setText("Wrong!");
        }


    }

    public void startQuiz(View view) {
        startQuiz.setVisibility(View.INVISIBLE);
        timerView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        optionOneView.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        optionTwoView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        optionThreeView.setVisibility(View.VISIBLE);
        optionFourView.setVisibility(View.VISIBLE);
        resultsView.setVisibility(View.VISIBLE);
        countDown = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                int min, sec;
                String time, padSec = "", padMin = "";
                min = (int) (l / 1000) / 60;
                sec = (int) (l / 1000) % 60;
                if (min <= 9) {
                    padMin = "0";
                }
                if (sec <= 9) {
                    padSec = "0";
                }
                time = padMin + min + ":" + padSec + sec;
                timerView.setText(time);
            }

            @Override
            public void onFinish() {
                this.cancel();

                quizOver();



            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        timerView.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        optionOneView.setVisibility(View.INVISIBLE);
        questionView.setVisibility(View.INVISIBLE);
        optionTwoView.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        optionThreeView.setVisibility(View.INVISIBLE);
        optionFourView.setVisibility(View.INVISIBLE);
        resultsView.setVisibility(View.INVISIBLE);
        startQuiz.setVisibility(View.VISIBLE);
        setOptions();
    }

    public void init() {
        timerView = findViewById(R.id.timer_label);
        scoreView = findViewById(R.id.score_label);
        optionOneView = findViewById(R.id.option1_view);
        questionView = findViewById(R.id.question_label);
        optionTwoView = findViewById(R.id.option2_view);
        scoreView = findViewById(R.id.score_label);
        optionThreeView = findViewById(R.id.option3_view);
        optionFourView = findViewById(R.id.option4_view);
        resultsView = findViewById(R.id.results_label);
        startQuiz = findViewById(R.id.start_view);
        layout =  findViewById(R.id.retake_layout_view);

    }
}

package com.example.fad.beninquiz;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private static ImageView imageView;
    private static TextView textView, scoreT, timeT;
    private static RadioGroup radioGroup;

    private static RadioButton radioButton1;
    private static RadioButton radioButton2;
    private static RadioButton radioButton3;


    private int currentQuestionIndex;
    private static Button button;

    private ArrayList<Question> questions;

    int score ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();
       // scoreT.setText(score);


    }

    private void initialize() {

        imageView = (ImageView) findViewById(R.id.pictureI);
        textView = (TextView) findViewById(R.id.question);
        radioGroup = (RadioGroup) findViewById(R.id.rg_choices);
        radioButton1 = (RadioButton) findViewById(R.id.rb_choiceA);
        radioButton2 = (RadioButton) findViewById(R.id.rb_choiceB);
        radioButton3 = (RadioButton) findViewById(R.id.rb_choiceC);
        button = (Button) findViewById(R.id.submit);

        timeT = (TextView) findViewById(R.id.time);
        currentQuestionIndex = 0;




        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (this.answerIsRight()) {

                    score=score+5;
                    displayScore(score);

                    Log.e("score", String.valueOf(score));
                    Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();

                    advance();
                    countDownTimer.start();


                } else {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    advance();
                    countDownTimer.start();
                }




            }


            private boolean answerIsRight() {

                String answer = "x";
                int ids = radioGroup.getCheckedRadioButtonId();
                RadioButton rb_selected = (RadioButton) findViewById(ids);
                if (rb_selected == radioButton1) answer = "a";
                if (rb_selected == radioButton2) answer = "b";
                if (rb_selected == radioButton3) answer = "c";
              /*  if (rb_selected.getText().toString().isEmpty()) {
                    answer = "";
                    Toast.makeText(getApplicationContext(), "Veuillez faire un choix", Toast.LENGTH_SHORT).show();
                } */


                return questions.get(currentQuestionIndex).isCorrectAnswer(answer);


            }


        });



        FirebaseDatabase fbaseRef = FirebaseDatabase.getInstance();

        DatabaseReference dbase = fbaseRef.getReference("Questions");
        dbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questions = new ArrayList<Question>();
                for (DataSnapshot q : dataSnapshot.getChildren()) {
                    questions.add(q.getValue(Question.class));
                   // currentQuestionIndex = 0;
                    displayQuestion(currentQuestionIndex);
                    countDownTimer.start();







                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void displayQuestion(int index) {

        textView.setText(questions.get(currentQuestionIndex).getQuestionText());
        radioButton1.setText(questions.get(currentQuestionIndex).getChoiceA());
        radioButton2.setText(questions.get(currentQuestionIndex).getChoiceB());
        radioButton3.setText(questions.get(currentQuestionIndex).getChoiceC());
        radioGroup.clearCheck();

    }

    private void advance() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        displayQuestion(currentQuestionIndex);



    }

    private void displayScore(int score) {
        scoreT = (TextView) findViewById(R.id.score);
        scoreT.setText( "Score : "+String.valueOf(score));
    }




    CountDownTimer countDownTimer = new CountDownTimer(15000, 1000) {
        public void onTick(long millisUntilFinished) {
            timeT.setText("00:00:" + millisUntilFinished / 1000);
        }
        public void onFinish() {
            timeT.setText("Time Up!");

            advance();
        }
    };











}


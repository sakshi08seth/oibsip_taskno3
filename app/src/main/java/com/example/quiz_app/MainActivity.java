package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView total_question;
    TextView question;
    Button ansA,ansB,ansC,ansD;
    Button submit_btn;

    int score=0;
    int totalQuestion=question_answers.question.length;
    int currentQuestion=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total_question=findViewById(R.id.total_question);
        question=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        submit_btn=findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit_btn.setOnClickListener(this);

        total_question.setText("Total Questions :"+totalQuestion);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(question_answers.answers[currentQuestion])){
                score++;
            }
            currentQuestion++;
            loadNewQuestion();
        }
        else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }
    void loadNewQuestion(){
        if(currentQuestion==totalQuestion){
            finishQuiz();
            return;
        }
        question.setText(question_answers.question[currentQuestion]);
        ansA.setText(question_answers.choices[currentQuestion][0]);
        ansB.setText(question_answers.choices[currentQuestion][1]);
        ansC.setText(question_answers.choices[currentQuestion][2]);
        ansD.setText(question_answers.choices[currentQuestion][3]);
    }
    public void finishQuiz(){
        String passStatus="";
        if(score>totalQuestion*0.60){
            passStatus="Passed";
        }
        else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentQuestion=0;
        loadNewQuestion();
    }
}
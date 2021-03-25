package com.venkataram.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
//controller
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueButton,falseButton;
    private TextView questionTextView;
    private ImageButton nextButton,previousButton;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments,false),
            new Question(R.string.question_constitution,true),
            new Question(R.string.question_declaration,true),
            new Question(R.string.question_independence_rights,true),
            new Question(R.string.question_religion,true),
            new Question(R.string.question_government,false),
            new Question(R.string.question_government_feds,false),
            new Question(R.string.question_government_senators,true),
            new Question(R.string.rohit_sharma,true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        questionTextView = findViewById(R.id.question_text_view);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

        updateQuestion();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                currentQuestionIndex  = (currentQuestionIndex+1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.previous_button:
                if(currentQuestionIndex==0)
                    currentQuestionIndex = questionBank.length;
                currentQuestionIndex--;
                updateQuestion();

        }
    }
    private void updateQuestion(){
        questionTextView.setText(questionBank[currentQuestionIndex].getQuestionResId());
        Log.d("Debug","onclick : "+currentQuestionIndex);
    }

    private void checkAnswer(boolean userChoice){
        boolean actualAnswer = questionBank[currentQuestionIndex].isAnswerTrue();
        int resultResId = 0;

        if(userChoice==actualAnswer){
            resultResId = R.string.correct_text;
        }
        else{
            resultResId = R.string.wrong_text;
        }
        Toast.makeText(this, resultResId, Toast.LENGTH_SHORT).show();
    }
}
package com.srinija.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int right = 0, total = 0, timeRemaining;
    String[] operators = {"+", "-", "*"};
    int maximumOp1 = 20, minimumOp1 = 11;
    int maximumOp2 = 10, minimumOp2 = 1;
    CountDownTimer countdown;
    TextView timer,  question, resu, score;
    int randAnswerBut;
    Button but;
    Button but1, but2, but3, but4;
    Random ran = new Random();
    ArrayList<Integer> all = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1 = (Button) findViewById(R.id.option1);
        but2 = (Button) findViewById(R.id.option2);
        but3 = (Button) findViewById(R.id.option3);
        but4 = (Button) findViewById(R.id.option4);


        all.add(R.id.option1);
        all.add(R.id.option2);
        all.add(R.id.option3);
        all.add(R.id.option4);


        timer = (TextView) findViewById(R.id.timer);
        question = (TextView) findViewById(R.id.question);
        resu = (TextView) findViewById(R.id.answer);
        score = (TextView) findViewById(R.id.score);

        resu.setVisibility(View.INVISIBLE);

        score.setText(Integer.toString(right)+"/"+Integer.toString(total));

        String operand = generate_random_operator();

        ArrayList<Integer> arrayList = generate_random_operands(operand);

        String quest = Integer.toString(arrayList.get(0)) + operand + Integer.toString(arrayList.get(1));


        Log.i("Operator selected = " , operand);

        Log.i("Question generated = ", quest);

        question.setText(quest);

        int tag_result = generate_options(arrayList, operand, all);

        Log.i("Tag of result=", Integer.toString(tag_result));

        countdown = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                timeRemaining = (int) (l/1000);
                timer.setText(Integer.toString(timeRemaining) + "s");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("total", Integer.toString(total));
                intent.putExtra("right", Integer.toString(right));
                startActivity(intent);
            }
        }.start();
    }

    public void buttonClicked(View view)
    {
        total++;
        Button pressed = (Button) view;
        int tag_pressed = Integer.parseInt(pressed.getTag().toString());
        if(tag_pressed == randAnswerBut) {
            resu.setText("RIGHT!");

            resu.setBackgroundColor(Color.GREEN);
            resu.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    resu.setVisibility(View.INVISIBLE);
                }
            }, 1000);
            right++;
        }
        else {
            resu.setText("WRONG!");
            resu.setVisibility(View.VISIBLE);
            resu.setBackgroundColor(Color.RED);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    resu.setVisibility(View.INVISIBLE);
                }
            }, 1000);
        }

        score.setText(Integer.toString(right)+"/"+Integer.toString(total));

        all.add(R.id.option1);
        all.add(R.id.option2);
        all.add(R.id.option3);
        all.add(R.id.option4);

        String operand = generate_random_operator();

        ArrayList<Integer> arrayList = generate_random_operands(operand);

        String quest = Integer.toString(arrayList.get(0)) + operand + Integer.toString(arrayList.get(1));


        Log.i("Operator selected = " , operand);

        Log.i("Question generated = ", quest);

        question.setText(quest);



        int tag_result = generate_options(arrayList, operand, all);

        Log.i("Tag of result=", Integer.toString(tag_result));

        Log.i("___","----");

        //Log.i("Tag:",but.getTag().toString());
    }


    public String generate_random_operator(){

        return operators[ran.nextInt(operators.length)];
    }

    public ArrayList<Integer> generate_random_operands(String op)
    {
        int operand1 = (int) (Math.random() * (maximumOp1 - minimumOp1 + 1) + minimumOp1);
        int operand2 = (int) (Math.random() * (maximumOp2 - minimumOp2 + 1) + minimumOp2);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(operand1);
        arrayList.add(operand2);
        return arrayList;
    }

    public int generate_options(ArrayList<Integer> arrayList, String operand, ArrayList<Integer> all)
    {
        int result, id, option1, option2, option3, index;
        int op1 = arrayList.get(0), op2 = arrayList.get(1);
        switch (operand)
        {
            case "+" : result = (op1 + op2);
                        break;
            case "-" : result = (op1 - op2);
                        break;
            case "*" : result  = (op1 * op2);
                        break;
            default: result = 0;
                     break;
        }

        //randAnswerBut = (int)(Math.random() * (5-1 +1) + 1);

        option1 = (int)(Math.random() * (result+10 - (result+5) +1) + (result+5));
        option2 = (int) (Math.random() * ((result-10) - (result-5) + 1) + (result+5));
        option3 = (result - 1);

        Log.i("RESULT: ",Integer.toString(result));

        Log.i("All:",all.toString());

            index = ran.nextInt(all.size());
            id = all.get(index);
            but = (Button) findViewById(id);
            but.setText(Integer.toString(result));
            randAnswerBut = Integer.parseInt(but.getTag().toString());


            all.remove(index);
            index = ran.nextInt(all.size());
            id = all.get(index);
            but = (Button) findViewById(id);
            but.setText(Integer.toString(option1));

            all.remove(index);
            index = ran.nextInt(all.size());
            id = all.get(index);
            but = (Button) findViewById(id);
            but.setText(Integer.toString(option2));

            all.remove(index);
            index = ran.nextInt(all.size());
            id = all.get(index);
            but = (Button) findViewById(id);
            but.setText(Integer.toString(option3));

            all.remove(index);



        //Log.i("Tag of selected button:", but.getTag().toString() );


        //but.setText(Integer.toString(result));

        return randAnswerBut;
    }


}
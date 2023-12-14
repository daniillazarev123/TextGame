package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStory;
    private EditText etAnswer;
    private Button start , action;
    public static Character manager;
    public static Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStory= findViewById(R.id.textView);
        etAnswer= findViewById(R.id.editTextText2);
        start= findViewById(R.id.button);
        action= findViewById(R.id.button2);
        tvStory.setText("Вы прошли собеседование и вот-вот станете сотрудником Корпорации.\n" +"" +
                " Осталось уладить формальности - подпись под договором (Введите ваше имя)");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager = new Character(etAnswer.getText().toString());
                story = new Story();
                getStory();
                start.setVisibility(View.GONE);
                action.setVisibility(View.VISIBLE);

            }
        });


        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (story.isEnd()) {
                    tvStory.append("\n====================the end!===================");
                    return;
                }
                story.go(Integer.parseInt(etAnswer.getText().toString()));
                getStory();


            }
        });
    }



    public void getStory(){
        manager.A += story.current_situation.dA;
        manager.K += story.current_situation.dK;
        manager.R += story.current_situation.dR;
        tvStory.append("\nКарьера:" + manager.K + "\nАктивы:"
                + manager.A + "\nРепутация:" + manager.R + "\n");
        tvStory.append("============="
                + story.current_situation.subject + "==============\n");
        tvStory.append(story.current_situation.text);
        etAnswer.setText("");
        //привет
    }
}
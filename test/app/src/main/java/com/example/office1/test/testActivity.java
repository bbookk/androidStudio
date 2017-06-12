package com.example.office1.test;

import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class testActivity extends Activity implements View.OnClickListener{
    TextView text;
    RadioButton up , down;
    Button btnCount;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //อ้างอิงไปยังวิวต่างๆใน UI ตาม ID ที่เราระบุใน Layout file
        text = (TextView) findViewById(R.id.counter_value);
        up = (RadioButton) findViewById(R.id.count_up_radio);
        down = (RadioButton) findViewById(R.id.count_down_radio);
        btnCount = (Button) findViewById(R.id.count_button);

        Button btn = (Button) this.findViewById(R.id.count_button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Context context = getApplicationContext();
        String msg = "คุณคลิกปุ่ม";
        int duration = Toast.LENGTH_SHORT;

        Toast t = Toast.makeText(context, msg, duration);
        t.show();

        if(up.isChecked()){
            count++;
        }
        else if(down.isChecked()){
            count--;
        }

        text.setText(String.valueOf(count));
    }


}

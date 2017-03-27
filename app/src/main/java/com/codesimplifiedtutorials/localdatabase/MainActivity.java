package com.codesimplifiedtutorials.localdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1,et2;
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);

            bt1.setOnClickListener(this);


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,SecondActivity.class));

            }
        });
    }

    @Override
    public void onClick(View v) {


        String task = et1.getText().toString();
        String desc = et2.getText().toString();


        MyDatabase mdb = new MyDatabase(this);

        mdb.open();
        mdb.write(task, desc);
        mdb.close();

        Toast.makeText(MainActivity.this, "Your Data is saved", Toast.LENGTH_SHORT).show();


    }
}

package com.codesimplifiedtutorials.localdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = (TextView) findViewById(R.id.textView);
        MyDatabase mdb = new MyDatabase(this);
        mdb.open();
        String result =  mdb.read();
        mdb.close();

        tv1.setText(result);

    }
}

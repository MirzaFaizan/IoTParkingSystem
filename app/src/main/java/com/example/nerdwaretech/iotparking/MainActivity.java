package com.example.nerdwaretech.iotparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView title;
    TextView name, no, price, desc, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.title);
        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("title"));
    }
}

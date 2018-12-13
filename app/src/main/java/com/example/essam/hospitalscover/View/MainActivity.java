package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.essam.hospitalscover.Model.Example;
import com.example.essam.hospitalscover.ModelView.TempModelView;
import com.example.essam.hospitalscover.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TempModelView tempModelView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.test);
        tempModelView = ViewModelProviders.of(this).get(TempModelView.class);

        initListener();
        getData();
    }

    private void getData() {
        tempModelView.getAllTest();
    }

    private void initListener() {
        tempModelView.getTest().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(@Nullable Example strings) {
                if (strings != null) {
                    textView.setText(strings.getData()+" " +strings.getTime());

                } else {
                    Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}

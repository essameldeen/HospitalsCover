package com.example.essam.hospitalscover.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.essam.hospitalscover.ModelView.TempModelView;
import com.example.essam.hospitalscover.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TempModelView tempModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempModelView = ViewModelProviders.of(this).get(TempModelView.class);

        initListener();
        getData();
    }

    private void getData() {
        tempModelView.getAllTest(this, "id");
    }

    private void initListener() {
        tempModelView.getTest().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                // any change data will be fire in here
            }
        });
    }


}

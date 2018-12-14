package com.example.essam.hospitalscover.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.essam.hospitalscover.Adapter.ResultAdapter;
import com.example.essam.hospitalscover.Interfaces.AdapterCategoryInterface;
import com.example.essam.hospitalscover.Model.ResultData;
import com.example.essam.hospitalscover.R;

import java.util.ArrayList;
import java.util.List;

public class ResultActiviyt extends AppCompatActivity implements AdapterCategoryInterface {

    private RecyclerView recycler_result;
    private ResultAdapter resultAdapter;
    private List<ResultData> resultDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result_activity);

        resultDataList = (List<ResultData>) getIntent().getSerializableExtra("data");

        initView();


    }

    private void initView() {
        recycler_result = (RecyclerView) findViewById(R.id.recycler_result);
        recycler_result.setLayoutManager(new LinearLayoutManager(this));
        recycler_result.setHasFixedSize(true);
        resultAdapter = new ResultAdapter(this, this);
        recycler_result.setAdapter(resultAdapter);
        resultAdapter.setData(resultDataList);


    }

    @Override
    public void onCardClick(View view, int position) {

        int idView = view.getId() ;

        switch (idView){
            case R.id.booking:
                booking(position);
                break;

                default:
                    break;

        }
    }

    private void booking(int position) {

    }
}

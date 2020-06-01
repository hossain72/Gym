package com.demo.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class AllTrainingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrainingAdapter trainingAdapter;
    private List<Training> trainingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_training);

        recyclerView = findViewById(R.id.recyclerView);
        trainingAdapter = new TrainingAdapter(this);
        recyclerView.setAdapter(trainingAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        trainingList = Utils.getTrainingList();

        if (null != trainingList){
            trainingAdapter.setTrainingList(trainingList);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllTrainingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

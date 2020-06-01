package com.demo.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {

    private TextView dayNameTV;
    private RecyclerView recyclerView;
    private Button addMorePlanBtn;
    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();

        adapter = new PlanAdapter(this);
        adapter.setType("Edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        if (null != intent) {
            String day = intent.getStringExtra("day");
            if (null != day) {
                dayNameTV.setText(day);
                List<Plan> planList = getPlansByDay(day);
                adapter.setPlanList(planList);
            }
        }

        addMorePlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this, AllTrainingActivity.class));
            }
        });

    }

    private List<Plan> getPlansByDay(String day) {

        List<Plan> allPlans = Utils.getPlanList();
        List<Plan> planList = new ArrayList<>();

        for (Plan plan : allPlans) {

            if (plan.getDay().equalsIgnoreCase(day)) {
                planList.add(plan);
            }

        }

        return planList;

    }

    private void initView() {

        dayNameTV = findViewById(R.id.dayNameTV);
        recyclerView = findViewById(R.id.recyclerView);
        addMorePlanBtn = findViewById(R.id.addMorePlanBtn);

    }

    @Override
    public void onRemovePlanResult(Plan plan) {
        if (Utils.removePlan(plan)){
            Toast.makeText(this, "Training removed from your plan.", Toast.LENGTH_SHORT).show();
            adapter.setPlanList(getPlansByDay(plan.getDay()));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditActivity.this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

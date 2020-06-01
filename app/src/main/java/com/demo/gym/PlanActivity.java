package com.demo.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    private LinearLayout noPlanLayout;
    private Button addPlanBtn;
    private TextView mondayEditET, tuesdayEditET, wednesdayEditET, thursdayEditET, fridayEditET, saturdayEditET, sundayEditET;
    private NestedScrollView nestedScrollView;
    private RecyclerView mondayRV, tuesdayRV, wednesdayRV, thursdayRV, fridayRV, saturdayRV, sundayRV;
    private PlanAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter, fridayAdapter, saturdayAdapter, sundayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initView();

        List<Plan> planList = Utils.getPlanList();

        if (null != planList) {

            if (planList.size() > 0) {

                noPlanLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);

                initRecyclerView();
                setEditOnClickListeners();

            } else {
                noPlanLayout.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.GONE);
                addPlanBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlanActivity.this, AllTrainingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }

        } else {
            noPlanLayout.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
            addPlanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlanActivity.this, AllTrainingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

    }

    private void setEditOnClickListeners() {

        final Intent intent = new Intent(PlanActivity.this, EditActivity.class);
        mondayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Monday");
                startActivity(intent);
            }
        });

        tuesdayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Tuesday");
                startActivity(intent);
            }
        });

        wednesdayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Wednesday");
                startActivity(intent);
            }
        });

        thursdayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Thursday");
                startActivity(intent);
            }
        });

        fridayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Friday");
                startActivity(intent);
            }
        });

        saturdayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Saturday");
                startActivity(intent);
            }
        });

        sundayEditET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day", "Sunday");
                startActivity(intent);
            }
        });

    }

    private void initView() {

        noPlanLayout = findViewById(R.id.noPlanLayout);
        addPlanBtn = findViewById(R.id.addPlanBtn);
        mondayEditET = findViewById(R.id.mondayEditTV);
        tuesdayEditET = findViewById(R.id.tuesdayEditTV);
        wednesdayEditET = findViewById(R.id.wednesdayEditTV);
        thursdayEditET = findViewById(R.id.thursdayEditTV);
        fridayEditET = findViewById(R.id.fridayEditTV);
        saturdayEditET = findViewById(R.id.saturdayEditTV);
        sundayEditET = findViewById(R.id.sundayEditTV);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        mondayRV = findViewById(R.id.mondayRecyclerView);
        tuesdayRV = findViewById(R.id.tuesdayRecyclerView);
        wednesdayRV = findViewById(R.id.wednesdayRecyclerView);
        thursdayRV = findViewById(R.id.thursdayRecyclerView);
        fridayRV = findViewById(R.id.fridayRecyclerView);
        saturdayRV = findViewById(R.id.saturdayRecyclerView);
        sundayRV = findViewById(R.id.sundayRecyclerView);

    }

    private void initRecyclerView() {

        mondayAdapter = new PlanAdapter(PlanActivity.this);
        mondayRV.setAdapter(mondayAdapter);
        mondayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mondayAdapter.setPlanList(getPlansByDay("Monday"));

        tuesdayAdapter = new PlanAdapter(PlanActivity.this);
        tuesdayRV.setAdapter(tuesdayAdapter);
        tuesdayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        tuesdayAdapter.setPlanList(getPlansByDay("Tuesday"));

        wednesdayAdapter = new PlanAdapter(PlanActivity.this);
        wednesdayRV.setAdapter(wednesdayAdapter);
        wednesdayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        wednesdayAdapter.setPlanList(getPlansByDay("Wednesday"));

        thursdayAdapter = new PlanAdapter(PlanActivity.this);
        thursdayRV.setAdapter(thursdayAdapter);
        thursdayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        thursdayAdapter.setPlanList(getPlansByDay("Thursday"));

        fridayAdapter = new PlanAdapter(PlanActivity.this);
        fridayRV.setAdapter(fridayAdapter);
        fridayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        fridayAdapter.setPlanList(getPlansByDay("Friday"));

        saturdayAdapter = new PlanAdapter(PlanActivity.this);
        saturdayRV.setAdapter(saturdayAdapter);
        saturdayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        saturdayAdapter.setPlanList(getPlansByDay("Saturday"));

        sundayAdapter = new PlanAdapter(PlanActivity.this);
        sundayRV.setAdapter(sundayAdapter);
        sundayRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        sundayAdapter.setPlanList(getPlansByDay("Sunday"));

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

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

}

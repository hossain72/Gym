package com.demo.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {

    private Button addPlanBtn;
    private TextView trainingNameTV, longDescTV;
    private ImageView trainingIV;
    public static final String TRAINING_KEY = "training";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initView();

        Intent intent = getIntent();

        if (null != intent) {

            final Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training) {
                trainingNameTV.setText(training.getName());
                longDescTV.setText(training.getLongDesc());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(trainingIV);
                addPlanBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PlanDetailDialog dialog = new PlanDetailDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "plan details dialog");

                    }
                });
            }

        }

    }

    private void initView() {

        addPlanBtn = findViewById(R.id.addPlanBtn);
        trainingNameTV = findViewById(R.id.trainingNameTV);
        longDescTV = findViewById(R.id.longDescriptionTV);
        trainingIV = findViewById(R.id.trainingIV);

    }

    @Override
    public void getPlan(Plan plan) {

        if (Utils.addPlan(plan)) {
            Toast.makeText(this, plan.getTraining().getName() + " add to your plan.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TrainingActivity.this, PlanActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}

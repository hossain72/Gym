package com.demo.gym;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static com.demo.gym.TrainingActivity.TRAINING_KEY;

public class PlanDetailDialog extends DialogFragment {

    public interface PassPlanInterface {

        void getPlan(Plan plan);

    }

    private PassPlanInterface passPlanInterface;

    private TextView trainingNameTV;
    private EditText minutesET;
    private Spinner daySpinner;
    private Button dismissBtn, addBtn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.diolog_plan_details, null);
        initViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Details");

        Bundle bundle = getArguments();

        if (null != bundle) {
            final Training training = bundle.getParcelable(TRAINING_KEY);
            if (null != training) {
                trainingNameTV.setText(training.getName());
                dismissBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = daySpinner.getSelectedItem().toString();
                        int minutes = Integer.valueOf(minutesET.getText().toString());
                        Plan plan = new Plan(training, minutes, day, false);

                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        }catch (ClassCastException e){
                            e.printStackTrace();
                            dismiss();
                        }

                    }
                });
            }
        }

        return builder.create();
    }

    private void initViews(View view) {

        trainingNameTV = view.findViewById(R.id.trainingNameTV);
        minutesET = view.findViewById(R.id.minuteET);
        daySpinner = view.findViewById(R.id.selectDay);
        dismissBtn = view.findViewById(R.id.dismissBtn);
        addBtn = view.findViewById(R.id.addBtn);

    }
}

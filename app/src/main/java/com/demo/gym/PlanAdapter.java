package com.demo.gym;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.demo.gym.TrainingActivity.TRAINING_KEY;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }

    private RemovePlan removePlan;
    private List<Plan> planList = new ArrayList<>();
    private Context context;
    private String type = "";

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Plan plan = planList.get(position);

        holder.trainingNameTV.setText(plan.getTraining().getName());
        holder.timeTV.setText(String.valueOf(plan.getMinutes()));
        holder.descriptionTV.setText(plan.getTraining().getShortDesc());

        Glide.with(context)
                .asBitmap()
                .load(plan.getTraining().getImageUrl())
                .into(holder.trainingImageIV);

        if (plan.isAccomplished()) {

            holder.emptyCircleIV.setVisibility(View.GONE);
            holder.checkCircleIV.setVisibility(View.VISIBLE);

        } else {

            holder.emptyCircleIV.setVisibility(View.VISIBLE);
            holder.checkCircleIV.setVisibility(View.GONE);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra(TRAINING_KEY, plan.getTraining());
                context.startActivity(intent);

            }
        });

        if (type.equals("Edit")) {
            holder.emptyCircleIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finished")
                            .setMessage("Have you finished " + plan.getTraining().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    for (Plan p : Utils.getPlanList()) {
                                        if (p.equals(plan)) {
                                            p.setAccomplished(true);
                                        }
                                    }
                                    notifyDataSetChanged();

                                }
                            });
                    builder.create().show();
                }
            });

            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Remove")
                            .setMessage("Are you sure you want to delete " + plan.getTraining().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        removePlan = (RemovePlan) context;
                                        removePlan.onRemovePlanResult(plan);
                                        notifyDataSetChanged();
                                    }catch (ClassCastException e){
                                        e.printStackTrace();
                                    }
                                }
                            });

                    builder.create().show();

                    return true;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return planList.size();
    }


    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView trainingNameTV, timeTV, descriptionTV;
        private ImageView emptyCircleIV, checkCircleIV, trainingImageIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            trainingNameTV = itemView.findViewById(R.id.trainingNameTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            descriptionTV = itemView.findViewById(R.id.descriptionET);
            emptyCircleIV = itemView.findViewById(R.id.emptyCircle);
            checkCircleIV = itemView.findViewById(R.id.checkedCircle);
            trainingImageIV = itemView.findViewById(R.id.trainingImage);

        }
    }
}

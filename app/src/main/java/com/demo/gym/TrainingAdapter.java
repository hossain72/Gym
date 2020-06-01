package com.demo.gym;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.demo.gym.TrainingActivity.TRAINING_KEY;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private Context context;
    private List<Training> trainingList;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_training, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Training training = trainingList.get(position);
        holder.trainingNameTV.setText(training.getName());
        holder.shortDescTV.setText(training.getShortDesc());
        Glide.with(context)
                .asBitmap()
                .load(training.getImageUrl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra(TRAINING_KEY, training);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }


    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView trainingNameTV, shortDescTV;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            trainingNameTV = itemView.findViewById(R.id.trainingNameTV);
            shortDescTV = itemView.findViewById(R.id.shortDescTV);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}

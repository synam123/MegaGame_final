package com.poly.megagame.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.megagame.R;
import com.poly.megagame.model.altp.PointMiliestonesALTP;

import java.util.ArrayList;

public class PointMiliestonesALTPAdapter extends RecyclerView.Adapter<PointMiliestonesALTPAdapter.ViewHolder> {

    private ArrayList<PointMiliestonesALTP> arrayPoint;
    private Handler handler = new Handler();
    private int position = 0;

    public PointMiliestonesALTPAdapter() {
        arrayPoint = new ArrayList<>();
        arrayPoint.add(new PointMiliestonesALTP("①  200.000"));
        arrayPoint.add(new PointMiliestonesALTP("②  400.000"));
        arrayPoint.add(new PointMiliestonesALTP("③  600.000"));
        arrayPoint.add(new PointMiliestonesALTP("④  1.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑤  2.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑥  3.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑦  6.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑧  8.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑨  14.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑩  22.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑪  30.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑫  40.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑬  60.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑭  85.000.000"));
        arrayPoint.add(new PointMiliestonesALTP("⑮  150.000.000"));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_point_milestones_altp,null);

        return new ViewHolder(view);
    }

    public void sliderPoint(){
        position = 0;
        new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i< arrayPoint.size(); i++){
                    if (arrayPoint.get(i).isChecked()){
                        arrayPoint.get(i).setChecked(false);
                        notifyItemChanged(i);
                        break;
                    }
                }
                arrayPoint.get(position).setChecked(true);
                notifyItemChanged(position);

                position++;
                if (position == arrayPoint.size()){
                    return;
                }
                handler.postDelayed(this,400);
            }
        }.run();
    }

    public void slidePointMiliestones(){
        position = 0;

        arrayPoint.get(14).setChecked(false);
        notifyItemChanged(14);

        new Runnable(){
            @Override
            public void run() {
                switch (position){
                    case 0:
                        arrayPoint.get(4).setChecked(true);
                        notifyItemChanged(4);
                        break;
                    case 1:
                        arrayPoint.get(4).setChecked(false);
                        notifyItemChanged(4);

                        arrayPoint.get(9).setChecked(true);
                        notifyItemChanged(9);
                        break;
                    case 2:
                        arrayPoint.get(9).setChecked(false);
                        notifyItemChanged(9);

                        arrayPoint.get(14).setChecked(true);
                        notifyItemChanged(14);
                        break;
                }
                position++;

                if (position == 3){
                    return;
                }
                handler.postDelayed(this,500);
            }
        }.run();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (arrayPoint.get(position).isChecked()){
            holder.txtPoints.setTextColor(Color.BLACK);
            holder.txtPoints.setBackgroundResource(R.drawable.ic_custom_points);
        }else {
            switch (position){
                case 4:
                case 9:
                case 14:
                    holder.txtPoints.setTextColor(Color.WHITE);
                    break;
                default:
                    holder.txtPoints.setTextColor(Color.parseColor("#FF5722"));
                    break;
            }
            holder.txtPoints.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.txtPoints.setText(arrayPoint.get(position).getPoints());
    }

    @Override
    public int getItemCount() {
        return arrayPoint.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtPoints;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPoints    = itemView.findViewById(R.id.txtPoints);
        }
    }
}

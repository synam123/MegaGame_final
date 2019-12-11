package com.poly.megagame.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poly.megagame.callback.PuzzleOnItemCount;
import com.poly.megagame.model.puzzle.Puzzle;
import com.poly.megagame.utils.ScreenUtils;
import com.poly.megagame.utils.GameUtils;

import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGameAdapter extends RecyclerView.Adapter<PuzzleGameAdapter.ViewHolder> {

    private Context             context;
    private ArrayList<Puzzle>   arrayRowGames;
    private PuzzleOnItemCount   puzzleOnItemCount;

    private int mSpacePosition = 0;

    public PuzzleGameAdapter(ArrayList<Puzzle> arrayRowGames) {
        this.arrayRowGames = arrayRowGames;
    }

    public void setPuzzleOnItemCount(PuzzleOnItemCount puzzleOnItemCount) {
        this.puzzleOnItemCount = puzzleOnItemCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        int size = ScreenUtils.getInstance().getWithd() / 3;

        ImageView mImageView = new ImageView(parent.getContext());
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(size,size));

        return new ViewHolder(mImageView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (arrayRowGames.get(position).getBitmap() != null) {

            Glide.with(holder.mImageView.getContext())
                    .load(arrayRowGames.get(position).getBitmap())
                    .into(holder.mImageView);
        }else {
            Glide.with(holder.mImageView.getContext())
                    .load(android.R.color.white)
                    .into(holder.mImageView);
        }
    }

    private void SwapData(int position1,int position2){
        Collections.swap(arrayRowGames, position1, position2);
        notifyItemChanged(position1);
        notifyItemChanged(position2);

        if (isComplete()){
            new AlertDialog.Builder(context)
                    .setTitle("Hoàn Thành")
                    .setMessage("Bạn đã hoàn thành xuất sắc trò chơi")
                    .setPositiveButton("Trở lại", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    private boolean isComplete(){
        int check = 0;
        for (int i = 0; i<  arrayRowGames.size(); i++){
            if (arrayRowGames.get(i).getIndex().equals(check+"")){
                check++;
            }
        }
        if (check==8){
            return true;
        }
        return false;
    }

    public int getSpacePosition(){
        for (int i=0; i < arrayRowGames.size();i++){
            if (arrayRowGames.get(i).getBitmap()==null){
                mSpacePosition = i;
            }
        }
        return mSpacePosition;
    }

    @Override
    public int getItemCount() {
        return arrayRowGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            mImageView = (ImageView)itemView;
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int Swipe = GameUtils.getPuzzleSwipeImage(getSpacePosition(),getAdapterPosition());

                    if (Swipe>0){
                        SwapData(getSpacePosition(),getAdapterPosition());
                        puzzleOnItemCount.onClick();
                    }
                }
            });
        }
    }
}

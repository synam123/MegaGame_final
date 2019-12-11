package com.poly.megagame.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.megagame.R;
import com.poly.megagame.model.altp.RequestionALTP;

public class AddRequestionALTPAdapter extends RecyclerView.Adapter<AddRequestionALTPAdapter.ViewHolder> {

    private RequestionALTP requestionALTP;

    public AddRequestionALTPAdapter(RequestionALTP requestionALTP) {
        this.requestionALTP = requestionALTP;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_add_requestions_altp,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (requestionALTP.getAnswer() == holder.getAdapterPosition()){
            holder.rbAnswer.setChecked(true);
        }else {
            holder.rbAnswer.setChecked(false);
        }

        holder.rbAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    int answerAfter = requestionALTP.getAnswer();

                    if (answerAfter == 4){
                        requestionALTP.setAnswer(holder.getAdapterPosition());
                        notifyItemChanged(holder.getAdapterPosition());
                        return;
                    }

                    requestionALTP.setAnswer(holder.getAdapterPosition());
                    notifyItemChanged(holder.getAdapterPosition());
                    notifyItemChanged(answerAfter);
                }
            }
        });

        holder.edtAnswer.setText(requestionALTP.getArrayAnswer().get(holder.getAdapterPosition()));
        holder.edtAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                requestionALTP.getArrayAnswer().set(holder.getAdapterPosition(),s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestionALTP.getArrayAnswer().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RadioButton rbAnswer;
        EditText    edtAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rbAnswer    = itemView.findViewById(R.id.rbAnswer);
            edtAnswer   = itemView.findViewById(R.id.edtAnswer);
        }
    }
}

package com.tekbeast.pollster.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tekbeast.pollster.R;
import com.tekbeast.pollster.Selects;
import com.tekbeast.pollster.model.Qus;

import java.util.List;

public class QusAdapter extends RecyclerView.Adapter<QusAdapter.QusViewHolder> {
    private List<Qus> qus;
    private int rowLayout;
    private Context context;

    public static class QusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout qusLayout;
        TextView idTitle;
        TextView qust;
        String id, questions;
        private Context context;



        public QusViewHolder(View v,Context context) {
            super(v);

            this.context=context;
            qusLayout = (LinearLayout) v.findViewById(R.id.qus_layout);
            idTitle = (TextView) v.findViewById(R.id.titleid);
            qust = (TextView) v.findViewById(R.id.qus);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
           id= idTitle.getText().toString();
           questions=qust.getText().toString();
            Intent intent= new Intent(this.context, Selects.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id",id);
            intent.putExtra("question",questions);
            this.context.startActivity(intent);


        }
    }
    public QusAdapter(List<Qus> qus, int rowLayout, Context context) {
        this.qus = qus;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public QusAdapter.QusViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new QusViewHolder(view,context);
    }


    @Override
    public void onBindViewHolder(QusViewHolder holder, final int position) {
        holder.idTitle.setText(qus.get(position).getId());
        holder.qust.setText(qus.get(position).getQuestion());


    }

    @Override
    public int getItemCount() {
        return qus.size();
    }

}

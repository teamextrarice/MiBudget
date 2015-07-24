package com.misys.teamextrarice.mibudget.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.misys.teamextrarice.mibudget.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by bozaldua on 7/24/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TextViewHolder>{
    List<String> details;

    public RVAdapter(List<String> details) {
        this.details = details;
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView text;



        TextViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView) itemView.findViewById(R.id.transaction_card);
//            text= (TextView) itemView.findViewById(R.id.transaction_details);
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_transaction_history, viewGroup, false);
            TextViewHolder tvh = new TextViewHolder(v);

            return tvh;
    }

    @Override
    public void onBindViewHolder(TextViewHolder textViewHolder, int i) {
        textViewHolder.text.setText(details.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}



package com.example.drugaddicationtherapysystem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;


class SuggestionsViewHolder extends RecyclerView.ViewHolder {
    TextView sender_name;
    TextView suggestion_message_d;
    TextView message_time;


    public SuggestionsViewHolder(@NonNull View itemView ){
        super(itemView);
        message_time = itemView.findViewById(R.id.text_message_time);
        sender_name = itemView.findViewById(R.id.text_message_name);
        suggestion_message_d = itemView.findViewById(R.id.text_message_body);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        message_time.setText(currentDateTimeString);
    }
}

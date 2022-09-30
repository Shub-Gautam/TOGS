package com.example.tog_s.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tog_s.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.myviewHolder>  {
    List<EventData> data ;
    onEventListner monEventListner ;

    public CardAdapter(List<EventData> data, onEventListner list) {
        this.data = data;
        this.monEventListner = list;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new myviewHolder(view,monEventListner);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
            holder.title.setText(data.get(position).getTitle());
        holder.location.setText(data.get(position).getVenue());
//        Glide.with(holder.title.getContext().load(data.get(position)))
//        holder.

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title ;
        TextView location ;
//        TextView eventId ;
        onEventListner onEventListner ;


        public myviewHolder(@NonNull View itemView,onEventListner onEventListner) {
            super(itemView);
            title=itemView.findViewById(R.id.CardTitle);
            location=itemView.findViewById(R.id.CardLocation);
//            eventId=itemView.findViewById(R.id.eventId);
            this.onEventListner = onEventListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEventListner.onNoteClick(getAbsoluteAdapterPosition());
        }
    }

    public interface onEventListner{
        void onNoteClick(int position);
    }

}

package com.example.tog_s.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tog_s.R;
import com.example.tog_s.response.Attendance;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.myvHolder>{

    List<Attendance> data ;

    public AttendanceAdapter(List<Attendance> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public AttendanceAdapter.myvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_item,parent,false);
        return new AttendanceAdapter.myvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.myvHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.loc.setText(data.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myvHolder extends RecyclerView.ViewHolder{

        TextView name ;
        TextView loc ;

        public myvHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CardName);
            loc=itemView.findViewById(R.id.CardLoc);
        }
    }

}

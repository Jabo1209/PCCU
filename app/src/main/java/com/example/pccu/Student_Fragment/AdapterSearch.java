package com.example.pccu.Student_Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pccu.R;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    ArrayList<FirebaseBean>firebaseBeans;
    Context context;

    public AdapterSearch(ArrayList<FirebaseBean> arrayList, Context applicationContext) {
        this.firebaseBeans=arrayList;
        context=applicationContext;
    }

    @NonNull
    @Override
    public AdapterSearch.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_row,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearch.ViewHolder viewHolder, int i) {
        viewHolder.Title.setText(firebaseBeans.get(i).getTitle());
        viewHolder.Room.setText(firebaseBeans.get(i).getRoom());
    }

    @Override
    public int getItemCount() {
        return firebaseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title,Room;

        public ViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.title);
            Room = (TextView) itemView.findViewById(R.id.room);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, firebaseBeans.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, FirebaseBean data);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

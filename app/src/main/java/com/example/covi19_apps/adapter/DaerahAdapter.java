package com.example.covi19_apps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covi19_apps.R;
import com.example.covi19_apps.model.ResultsItem;

import java.util.List;

public class DaerahAdapter extends RecyclerView.Adapter<DaerahAdapter.ViewHolder> {

    List<ResultsItem> itemList;
    Context context;

    public DaerahAdapter(List<ResultsItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daerah,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultsItem data = itemList.get(position);

        holder.title.setText(data.getKabupatenKota());
        holder.tv_positif.setText(data.getPositif());
        holder.tv_meninggal.setText(data.getCovidMeninggal());
        holder.tv_sembuh.setText(data.getCovidSembuh());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView tv_sembuh,tv_positif,tv_meninggal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            tv_positif = itemView.findViewById(R.id.tv_Positif);
            tv_meninggal = itemView.findViewById(R.id.tv_meninggal);
            tv_sembuh = itemView.findViewById(R.id.tv_sembuh);
        }
    }
}

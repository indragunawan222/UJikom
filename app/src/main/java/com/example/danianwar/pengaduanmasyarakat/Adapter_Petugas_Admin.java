package com.example.danianwar.pengaduanmasyarakat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Petugas_Admin extends RecyclerView.Adapter<Adapter_Petugas_Admin.ViewHolder> {

    String name;

    private List<Entity_Petugas_Admin> list;
    private Context mContext;

    public Adapter_Petugas_Admin(Context mContext, List<Entity_Petugas_Admin> list){
        this.mContext = mContext;
        this.list = list;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameAdmin;
        LinearLayout lr;

        public ViewHolder(View v){
            super(v);
            nameAdmin = (TextView)v.findViewById(R.id.tv_name_recycler);

            lr = (LinearLayout)v.findViewById(R.id.linear1_container);
        }
    }

    @Override
    public Adapter_Petugas_Admin.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_petugas,
                parent,
                false);

        Adapter_Petugas_Admin.ViewHolder vh = new Adapter_Petugas_Admin.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(Adapter_Petugas_Admin.ViewHolder holder, final int position) {
        Entity_Petugas_Admin petugas = list.get(position);

        holder.nameAdmin.setText(String.valueOf(petugas.getNama_petugas()));

        holder.lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, list.get(position).getNama_petugas(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

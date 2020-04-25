package com.example.danianwar.pengaduanmasyarakat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Adapter_Tanggapan_Petugas extends RecyclerView.Adapter<Adapter_Tanggapan_Petugas.ViewHolder> {

    String id,nik,isi,foto;

    private List<Entity_Tanggapan> list;
    private Context mContext;

    FirebaseAuth mAuth,refMas;
    String user;

    DatabaseReference databaseReference;

    public Adapter_Tanggapan_Petugas(Context mContext, List<Entity_Tanggapan> list){
        this.mContext = mContext;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public EditText txtIsi;

        Button btnAdd,btnUp;



        public ViewHolder(View v){
            super(v);
            txtIsi = (EditText) v.findViewById(R.id.text2);

            btnAdd = (Button)v.findViewById(R.id.btn_tanggapan_tambah);
            btnUp = (Button)v.findViewById(R.id.btn_tanggapan_edit);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activitiy_tangapan,parent,
                false);


        ViewHolder vh = new ViewHolder(v);

        return vh;

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Entity_Tanggapan info = list.get(position);


        holder.txtIsi.setText(String.valueOf(info.getTanggapan()));


    }


    //    ini untuk inisialisasi ukuran dari list/arraylist/recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }


}

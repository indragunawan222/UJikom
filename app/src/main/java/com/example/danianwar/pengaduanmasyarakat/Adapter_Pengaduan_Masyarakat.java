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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Adapter_Pengaduan_Masyarakat extends RecyclerView.Adapter<Adapter_Pengaduan_Masyarakat.ViewHolder> {

    String id,nik,isi,foto;

    private List<Entity_Pengaduan_Masyarakat> list;
    private Context mContext;


    private StorageReference mStorageRef;

    private Button gambarbtn;
    private ImageView gambar;


    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;
    private Uri imgUri;

    public Adapter_Pengaduan_Masyarakat(Context mContext, List<Entity_Pengaduan_Masyarakat> list){
        this.mContext = mContext;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView txtIsi;
        LinearLayout lr;
        Button btnChange;
        ImageView img;


        public ViewHolder(View v){
            super(v);
            txtIsi = (TextView)v.findViewById(R.id.tv_isi);

            img = (ImageView)v.findViewById(R.id.iv_pengaduan);

            btnChange = (Button)v.findViewById(R.id.btn_change);

            lr = (LinearLayout)v.findViewById(R.id.linearlayout1);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_pengaduan,parent,
                false);


        ViewHolder vh = new ViewHolder(v);

        return vh;

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Entity_Pengaduan_Masyarakat info = list.get(position);


        holder.txtIsi.setText(String.valueOf(info.getIsi_laporan()));

        Glide.with(mContext)
                .load(info.getFoto())
                .into(holder.img);


        mStorageRef = FirebaseStorage.getInstance().getReference();


        holder.lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                bundle.putString("foto",list.get(position).getFoto());
                bundle.putString("isi",list.get(position).getIsi_laporan());

                Intent inten = new Intent(mContext,PengaduanDiTanggapi.class);
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten.putExtras(bundle);

                mContext.startActivity(inten);
            }
        });

        holder.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entity_Pengaduan_Masyarakat info = list.get(position);
                String id = info.getIdPengaduan();
                nik = info.getNik();
                isi = info.getIsi_laporan();
                foto = info.getFoto();
                showDialogWhatToDo(id);
            }
        });




    }


    //    ini untuk inisialisasi ukuran dari list/arraylist/recyclerview
    @Override
    public int getItemCount() {
        return list.size();
    }





    private void showDialogWhatToDo(final String idPengaduan) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialogView = inflater.inflate(R.layout.layout_update_or_delete,null);
        dialogBuilder.setView(dialogView);

        Button btnDoUpdate = (Button) dialogView.findViewById(R.id.doUpdate);
        Button btnDoDelete = (Button) dialogView.findViewById(R.id.doDelete);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnDoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogUpdateInfo(idPengaduan);
                alertDialog.dismiss();
            }
        });

        btnDoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteInfo(idPengaduan);
                alertDialog.dismiss();
            }
        });

    }
    //
//
    private void deleteInfo(String idPengaduan) {
        DatabaseReference dPengaduan = FirebaseDatabase.getInstance().getReference("Pengaduan").child(idPengaduan);
        dPengaduan.removeValue();
        Toast.makeText(mContext, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show();

    }


    private void showDialogUpdateInfo(final String idPengaduan) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View dialogView = inflater.inflate(R.layout.layout_edit_pengaduan,null);
        dialogBuilder.setView(dialogView);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.editPengaduan);

        dialogBuilder.setTitle("Update Pengaduan ");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etAbout = (EditText)dialogView.findViewById(R.id.et_isi);
        etAbout.setText(isi);


        gambar = dialogView.findViewById(R.id.imageContainer);
        gambarbtn = dialogView.findViewById(R.id.btn_image);

        gambarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Fitur Belum Tersedia", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= 22) {
//                    checkAndRequestForPermissions();
                } else {
//                    openGallery();
                }
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String perinci = etAbout.getText().toString();

//                updateInfo(idInfo , pengirim ,Tanggal, penjudul,perinci);
                alertDialog.dismiss();
            }
        });
    }

    private boolean updateInfo(String idInfo, String pengirim, String tanggal, String penjudul, String perinci){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Info").child(idInfo);
//        Entitiy_smartinfo info = new Entitiy_smartinfo(idInfo , pengirim, penjudul,tanggal,perinci);
//        databaseReference.setValue(info);

        Toast.makeText(mContext, "Behasil Update Data", Toast.LENGTH_SHORT).show();

        return true;
    }


}

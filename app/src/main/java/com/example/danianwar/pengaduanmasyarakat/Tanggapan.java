package com.example.danianwar.pengaduanmasyarakat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class Tanggapan extends AppCompatActivity {
    FirebaseAuth mAuth,refMas;
    String user;
    ImageView iv;
    TextView txt;
    EditText edt;
    Button btnT,btnE,btnH;
    Bundle bundle;
    String idPengaduan,idTanggapan;

    DatabaseReference databaseReference,tanggapan;

    private Adapter_Tanggapan_Petugas adapter;

    private List<Entity_Tanggapan> list;

    View view;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_tangapan);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();
        String idUser = String.valueOf(user);
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Masyrakat").child(user);
        String idUser2 = databaseReference.toString();
        tanggapan = FirebaseDatabase.getInstance().getReference("Tanggapan");

        list = new ArrayList<>();

        iv = findViewById(R.id.imageView2);
        txt = findViewById(R.id.txt1);
        btnT = (Button) findViewById(R.id.btn_tanggapan_tambah);
        btnE = (Button) findViewById(R.id.btn_tanggapan_edit);
        btnH = (Button) findViewById(R.id.btn_tanggapan_hapus);
        edt = findViewById(R.id.text2);
        bundle = getIntent().getExtras();

        if(getIntent().getExtras() != null){

            if(bundle != null){
                Glide.with(Tanggapan.this)
                        .load(bundle.getString("foto"))
                        .into(iv);
                txt.setText(bundle.getString("isi"));
                idPengaduan = bundle.getString("idPengaduan");
            } else{
                Glide.with(Tanggapan.this)
                        .load(getIntent().getStringExtra("foto"))
                        .into(iv);
                txt.setText(getIntent().getStringExtra("isi"));
                idPengaduan = getIntent().getStringExtra("idPengaduan");
            }

        }


//        if (idUser == idUser2){
//            btnT.setVisibility(View.GONE);
//            btnE.setVisibility(GONE);
//            btnH.setVisibility(GONE);
//            edt.setEnabled(false);
//        } else{
//            btnE.setVisibility(View.VISIBLE);
//            btnT.setVisibility(View.VISIBLE);
//            btnH.setVisibility(View.VISIBLE);
//            edt.setEnabled(true);
//        }

//
//
//        if (edt.getText().toString() == "" || edt.getText().toString() == " " || edt.getText().toString() == null){
//            btnT.setEnabled(true);
//            btnE.setEnabled(false);
//            btnH.setEnabled(false);
//        } else{
//            btnT.setEnabled(false);
//            btnE.setEnabled(true);
//            btnH.setEnabled(true);
//        }



        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAddEvent();
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callUpdateEvent();
            }
        });

        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDelEvent();
            }
        });


    }


    private void callAddEvent(){

        String isiTanggapan = edt.getText().toString();
        DatabaseReference tanggapan = FirebaseDatabase.getInstance().getReference("Tanggapan");
        String idInfo = tanggapan.push().getKey();
        Entity_Tanggapan Info = new Entity_Tanggapan(idInfo,idPengaduan , isiTanggapan, user);
        tanggapan.child(idInfo).setValue(Info);

        Toast.makeText(Tanggapan.this, "berhasil", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Tanggapan.this,PengaduanPetugas.class));

    }

    private void callUpdateEvent(){
        String isiTanggapan = edt.getText().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Tanggapan").child(idTanggapan);
        Entity_Tanggapan info = new Entity_Tanggapan(idTanggapan , idPengaduan, isiTanggapan,user);
        databaseReference.setValue(info);
        Toast.makeText(Tanggapan.this, "berhasil", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Tanggapan.this,PengaduanPetugas.class));
    }

    private void callDelEvent(){
        DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("Tanggapan").child(idTanggapan);
        dInfo.removeValue();
        Toast.makeText(Tanggapan.this, "berhasil", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Tanggapan.this,PengaduanPetugas.class));
    }


    @Override
    public void onStart() {
        super.onStart();

        edt = findViewById(R.id.text2);

        list = new ArrayList<>();

        tanggapan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()) {
                    Entity_Tanggapan info = infoSnapShot.getValue(Entity_Tanggapan.class);
                    edt.setText(info.getTanggapan());
                    idTanggapan = info.getIdTanggapan();
                    list.add(info);
                }
                adapter = new Adapter_Tanggapan_Petugas(Tanggapan.this, list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}

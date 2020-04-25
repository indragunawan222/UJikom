package com.example.danianwar.pengaduanmasyarakat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PengaduanDiTanggapi extends AppCompatActivity {

    FirebaseAuth mAuth,refMas;
    String user;
    ImageView iv;
    TextView txt;
    EditText edt;
    Bundle bundle;
    String idPengaduan,idTanggapan;

    DatabaseReference databaseReference,tanggapan;

    private Adapter_Tanggapan_Petugas adapter;

    private List<Entity_Tanggapan> list;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_di_tanggapi);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();
        String idUser = String.valueOf(user);
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Masyrakat").child(user);
        String idUser2 = databaseReference.toString();
        tanggapan = FirebaseDatabase.getInstance().getReference("Tanggapan");

        list = new ArrayList<>();

        iv = findViewById(R.id.imageView2);
        txt = findViewById(R.id.txt1);
        edt = findViewById(R.id.text2);
        bundle = getIntent().getExtras();

        if(getIntent().getExtras() != null){

            if(bundle != null){
                Glide.with(PengaduanDiTanggapi.this)
                        .load(bundle.getString("foto"))
                        .into(iv);
                txt.setText(bundle.getString("isi"));
                idPengaduan = bundle.getString("idPengaduan");
            } else{
                Glide.with(PengaduanDiTanggapi.this)
                        .load(getIntent().getStringExtra("foto"))
                        .into(iv);
                txt.setText(getIntent().getStringExtra("isi"));
                idPengaduan = getIntent().getStringExtra("idPengaduan");
            }

        }


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
                adapter = new Adapter_Tanggapan_Petugas(PengaduanDiTanggapi.this, list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}

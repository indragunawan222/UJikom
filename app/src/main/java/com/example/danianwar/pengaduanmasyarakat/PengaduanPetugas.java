package com.example.danianwar.pengaduanmasyarakat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PengaduanPetugas extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Pengaduan_Masyarakat> list;
    private List<Entity_Masyarakat> listMas;
    private Adapter_Pengaduan_Petugas adapter;
    DatabaseReference databaseReference;
    private StorageReference mStorageRef;

    private Button gambarbtn;
    private ImageView gambar;


    public static final int pReqCode = 1;
    public static final int REQUESCODE = 1;
    private Uri imgUri;

    private String refMasyrakat;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan_petugas);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_pengaduan);
        list = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Pengaduan");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Button log = (Button) findViewById(R.id.btn_logout);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = findViewById(R.id.rv_pengaduan);
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(PengaduanPetugas.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        listMas = new ArrayList<>();
        list = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()) {
                    Entity_Pengaduan_Masyarakat info = infoSnapShot.getValue(Entity_Pengaduan_Masyarakat.class);
                    list.add(info);
                }

                adapter = new Adapter_Pengaduan_Petugas(PengaduanPetugas.this, list);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

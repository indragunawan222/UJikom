package com.example.danianwar.pengaduanmasyarakat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PetugasActivity extends AppCompatActivity {

    public static final String ID_PETUGAS = "idPetugas";
    public static final String NAMA_PETUGAS = "nama_petugas";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Entity_Petugas_Admin> list;
    private Adapter_Petugas_Admin adapter;

    DatabaseReference databaseReference;

    private FloatingActionButton fab;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_petugas_admin);
        list = new ArrayList<>();
        fab = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Petugas");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mRecyclerView = findViewById(R.id.rv_petugas_admin );
        //        ini untuk menetapkan ukuran recyclerview
        if (mRecyclerView != null){
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(PetugasActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot infoSnapShot : dataSnapshot.getChildren()){
                    Entity_Petugas_Admin petugas = infoSnapShot.getValue(Entity_Petugas_Admin.class);
                    list.add(petugas);
                }

                adapter = new Adapter_Petugas_Admin(PetugasActivity.this,list);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showDialogAdd(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(PetugasActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_tambah_petugas, null);
        dialogBuilder.setView(dialogView);


        //set judul alert dialog agar tidak bingung
        dialogBuilder.setTitle("Tambah Petugas");
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText etNama = (EditText)dialogView.findViewById(R.id.et_nama_petugas);
        final EditText etUsername = (EditText)dialogView.findViewById(R.id.et_username_petugas);
        final EditText etPassword = (EditText) dialogView.findViewById(R.id.et_pass_petugas);
        final EditText etTelp = (EditText)dialogView.findViewById(R.id.et_telp_petugas);
        Button tambahGuru = (Button) dialogView.findViewById(R.id.btntambahPetugas);

        tambahGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nams = etNama.getText().toString();
                final String username = etUsername.getText().toString()+"@gmail.com";
                final String pass = etPassword.getText().toString();
                final String telp = etTelp.getText().toString();

                //validasi email dan password
                // jika email kosong
                if (username.isEmpty()){
                    Toast.makeText(PetugasActivity.this, "username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                //jika password kurang dari 6 karakter
                else if (username.length() == 18){
                    Toast.makeText(PetugasActivity.this, "nip tidak 18 digit", Toast.LENGTH_SHORT).show();
                }
                // jika password kosong
                else if (pass.isEmpty()){
                    Toast.makeText(PetugasActivity.this, "password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                //jika password kurang dari 6 karakter
                else if (pass.length() < 6){
                    Toast.makeText(PetugasActivity.this, "password kurang dari 8", Toast.LENGTH_SHORT).show();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(username, pass)
                            .addOnCompleteListener(PetugasActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(PetugasActivity.this,
                                                "Register gagal karena " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        //jika sukses akan menuju ke login activity
                                        String id = mAuth.getCurrentUser().getUid();
                                        Entity_Petugas_Admin petugas = new Entity_Petugas_Admin(id, nams, username, pass, telp);
                                        databaseReference.child(id).setValue(petugas);
                                        Toast.makeText(PetugasActivity.this,
                                                "Register berhasil ",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

                alertDialog.dismiss();
            }
        });

    }

}

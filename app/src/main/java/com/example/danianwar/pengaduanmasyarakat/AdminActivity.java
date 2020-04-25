package com.example.danianwar.pengaduanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    Button bPetugas,bMasyarakat,bPengaduan,bAdmin,bTanggapan;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAuth = FirebaseAuth.getInstance();
        buttonHomeAdmin();

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

    public void buttonHomeAdmin(){
        bPetugas = (Button)findViewById(R.id.btnPetugasAdmin);
        bMasyarakat = (Button)findViewById(R.id.btnMasyarakatAdmin);
        bPengaduan = (Button)findViewById(R.id.btnPengaduanAdmin);
        bAdmin = (Button)findViewById(R.id.btnAdminAdmin);
        bTanggapan = (Button)findViewById(R.id.btnTanggapanAdmin);

        bPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, PetugasActivity.class));

            }
        });

        bMasyarakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, MasyarakatActivity.class));
            }
        });

        bPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, PengaduanActivity.class));
            }
        });

        bAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, UserActivity.class));
            }
        });

        bTanggapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, TanggapanActivity.class));
            }
        });

    }

}

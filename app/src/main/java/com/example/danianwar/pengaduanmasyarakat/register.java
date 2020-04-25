package com.example.danianwar.pengaduanmasyarakat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User").child("Masyarakat");

        Button btn = (Button) findViewById(R.id.btn_register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallEventRegister();
            }
        });
    }
    private void CallEventRegister(){
        final EditText etNama = (EditText)findViewById(R.id.txt_nama);
        final EditText etUsername = (EditText)findViewById(R.id.txt_username);
        final EditText etPassword = (EditText)findViewById(R.id.txt_password);
        final EditText etTelp = (EditText)findViewById(R.id.tlp);
        Button tambahMasyarakat = (Button)findViewById(R.id.btn_register);

        tambahMasyarakat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nik = etUsername.getText().toString();
                final String nams = etNama.getText().toString();
                final String username = etUsername.getText().toString()+"@gmail.com";
                final String pass = etPassword.getText().toString();
                final String telp = etTelp.getText().toString();

                //validasi email dan password
                // jika email kosong
                if (username.isEmpty()){
                    Toast.makeText(register.this,"username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                //jika password kurang dari 6 karakter
                else if (username.length() == 16){
                    Toast.makeText(register.this, "nik tidak 16 digit", Toast.LENGTH_SHORT).show();
                }
                // jika password kosong
                else if (pass.isEmpty()){
                    Toast.makeText(register.this, "password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                //jika password kurang dari 6 karakter
                else if (pass.length() < 6){
                    Toast.makeText(register.this, "password kurang dari 8", Toast.LENGTH_SHORT).show();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(username, pass)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(register.this,
                                                "Register gagal karena " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        //jika sukses akan menuju ke login activity
                                        String id = mAuth.getCurrentUser().getUid();
                                        Entity_Masyarakat masyarakat = new Entity_Masyarakat(id,nik, nams, username, pass, telp);
                                        databaseReference.child(id).setValue(masyarakat);
                                        Toast.makeText(register.this,
                                                "Register berhasil ",
                                                Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), Activity_Login.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }


            }
        });
    }
}

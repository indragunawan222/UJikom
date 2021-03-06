package com.example.danianwar.pengaduanmasyarakat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;

    private FirebaseAuth mAuth;
    private DatabaseReference refMasyrakat,refAdmin,refPetugas;
    String users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formlogin);
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.txt_username);
        edtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLoginEvent();
                Intent intent = new Intent(getApplicationContext(), Home_Fragment.class);
                startActivity(intent);
            }
        });
    }

    private void callLoginEvent(){
        String emails = edtEmail.getText().toString()+"@gmail.com";
        String pass = edtPassword.getText().toString();


        mAuth.signInWithEmailAndPassword(emails, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("", "signInWithEmail:success");
                    users = mAuth.getCurrentUser().getUid();
                    refMasyrakat = FirebaseDatabase.getInstance().getReference("User").child("Masyarakat").child(users);
                    refAdmin = FirebaseDatabase.getInstance().getReference("User").child("Admin").child(users);
                    refPetugas = FirebaseDatabase.getInstance().getReference("User").child("Petugas").child(users);

                    String admin = refAdmin.toString();

                    if (edtEmail.getText().length()== 16){
                        startActivity(new Intent(Activity_Login.this, MainActivity.class));
                        Toast.makeText(Activity_Login.this, "masyarakat", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if(edtEmail.getText().length() == 5){
                        startActivity(new Intent(Activity_Login.this, AdminActivity.class));
                        Toast.makeText(Activity_Login.this, "admin", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if(edtEmail.getText().length() == 18){
                        startActivity(new Intent(Activity_Login.this, PengaduanPetugas.class));
                        Toast.makeText(Activity_Login.this, "petugas", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("", "signInWithEmail:failure", task.getException());
                    Toast.makeText(Activity_Login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Log.d("anjing",user.getEmail().length()+"");
            if (user.getEmail().length() == 15){
                startActivity(new Intent(Activity_Login.this,AdminActivity.class));
                Toast.makeText(Activity_Login.this, "admin", Toast.LENGTH_SHORT).show();
                finish();
            } else if(user.getEmail().length() == 28){
                startActivity(new Intent(Activity_Login.this,PengaduanPetugas.class));
                Toast.makeText(Activity_Login.this, "petugas", Toast.LENGTH_SHORT).show();
                finish();
            } else if(user.getEmail().length() == 26){
                startActivity(new Intent(Activity_Login.this,MainActivity.class));
                Toast.makeText(Activity_Login.this, "masyarakat", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}

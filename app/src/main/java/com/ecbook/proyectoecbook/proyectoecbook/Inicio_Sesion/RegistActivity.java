package com.ecbook.proyectoecbook.proyectoecbook.Inicio_Sesion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ecbook.proyectoecbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistActivity extends AppCompatActivity {

    private EditText newuser;
    private EditText email;
    private EditText contra;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        firebaseAuth = FirebaseAuth.getInstance();

        newuser = (EditText) findViewById(R.id.txt_newuser);
        email =(EditText)findViewById(R.id.txtEmail);
        contra = (EditText)findViewById(R.id.txtContraseña);

        mProgress = new ProgressDialog(this);
        //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(getBaseContext(), "Usuario creado", Toast.LENGTH_LONG).show();

                    finish();
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        };
    }

    public void btnSignin_click(View view){
        final String username = newuser.getText().toString().trim();

        final ProgressDialog progressDialog = ProgressDialog.show(RegistActivity.this, " Por favor espere... ", " Cargando... ", true);
        (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), contra.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    String user_id = firebaseAuth.getCurrentUser().getUid();

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username).build();
                    user.updateProfile(profileUpdates);

                    FirebaseAuth.getInstance().signOut();
                    firebaseAuth.signInWithEmailAndPassword(email.toString(), contra.toString());


                    Toast.makeText(RegistActivity.this, "Se ha completado el Registro: " + user_id, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegistActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    Log.e("Error al Registrase", task.getException().toString());
                    Toast.makeText(RegistActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
    */
}

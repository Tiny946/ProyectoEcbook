package com.ecbook.proyectoecbook.proyectoecbook.Inicio_Sesion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecbook.proyectoecbook.proyectoecbook.MainActivity;
import com.ecbook.proyectoecbook.proyectoecbook.MainActivityTabs;
import com.ecbook.proyectoecbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {



    private EditText emailLogin;
    private EditText contraLogin;
    private TextView tvregistrarse;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnregistrase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // si el usuario esta registrado correctamente finaliza la actividad volviendo a la principal
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    finish();
                }

            }
        };

        emailLogin = (EditText)findViewById(R.id.txtEmail);
        contraLogin = (EditText)findViewById(R.id.txtContraseña);
        firebaseAuth = FirebaseAuth.getInstance();
        //btnregistrase = (Button) findViewById(R.id.buttonRegister);
        tvregistrarse = (TextView) findViewById(R.id.textViewRegistrarse);

        tvregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrase();
            }
        });
    }


    public void btnLogin_click(View view){
        String user,pass;

        // recoge el texto que introduce el usuario
        user = emailLogin.getText().toString();
        pass = contraLogin.getText().toString();

        if (pass.equals("") || user.equals("")) {
            Toast.makeText(LoginActivity.this, "por favor rellene los campos", Toast.LENGTH_SHORT).show();

        }else if(user.toString().equals("admin@admin.com") && pass.toString().equals("admin")){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            Toast.makeText(LoginActivity.this, "Bienvenido Admin", Toast.LENGTH_SHORT).show();
            startActivity(i);

        } else {
            //firebaseAuth.signInWithCredential()
            firebaseAuth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            // controla los posibles errores que pueden salir
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Exito al Loguearse", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(LoginActivity.this, MainActivityTabs.class);
                                i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                startActivity(i);
                            }else{
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        /*
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, " Por favor espere... ", " Cargando... ", true);
        (firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(), contraLogin.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Exito al Loguearse", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, MainActivityUser.class);
                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);
                }else{
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        */
    }


    public void registrase(){
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
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

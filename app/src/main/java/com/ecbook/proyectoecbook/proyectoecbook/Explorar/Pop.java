package com.ecbook.proyectoecbook.proyectoecbook.Explorar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecbook.proyectoecbook.R;

public class Pop extends AppCompatActivity {
    TextView tvEmail, tvTelf;
    ImageView ivX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        Intent intent = getIntent();

        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvTelf = (TextView)findViewById(R.id.tvTelf);
        ivX = (ImageView)findViewById(R.id.ivX);

        tvEmail.setText(intent.getStringExtra(ExplorarFragment.email));
        tvTelf.setText(intent.getStringExtra(ExplorarFragment.telefono));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(heigth*.6));

        ivX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent i = new Intent(Pop.this, ExplorarFragment.class);
                startActivity(i);*/
                Pop.this.finish();
            }
        });

        /*tvTelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamar(view);
            }
        });*/
    }

    /*public void llamar(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(String.valueOf(tvTelf), tvTelf.toString(), null));
        startActivity(intent);
    }*/
}

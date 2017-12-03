package com.ecbook.proyectoecbook.proyectoecbook.Inicio_Sesion;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecbook.proyectoecbook.R;

public class spashscreen extends AppCompatActivity {
    TextView textView1;
    ImageView imageView;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Animation animR = AnimationUtils.loadAnimation(this, R.anim.circular);
        anim.reset();
       /* LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);*/

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        animR = AnimationUtils.loadAnimation(this, R.anim.circular);
        anim.reset();
        textView1 = (TextView) findViewById(R.id.textView1);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView1.clearAnimation();
        textView1.startAnimation(anim2);

        imageView.clearAnimation();
        imageView.startAnimation(animR);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 4500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(spashscreen.this,
                            LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    spashscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    spashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
}

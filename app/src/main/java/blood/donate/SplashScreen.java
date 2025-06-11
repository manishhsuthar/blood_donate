package blood.donate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;

import blood.donate.Activity.HomeActivity;
import blood.donate.Activity.MainActivity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sp;
    ImageView iv_splash;
    private static int Splash_Time = 2200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);
        boolean handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Splash_Time);

                    if (sp.getString(Constans.USERNAME, "").equalsIgnoreCase("")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Splash_Time);


        iv_splash = (ImageView) findViewById(R.id.splash_iv);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(SplashScreen.this, android.R.anim.accelerate_interpolator);
        iv_splash.startAnimation(alphaAnimation);

    }
}

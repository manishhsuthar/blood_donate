package blood.donate.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import blood.donate.ApActivity;
import blood.donate.Fragment.ChangePassFragment;
import blood.donate.Constans;
import blood.donate.Fragment.ViewProfileFragment;
import blood.donate.R;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup grp_rg;
    RadioButton grp_Apositive,grp_Anegative,grp_Bpositive,grp_Bnegative
            ,grp_Abpositive,grp_Abnegative,grp_Opositive,grp_Onegative;

    SharedPreferences sp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sp = getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        db = openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        grp_rg = (RadioGroup) findViewById(R.id.grp_rg);
        grp_Apositive = (RadioButton) findViewById(R.id.grp_APositive);
        grp_Anegative = (RadioButton) findViewById(R.id.grp_ANegative);
        grp_Bpositive = (RadioButton) findViewById(R.id.grp_BPositive);
        grp_Bnegative = (RadioButton) findViewById(R.id.grp_BNegative);
        grp_Abpositive = (RadioButton) findViewById(R.id.grp_ABPositive);
        grp_Abnegative = (RadioButton) findViewById(R.id.grp_ABNegative);
        grp_Opositive = (RadioButton) findViewById(R.id.grp_OPositive);
        grp_Onegative = (RadioButton) findViewById(R.id.grp_ONegative);

        grp_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                sp.edit().putString(Constans.BLOOD_GROUP,rb.getText().toString()).commit();
                startActivity(new Intent(HomeActivity.this, ApActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() > 1) {
                // If there are back-stack entries, leave the FragmentActivity
                // implementation take care of them.
                manager.popBackStack();

            } else {
                // Otherwise, ask user if he wants to leave :)
                new AlertDialog.Builder(this)
                        .setTitle("Blood Donate")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                HomeActivity.super.onBackPressed();
                            }
                        }).create().show();
            }
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home_home) {
            // Handle the camera action
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
        } else if (id == R.id.nav_change_password) {
            Fragment fragment = new ChangePassFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_home, fragment).addToBackStack("HomeActivity").commit();

        } else if (id == R.id.nav_view_profile) {

            Fragment fragment = new ViewProfileFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_home, fragment).addToBackStack("HomeActivity").commit();

        } else if (id == R.id.nav_logout) {
            sp.edit().clear().commit();// all pref clean
            Toast.makeText(HomeActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
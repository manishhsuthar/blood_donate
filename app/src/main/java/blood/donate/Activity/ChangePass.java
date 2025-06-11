package blood.donate.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import blood.donate.Constans;
import blood.donate.R;

public class ChangePass extends AppCompatActivity {

    EditText edtnewpass, edtnewcpass;
    Button btnsave;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        sp = getSharedPreferences(Constans.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);
        edtnewpass = (EditText) findViewById(R.id.change_pass_edt_new_pass);
        edtnewcpass = (EditText) findViewById(R.id.change_pass_edt_cnew_pass);
        btnsave = (Button) findViewById(R.id.change_pass_btn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass_str = edtnewpass.getText().toString();
                String cpass_str = edtnewcpass.getText().toString();
                if (edtnewpass.getText().toString().equalsIgnoreCase("")) {
                    edtnewpass.setError("New Password Required");
                } else if (edtnewcpass.getText().toString().equalsIgnoreCase("")) {
                    edtnewcpass.setError("Confirm Password Required");
                } else if (!edtnewpass.getText().toString().matches(edtnewcpass.getText().toString())) {
                    edtnewcpass.setError("Password Does Not Match");
                } else {
                    Cursor c = db.rawQuery("select * from register where hint='"
                            + sp.getString(Constans.HINT, "") + "'", null);
                    if (c.getCount() > 0) {
                        String QueryUpdate = "update register set pass='" + edtnewpass.getText().toString() + "' where hint='" + sp.getString(Constans.HINT,"") + "'";
                        db.execSQL(QueryUpdate);
                        Toast.makeText(getApplicationContext(), "Password Change Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Old Password Is Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}

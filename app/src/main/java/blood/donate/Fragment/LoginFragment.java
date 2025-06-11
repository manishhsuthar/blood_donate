package blood.donate.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import blood.donate.Activity.HomeActivity;
import blood.donate.Constans;
import blood.donate.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText edtemail, edtpass;
    Button btnlogin;
    SharedPreferences sp;
    SQLiteDatabase db;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        edtemail = (EditText) view.findViewById(R.id.login_email);
        edtpass = (EditText) view.findViewById(R.id.login_pass);
        btnlogin = (Button) view.findViewById(R.id.login_btn);
        sp = getActivity().getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtemail.getText().toString().equalsIgnoreCase("")){

                    edtemail.setError("Enter Email ID");
                }
                String email = edtemail.getText().toString();
                if (email.matches(emailPattern)) {
                } else {
                    Toast.makeText(getActivity(), "In Valid Email", Toast.LENGTH_SHORT).show();
                }
                if(edtpass.getText().toString().equalsIgnoreCase("")){
                    edtpass.setError("Enter Password");
                }
                else{

                    Cursor c = db.rawQuery("select * from register where email='"
                            + edtemail.getText().toString() + "' and pass='"
                            + edtpass.getText().toString() + "'", null);

                    if (c.getCount()>0) {
                        String name=edtemail.getText().toString();
                        sp.edit().putString(Constans.USERNAME,name).commit();
                        startActivity(new Intent(getActivity(), HomeActivity.class));
                    }
                    else{
                        Toast.makeText(getActivity(), "Login Unsuccessfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

}

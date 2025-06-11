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

import blood.donate.Activity.ChangePass;
import blood.donate.Constans;
import blood.donate.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {

    EditText edthint;
    Button btnnext;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        sp = getActivity().getSharedPreferences(Constans.PREF,Context.MODE_PRIVATE);
        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        edthint = (EditText) view.findViewById(R.id.forget_pass_edt_hint);
        btnnext = (Button) view.findViewById(R.id.forget_pass_btn);


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edthint.getText().toString().equals("")){
                    edthint.setError("Enter Hint");
                }
                else{

                    Cursor c = db.rawQuery("select * from register where hint='"
                            + edthint.getText().toString() + "'", null);

                    if (c.getCount()>0) {
                        sp.edit().putString(Constans.HINT,edthint.getText().toString()).commit();
                        startActivity(new Intent(getActivity(), ChangePass.class));

                    }
                    else{
                        Toast.makeText(getActivity(), "Hint Does Not Match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    return view;
    }
}

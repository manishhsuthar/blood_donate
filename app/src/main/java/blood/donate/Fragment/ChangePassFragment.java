package blood.donate.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import blood.donate.R;

public class ChangePassFragment extends Fragment {

    EditText edtoldpass,edtnewpass,edtnewcpass;
    Button btnsave;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);

        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);;
        edtoldpass = (EditText)view.findViewById(R.id.change_pass_fragment_edt_old_pass);
        edtnewpass = (EditText)view.findViewById(R.id.change_pass_fragment_edt_new_pass);
        edtnewcpass = (EditText)view.findViewById(R.id.change_pass_fragment_edt_cnew_pass);
        btnsave = (Button)view.findViewById(R.id.change_pass_fragment_btn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass_str = edtnewpass.getText().toString();
                String cpass_str = edtnewcpass.getText().toString();
                if(pass_str.matches(cpass_str)){

                }
                else{
                    Toast.makeText(getActivity(), "Password Is Not Match", Toast.LENGTH_SHORT).show();
                    return;
                }


                Cursor c = db.rawQuery("select * from register where pass='"
                        + edtoldpass.getText().toString() + "'", null);

                if (c.moveToFirst()) {

                }
                else{
                    Toast.makeText(getActivity(), "Old Password Is Wrong", Toast.LENGTH_SHORT).show();
                }


                if(edtoldpass.getText().toString().equalsIgnoreCase("")){
                    edtoldpass.setError("Old Password Empty");
                }
                if(edtnewpass.getText().toString().equalsIgnoreCase("")){
                    edtnewpass.setError("New Password Empty");
                }

                else {

                    String QuerySelect = "select * from register where pass='" + edtoldpass.getText().toString() + "'";
                    Cursor c1 = db.rawQuery(QuerySelect, null);
                    if (c1.moveToFirst()) {
                        do {
                            String QueryUpdate = "update register set pass='" + edtnewpass.getText().toString() + "' where pass='" + edtoldpass.getText().toString() + "'";
                            db.execSQL(QueryUpdate);
                            Toast.makeText(getActivity(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                        } while (c.moveToNext());
                    } else {
                        Toast.makeText(getActivity(), "Password Not Changed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
         return view;
    }

}

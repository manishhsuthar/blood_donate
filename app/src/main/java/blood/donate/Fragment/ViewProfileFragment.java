package blood.donate.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import blood.donate.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment {

    TextView txtname,txtemail,txtpass,txtcno,txtbdate,txthint;

    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);

        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        txtname = (TextView) view.findViewById(R.id.view_profile_name);
        txtemail = (TextView)  view.findViewById(R.id.view_profile_email);
        txtpass = (TextView)  view.findViewById(R.id.view_profile_pass);
        txtbdate = (TextView)  view.findViewById(R.id.view_profile_date_of_birth);
        txtcno = (TextView)  view.findViewById(R.id.view_profile_contact_nomber);
        txthint = (TextView)  view.findViewById(R.id.view_profile_hint);

        String QueryDisplay = "select * from register";
        Cursor c = db.rawQuery(QueryDisplay, null);
        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                String name = c.getString(0);
                String email = c.getString(1);
                String pass = c.getString(2);
                String bdate = c.getString(3);
                String cno = c.getString(4);
                String hint = c.getString(5);
                txtname.setText(name);
                txtemail.setText(email);
                txtpass.setText(pass);
                txtbdate.setText(bdate);
                txtcno.setText(cno);
                txthint.setText(hint);
            } while (c.moveToNext());
            //Toast.makeText(getActivity(), "" + sb, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "data not found..", Toast.LENGTH_SHORT).show();
        }

    return view;
    }

    /*protected void openDatabase() {
        db = getActivity().openOrCreateDatabase("SagarDB", Context.MODE_PRIVATE, null);
    }*/

    /*private void showRecords() {
    }

    protected void moveNext() {
        if (!c.isLast())
            c.moveToNext();
        showRecords();
    }

    protected void movePrev() {
        if (!c.isFirst())
            c.moveToPrevious();
        showRecords();
    }
*/

}

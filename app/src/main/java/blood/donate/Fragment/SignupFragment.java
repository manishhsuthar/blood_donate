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
import blood.donate.MyMessage;
import blood.donate.R;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    EditText edtname,edtemail,edtpass,edtcpass,edtcno,edtbdate,edthint;
    Spinner sp_signup;
    RadioGroup rg_signup;
    RadioButton rb_male,rb_female;
    Button btnsignup;
    SharedPreferences sp;


    ArrayAdapter sp_adapter;
    String sp_str[] = {"A Positive","A Negative","B Positive","B Negative","AB Positive","AB Negative","O Positive","O Negative"};
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SQLiteDatabase db;
    Cursor c;
    MyMessage message;
    String email;
    String sBloodGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        sp = getActivity().getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        edtname = (EditText)view.findViewById(R.id.signup_name);
        edtemail  = (EditText)view.findViewById(R.id.signup_email);
        edtpass  = (EditText)view.findViewById(R.id.signup_pass);
        edtcpass  = (EditText)view.findViewById(R.id.signup_confirm_pass);
        edtcno  = (EditText)view.findViewById(R.id.signup_contact_nomber);
        edtbdate  = (EditText)view.findViewById(R.id.signup_date_of_birth);
        edthint = (EditText) view.findViewById(R.id.signup_hint);

        sp_signup = (Spinner) view.findViewById(R.id.signup_sp);

        rg_signup = (RadioGroup) view.findViewById(R.id.signup_rg);

        rb_male = (RadioButton) view.findViewById(R.id.signup_male_rb);
        rb_female = (RadioButton) view.findViewById(R.id.signup_female_rb);

        btnsignup = (Button) view.findViewById(R.id.signup_btn);

        sp_adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,sp_str);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        sp_signup.setAdapter(sp_adapter);

        sp_signup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sBloodGroup = sp_str[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rg_signup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rb_male.isChecked()){
                    Toast.makeText(getActivity(), rb_male.getText(), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getActivity(), rb_female.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        message = new MyMessage(getActivity());

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();

                email = edtemail.getText().toString();
            }

            private void Validation() {

                if(edtemail.getText().toString().equals("")){
                    edtemail.setError("Enter Email ID");
                }

                String email = edtemail.getText().toString();
                if (email.matches(emailPattern)) {
                } else {
                    Toast.makeText(getActivity(), "In Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edtpass.getText().toString().equals("")){
                    edtpass.setError("Enter Your Password");
                }

                String pwd=edtpass.getText().toString();
                String pwd1=edtcpass.getText().toString();
                if(pwd.equals(pwd1)){
                    //Toast.makeText(getActivity(), "match", Toast.LENGTH_SHORT).show();
                }else {
                    message.myMsg("Oops!","Password Does Not Match");
                      //Toast.makeText(getActivity(), "not match", Toast.LENGTH_SHORT).show();
                return;
                }

                if(edtname.getText().toString().equals("")){
                    edtname.setError("Enter Name");
                }
                if(edtbdate.getText().toString().equals("")){
                    edtbdate.setError("Enter Birth Date");
                }
                if(edtcno.getText().toString().equals("")){
                    edtcno.setError("Enter Contact Number");
                }
                if(edthint.getText().toString().equals("")){
                    edthint.setError("Enter Hint");
                }
                else{
                    String insert_Query = "insert into register values (?, '"+ edtname.getText().toString() + "','" + edtemail.getText().toString() + "','" + edtpass.getText().toString() + "','" + edtcno.getText().toString() + "','" + edtbdate.getText().toString() + "','" + edthint.getText().toString() + "','"+sBloodGroup+"')";
                    db.execSQL(insert_Query);

                    Intent i = new Intent(getActivity(), HomeActivity.class);

                    String email_sp=edtemail.getText().toString();
                    sp.edit().putString(Constans.USERNAME,email_sp).commit();
                    startActivity(i);
                    clear();
                }
            }
        });
        return view;
    }

    private void clear() {
        edtname.setText("");
        edtemail.setText("");
        edtpass.setText("");
        edtcpass.setText("");
        edtcno.setText("");
        edtbdate.setText("");
        edthint.setText("");
    }
}

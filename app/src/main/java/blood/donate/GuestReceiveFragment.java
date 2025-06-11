package blood.donate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuestReceiveFragment extends Fragment {

    RadioGroup grp_rg;
    RadioButton grp_Apositive,grp_Anegative,grp_Bpositive,grp_Bnegative
                ,grp_Abpositive,grp_Abnegative,grp_Opositive,grp_Onegative;

    SharedPreferences sp;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest_receive, container, false);
        sp = getActivity().getSharedPreferences(Constans.PREF, Context.MODE_PRIVATE);

        db = getActivity().openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        grp_rg = (RadioGroup) view.findViewById(R.id.grp_rg);
        grp_Apositive = (RadioButton) view.findViewById(R.id.grp_APositive);
        grp_Anegative = (RadioButton) view.findViewById(R.id.grp_ANegative);
        grp_Bpositive = (RadioButton) view.findViewById(R.id.grp_BPositive);
        grp_Bnegative = (RadioButton) view.findViewById(R.id.grp_BNegative);
        grp_Abpositive = (RadioButton) view.findViewById(R.id.grp_ABPositive);
        grp_Abnegative = (RadioButton) view.findViewById(R.id.grp_ABNegative);
        grp_Opositive = (RadioButton) view.findViewById(R.id.grp_OPositive);
        grp_Onegative = (RadioButton) view.findViewById(R.id.grp_ONegative);

        grp_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = view.findViewById(checkedId);
                sp.edit().putString(Constans.BLOOD_GROUP,rb.getText().toString()).commit();
                startActivity(new Intent(getActivity(), ApActivity.class));
                /*if(grp_Apositive.isChecked()){
                    Toast.makeText(getActivity(), grp_Apositive.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), ApActivity.class));
                }
                else if(grp_Anegative.isChecked()){
                    Toast.makeText(getActivity(), grp_Anegative.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodANegataive.class));
                }
                else if(grp_Bpositive.isChecked()){
                    Toast.makeText(getActivity(), grp_Bpositive.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodBPositive.class));
                }
                else if(grp_Bnegative.isChecked()){
                    Toast.makeText(getActivity(), grp_Bnegative.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodBNegative.class));
                }
                else if(grp_Abpositive.isChecked()){
                    Toast.makeText(getActivity(), grp_Abpositive.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodABPositive.class));
                }
                else if(grp_Abnegative.isChecked()){
                    Toast.makeText(getActivity(), grp_Abnegative.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodABNegative.class));
                }
                else if(grp_Opositive.isChecked()){
                    Toast.makeText(getActivity(), grp_Opositive.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodOPositive.class));
                }
                else if(grp_Onegative.isChecked()){
                    Toast.makeText(getActivity(), grp_Opositive.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), BloodONegative.class));
                }*/
            }
        });
        return view;
    }

}

package blood.donate;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ApActivity extends AppCompatActivity {

    List<ApList> apList;
    ApAdapter apAdapter;
    RecyclerView recyclerView;
    Context c = this;
    RecyclerView.LayoutManager mLayoutManager;

    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ap);

        sp = getSharedPreferences(Constans.PREF, MODE_PRIVATE);

        db = openOrCreateDatabase("Blood Donate", Context.MODE_PRIVATE, null);
        String tableQuery = "create table if not exists register(id integer primary key autoincrement, name text,email text,pass text,cno number,dob text,hint text,bloodgroup text)";
        db.execSQL(tableQuery);

        recyclerView = (RecyclerView) findViewById(R.id.ap_recycle_view);
        mLayoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        String selectQuery = "SELECT * FROM register WHERE bloodgroup='" + sp.getString(Constans.BLOOD_GROUP, "") + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            apList = new ArrayList<>();
            while (cursor.moveToNext()) {
                ApList apositive = new ApList();
                apositive.setId_ap(cursor.getString(0));
                apositive.setName_ap(cursor.getString(1));
                apositive.setAddress_ap(cursor.getString(2));
                apositive.setCall_ap(cursor.getString(4));
                apositive.setMsg_ap(cursor.getString(4));
                apositive.setGrp_ap(cursor.getString(7));
                apList.add(apositive);
            }
            apAdapter = new ApAdapter(c, apList);
            recyclerView.setAdapter(apAdapter);
        }
        else{
            Toast.makeText(c, "Record Not Found", Toast.LENGTH_SHORT).show();
        }

    }
}


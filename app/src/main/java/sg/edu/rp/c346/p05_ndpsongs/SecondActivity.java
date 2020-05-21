package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnShowList;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> al;
    ArrayList<String> alyear;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lv);
        btnShowList = findViewById(R.id.btnShowList);
        spinner = findViewById(R.id.spinner);
        al = new ArrayList<Song>();
        alyear = new ArrayList<String>();

        DBHelper db = new DBHelper(SecondActivity.this);
        al = db.getAllSongs();
        alyear = db.getAllYears();
        db.close();
        aa = new arrayAdapter(SecondActivity.this, R.layout.row, al);
        lv.setAdapter(aa);

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(SecondActivity.this);
                al.clear();
                al = db.getSong();
                db.close();

                aa = new arrayAdapter(SecondActivity.this, R.layout.row, al);
                lv.setAdapter(aa);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                Song data = al.get(i);
                intent.putExtra("data", data);
                startActivityForResult(intent, 9);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alyear);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a =(String) spinner.getItemAtPosition(i);
                DBHelper db = new DBHelper(SecondActivity.this);
                al.clear();
                al = db.getSongBasedYears(Integer.parseInt(a));
                db.close();

                aa = new arrayAdapter(SecondActivity.this, R.layout.row, al);
                lv.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper db = new DBHelper(SecondActivity.this);
            al.clear();
            al = db.getAllSongs();
            db.close();
            aa = new arrayAdapter(SecondActivity.this, R.layout.row, al);
            lv.setAdapter(aa);
        }
    }
}

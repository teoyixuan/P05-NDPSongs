package sg.edu.rp.c346.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rg;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTitle.getText().toString().length() == 0 ||
                        etSinger.getText().toString().length() == 0 ||
                        etYear.getText().toString().length() == 0 ||
                        rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Incomplete data",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String title = etTitle.getText().toString();
                    String singer = etSinger.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    //radio button
                    int radioButtonID = rg.getCheckedRadioButtonId();
                    View radioButton = (RadioButton) findViewById(radioButtonID);
                    int idx = rg.indexOfChild(radioButton);
                    RadioButton r = (RadioButton) rg.getChildAt(idx);
                    String selectedText = r.getText().toString();
                    int selected = Integer.parseInt(selectedText);

                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long result = dbh.insertSong(title, singer, year, selected);
                    dbh.close();


                    etTitle.setText("");
                    etSinger.setText("");
                    etYear.setText("");
                    rg.clearCheck();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(i);
            }
        });
    }
}

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

public class ThirdActivity extends AppCompatActivity {

    Song data;
    EditText etID, etTitle, etSinger, etYear;
    RadioGroup rg2;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle2);
        etSinger = findViewById(R.id.etSinger2);
        etYear = findViewById(R.id.etYear2);
        rg2 = findViewById(R.id.rg2);
        btnDelete = findViewById(R.id.btnDelete2);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        etID.setText(data.getId() + "");
        etYear.setText(data.getYear() + "");
        etSinger.setText(data.getSingers());
        etTitle.setText(data.getTitle());

        int star = data.getStars();
        if (star == 1){
            rg2.check(R.id.rb11);
        } else if (star == 2){
            rg2.check(R.id.rb22);
        }else if (star == 3){
            rg2.check(R.id.rb33);
        } else if (star == 4){
            rg2.check(R.id.rb44);
        } else {
            rg2.check(R.id.rb55);
        }

        etID.setEnabled(false);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(etID.getText().toString());
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                int radioButtonID = rg2.getCheckedRadioButtonId();
                View radioButton = (RadioButton) findViewById(radioButtonID);
                int idx = rg2.indexOfChild(radioButton);
                RadioButton r = (RadioButton) rg2.getChildAt(idx);
                String selectedText = r.getText().toString();
                int selected = Integer.parseInt(selectedText);

                Song song = new Song(id, title, singer, year, selected);

                DBHelper dbh = new DBHelper(ThirdActivity.this);
                long inserted_id = dbh.updateSong(song);
                dbh.close();

                Toast.makeText(ThirdActivity.this, "Sound Updated",
                        Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                long inserted_id = dbh.deleteSong(Integer.parseInt(etID.getText().toString()));
                dbh.close();

                Toast.makeText(ThirdActivity.this, "Song Deleted",
                        Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

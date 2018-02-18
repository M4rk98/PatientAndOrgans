package hu.petrik.betegszervapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

public class UjBetegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uj_beteg);

        Button ujbeteg = (Button)findViewById(R.id.ujbeteg_gomb);
        ujbeteg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Komponensektől kérdezzük le a beszúrandó értékeket
                // Form elemek
                TextView nevView = (TextView) findViewById(R.id.ujbeteg_nev);
                TextView tajView = (TextView) findViewById(R.id.ujbeteg_taj);
                Spinner szervView = (Spinner) findViewById(R.id.ujbeteg_szerv);
                TextView tipusView = (TextView) findViewById(R.id.ujbeteg_tipus);

                final String nev = nevView.getText().toString();
                final String taj = tajView.getText().toString();
                final String szerv = szervView.getSelectedItem().toString();
                final String tipus = tipusView.getText().toString();

                // ...

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DbHelper helper = DbHelper.getInstance(UjBetegActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        ContentValues beteg = new ContentValues();
                        beteg.put("nev", nev);
                        beteg.put("taj", taj);
                        beteg.put("szerv", szerv);
                        beteg.put("tipus", tipus);
                        beteg.putNull("szerv_id");
                        long betegId = db.insert("beteg", null, beteg);

                        db.close();
                        UjBetegActivity.this.finish();
                    }
                });

            }
        });
    }
}

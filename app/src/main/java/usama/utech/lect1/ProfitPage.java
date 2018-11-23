package usama.utech.lect1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import usama.utech.lect1.Database.ProductDb;

public class ProfitPage extends AppCompatActivity {

    GraphView graph;
    LineGraphSeries<DataPoint> series;
    ProductDb db;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_page);

        db = new ProductDb(this);
        sqLiteDatabase = db.getWritableDatabase();
        graph = findViewById(R.id.graph);
        graph.setTitle("Profit");
        series = new LineGraphSeries<DataPoint>(getData());
        graph.addSeries(series);
        Toast.makeText(this, ""+ getData(), Toast.LENGTH_SHORT).show();
    }

    private DataPoint[] getData() {
        //from here we will read data from database
        String[] columns = {"Profit, Date"};
        Cursor cursor = sqLiteDatabase.query("Profit",columns,null,
                null,null,null,null);

        DataPoint[] dp = new DataPoint[cursor.getCount()];
        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0),cursor.getInt(2));
            Toast.makeText(this, ""+ dp[i], Toast.LENGTH_SHORT).show();
        }
        return dp;
    }
}

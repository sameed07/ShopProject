package usama.utech.lect1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

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
        GraphView graph2 = (GraphView) findViewById(R.id.graph2);

        graph.setTitle("Profit (Line Graph)");
        graph2.setTitle("Profit (Bar Graph)");
        series = new LineGraphSeries<DataPoint>(getData());
        graph.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"nov","dec","jan","feb"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"500","1000","1500","2000"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {&quot;Sun&quot;, &quot;Mon&quot;, &quot;Tue&quot;});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(getData());
        graph2.addSeries(series2);
        StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph2);
        staticLabelsFormatter2.setHorizontalLabels(new String[] {"nov","dec","jan","feb"});
        staticLabelsFormatter2.setVerticalLabels(new String[] {"500","1000","1500","2000"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {&quot;Sun&quot;, &quot;Mon&quot;, &quot;Tue&quot;});
        graph2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter2);

        series2.setValueDependentColor(new ValueDependentColor() {
            @Override
            public int get(DataPointInterface data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);

            }
        });
        series2.setSpacing(20);
        // draw values on top
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.RED);
        series2.setValuesOnTopSize(40);
        // tap listener
        series2.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(ProfitPage.this, "datapoint is "+dataPoint, Toast.LENGTH_SHORT).show();

            }
        });

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

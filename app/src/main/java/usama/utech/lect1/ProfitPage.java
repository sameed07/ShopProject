package usama.utech.lect1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;

import usama.utech.lect1.Database.ProductDb;
import usama.utech.lect1.Model.Profit;

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

//to add data to profit table
//        try {
//            ProductDb productDb = new ProductDb(this);
//            productDb.delete();
//
//            productDb.insertInToProfitTable(new Profit("12/03/2018", "12:07", 300.0));
//            productDb.insertInToProfitTable(new Profit("13/03/2018", "4:07", 600.0));
//            productDb.insertInToProfitTable(new Profit("14/03/2018", "6:07", 700.0));
//            productDb.insertInToProfitTable(new Profit("15/03/2018", "2:07", 800.0));
//            productDb.insertInToProfitTable(new Profit("16/03/2018", "1:07", 100.0));
//            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//
//            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//        }

        graph.setTitle("Profit (Line Graph)");
        graph2.setTitle("Profit (Bar Graph)");
        series = new LineGraphSeries<DataPoint>(getData());
//        series = new LineGraphSeries<DataPoint>(new DataPoint[]{new DataPoint(1,50)
//                ,new DataPoint(2,150)
//                ,new DataPoint(3,70)
//                ,new DataPoint(4,250)
//                ,new DataPoint(5,300)
//        }
//                );

        graph.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"nov", "dec", "jan", "feb"});
      //  staticLabelsFormatter.setVerticalLabels(new String[]{"500", "1000", "1500", "2000"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {&quot;Sun&quot;, &quot;Mon&quot;, &quot;Tue&quot;});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(getData());
        graph2.addSeries(series2);
        StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph2);
        staticLabelsFormatter2.setHorizontalLabels(new String[]{"nov", "dec", "jan", "feb"});
      //  staticLabelsFormatter2.setVerticalLabels(new String[]{"500", "1000", "1500", "2000"});
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
                Toast.makeText(ProfitPage.this, "datapoint is " + dataPoint, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private DataPoint[] getData() {
        //from here we will read data from database
//        String[] columns = {"Profit, Date"};
//        Cursor cursor = sqLiteDatabase.query("Profit", columns, null,
//                null, null, null, null);
//
//        DataPoint[] dp = new DataPoint[cursor.getCount()];
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToNext();
//            dp[i] = new DataPoint(i, Double.parseDouble(cursor.getString(2)));
//            Toast.makeText(this, "" + dp[i], Toast.LENGTH_SHORT).show();
//        }

        ProductDb productDb = new ProductDb(this);
        ArrayList<Profit> profitData = new ArrayList<>();
        profitData.addAll(productDb.getAllProfitTableData());

        DataPoint[] dp2 = new DataPoint[profitData.size()];

        for (int i = 0; i < profitData.size(); i++) {
            Profit profit = profitData.get(i);

            dp2[i] = new DataPoint(i,profit.getProfit());


        }



        return dp2;
    }
}

package usama.utech.lect1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import usama.utech.lect1.Adapter.ProductAdapter;
import usama.utech.lect1.Database.ProductDb;
import usama.utech.lect1.Model.Products;

public class SearchProduct extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private AutoCompleteTextView autoCompleteTextView;
    private ProductAdapter adapter;
    private List<Products> exampleList;

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        zXingScannerView = (ZXingScannerView) findViewById(R.id.scanner);

        zXingScannerView.setResultHandler(this);

        fillExampleList();

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        RecyclerView recyclerView = findViewById(R.id.lv1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(this, exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searching();

    }

    public void searching() {


        ArrayList<String> prodctNames = new ArrayList<>();

        for (int i = 0; i < exampleList.size(); i++) {

            Products productsdata = exampleList.get(i);
            prodctNames.add(productsdata.getName());

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, prodctNames);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adapter.getFilter().filter( autoCompleteTextView.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        ProductDb db = new ProductDb(this);
//        exampleList.add(new Products("Product 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase Price: Rs 250","210"));
//        exampleList.add(new Products("New Pro 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase price : Rs 250","210"));
//        exampleList.add(new Products("Bulb 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase Price: Rs 250","210"));
//        exampleList.add(new Products("Clothes 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase price : Rs 250","210"));
//        exampleList.add(new Products("Laptops 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase Price: Rs 250","210"));
//        exampleList.add(new Products("Mobiles 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase price : Rs 250","210"));
//        exampleList.add(new Products("Mobiles 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase Price: Rs 250","210"));
//        exampleList.add(new Products("Desktops 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
//                "Purchase price : Rs 250","210"));

        exampleList.addAll(db.getAllProductsTableData());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        finish();
    }


    public void scanProductAndGetQROrBarcode(View view) {
        if (zXingScannerView.getVisibility() == View.INVISIBLE) {
            zXingScannerView.setVisibility(View.VISIBLE);
            zXingScannerView.startCamera();

        } else {
            zXingScannerView.stopCamera();
            // zXingScannerView.setFlash(false);
            zXingScannerView.removeAllViews();

            zXingScannerView.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void handleResult(final Result result) {
        final String myResult = result.getText();
        Toast.makeText(this, "result " + myResult, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Is This Scan Result Correct ?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                adapter.getFilter().filter(myResult);

                zXingScannerView.resumeCameraPreview(SearchProduct.this);

//error
//                zXingScannerView.stopCamera();
//               // zXingScannerView.setFlash(false);
//
                zXingScannerView.removeAllViews();
                zXingScannerView.setVisibility(View.INVISIBLE);


            }
        });
        builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zXingScannerView.resumeCameraPreview(SearchProduct.this);
                dialog.dismiss();
            }
        });
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.resumeCameraPreview(SearchProduct.this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zXingScannerView.stopCamera();
    }

}

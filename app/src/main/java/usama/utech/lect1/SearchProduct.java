package usama.utech.lect1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import usama.utech.lect1.Adapter.ProductAdapter;
import usama.utech.lect1.Model.Products;

public class SearchProduct extends AppCompatActivity {


    private SearchView searchView;
    private ProductAdapter adapter;
    private List<Products> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        fillExampleList();

        searchView = findViewById(R.id.searchView);
        RecyclerView recyclerView = findViewById(R.id.lv1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ProductAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searching();

    }

    public void searching(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new Products("Product 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase Price: Rs 250","210"));
        exampleList.add(new Products("New Pro 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase price : Rs 250","210"));
        exampleList.add(new Products("Bulb 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase Price: Rs 250","210"));
        exampleList.add(new Products("Clothes 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase price : Rs 250","210"));
        exampleList.add(new Products("Laptops 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase Price: Rs 250","210"));
        exampleList.add(new Products("Mobiles 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase price : Rs 250","210"));
        exampleList.add(new Products("Mobiles 1","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase Price: Rs 250","210"));
        exampleList.add(new Products("Desktops 2","21313131","Product1","Quantity : 25","Sale Price : Rs 200",
                "Purchase price : Rs 250","210"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        finish();
    }


}

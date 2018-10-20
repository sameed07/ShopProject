package usama.utech.lect1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),LoginPage.class));
        finish();

    }

    public void aboutActivity(View view) {

        startActivity(new Intent(getApplicationContext(), AboutUs.class));
        finish();
    }

    public void searchProduct(View view) {
        startActivity(new Intent(getApplicationContext(), SearchProduct.class));
        finish();
    }

    public void addProduct(View view) {
        startActivity(new Intent(getApplicationContext(), AddProduct.class));
        finish();
    }
}

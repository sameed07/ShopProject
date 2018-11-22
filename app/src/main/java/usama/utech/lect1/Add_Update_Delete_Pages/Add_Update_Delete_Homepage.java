package usama.utech.lect1.Add_Update_Delete_Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import usama.utech.lect1.HomePage;
import usama.utech.lect1.LoginPage;
import usama.utech.lect1.R;

public class Add_Update_Delete_Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__update__delete__homepage);
    }

    public void goto_Add_Product(View view) {

        startActivity(new Intent(getApplicationContext(), AddProduct.class));
        finish();
    }

    public void goto_update_Product(View view) {
        startActivity(new Intent(getApplicationContext(), UpdateProducts.class));
        finish();
    }

    public void goto_delete_Product(View view) {
        startActivity(new Intent(getApplicationContext(), DeleteProducts.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        finish();

    }


}

package usama.utech.lect1.Add_Update_Delete_Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import usama.utech.lect1.R;

public class UpdateProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Add_Update_Delete_Homepage.class));
        finish();
    }

    public void addProductsToDB(View view) {
    }
}
package usama.utech.lect1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
    }


    public void homepage(View view) {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
        finish();
    }
}

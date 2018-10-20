package usama.utech.lect1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button ;

    EditText editText;
    String data = null;
    int a = 7878;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button =(Button) findViewById(R.id.but1);
        editText = (EditText) findViewById(R.id.edtxt);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
    }

        public void plu(View view){

            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        }


}

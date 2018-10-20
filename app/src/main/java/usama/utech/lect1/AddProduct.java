package usama.utech.lect1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddProduct extends AppCompatActivity {

    private EditText i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        finish();
    }

    public void showFullScreenDialog(View view) {

        // custom dialog
        final Dialog dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_NoActionBar);
        dialog.setContentView(R.layout.diloag_fullscreen);
       Button dialogCancelBtn =  dialog.findViewById(R.id.btn_cancel);
       dialogCancelBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
           }
       });
        dialog.setCancelable(false);
        dialog.show();

    }
}

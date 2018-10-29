package usama.utech.lect1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import usama.utech.lect1.Database.ProductDb;
import usama.utech.lect1.Model.Products;

public class AddProduct extends AppCompatActivity {

    private TextView M_tv;

    TextView tv_barcode;
    TextView tv_name;
    TextView tv_pPrice;
    TextView tv_sPrice;
    TextView tv_wPrice;
    TextView tv_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //marquee textview
        M_tv = (TextView) this.findViewById(R.id.mywidget);
        M_tv.setSelected(true);  // Set focus to the textview

        tv_barcode = findViewById(R.id.tv_barcode);
        tv_name = findViewById(R.id.tv_name);
        tv_pPrice = findViewById(R.id.tv_PPrice);
        tv_sPrice = findViewById(R.id.tv_SPrice);
        tv_wPrice = findViewById(R.id.tv_WhPrice);
        tv_quantity = findViewById(R.id.tv_quantity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
        finish();
    }

    public void showFullScreenDialog(View view) {

        // custom dialog
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_NoActionBar);
        dialog.setContentView(R.layout.diloag_fullscreen);

        TextView dialogCancelBtn = dialog.findViewById(R.id.btn_cancel);

        final EditText barcode = dialog.findViewById(R.id.et_barcode);
        final EditText name = dialog.findViewById(R.id.et_name);
        final EditText pPrice = dialog.findViewById(R.id.et_PPrice);
        final EditText sPrice = dialog.findViewById(R.id.et_SPrice);
        final EditText wPrice = dialog.findViewById(R.id.et_WPrice);
        final TextView quantity = dialog.findViewById(R.id.et_quantity);

        if(!tv_barcode.getText().toString().equals("-") && !tv_barcode.getText().toString().equals("")){

            barcode.setText(tv_barcode.getText().toString());
            name.setText(tv_name.getText().toString());
            pPrice.setText(tv_pPrice.getText().toString());
            sPrice.setText(tv_sPrice.getText().toString());
            wPrice.setText(tv_wPrice.getText().toString());
            quantity.setText(tv_quantity.getText().toString());

        }


        dialogCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });




        final TextView quantity_txt = dialog.findViewById(R.id.et_quantity);

        Button plusQuantity = dialog.findViewById(R.id.btn_plusQuantity);
        plusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qun = Integer.parseInt(quantity_txt.getText().toString());
                qun++;
                quantity_txt.setText(qun + "");
            }
        });

        Button minusQuantity = dialog.findViewById(R.id.btn_minusQuantity);
        minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qun = Integer.parseInt(quantity_txt.getText().toString());
                if (qun > 0) {
                    qun--;
                    quantity_txt.setText(qun + "");
                }
            }
        });





        Button dialogSubmitBtn = dialog.findViewById(R.id.btn_submit);
        dialogSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {



                    tv_barcode.setText(barcode.getText().toString());
                    tv_name.setText(name.getText().toString());
                    tv_pPrice.setText(pPrice.getText().toString());
                    tv_sPrice.setText(sPrice.getText().toString());
                    tv_wPrice.setText(wPrice.getText().toString());
                    tv_quantity.setText(quantity.getText().toString());

                    dialog.dismiss();



                } catch (Exception e) {

                    Toast.makeText(AddProduct.this, "" + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    public void addProductsToDB(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Add Product");
        dialog.setMessage("Are You Sure You Want To Add This Product?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                try{

                    ProductDb db = new ProductDb(AddProduct.this);

                    Products products = new Products();

                    products.setBarCode(tv_barcode.getText().toString());
                    products.setName(tv_name.getText().toString());
                    products.setPurchasePrice(Double.parseDouble(tv_pPrice.getText().toString()));
                    products.setSalePrice(Double.parseDouble(tv_sPrice.getText().toString()));
                    products.setWholeSalePrice(Double.parseDouble(tv_wPrice.getText().toString()));
                    products.setQuantity(Integer.parseInt(tv_quantity.getText().toString()));

                    db.insertInToProductsTable(products);

                    Toast.makeText(AddProduct.this, "Product Added", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(AddProduct.this, "Error Adding Product", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });
        dialog.show();




    }
}

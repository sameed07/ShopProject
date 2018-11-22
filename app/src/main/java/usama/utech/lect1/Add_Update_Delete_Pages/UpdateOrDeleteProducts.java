package usama.utech.lect1.Add_Update_Delete_Pages;

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
import usama.utech.lect1.R;

public class UpdateOrDeleteProducts extends AppCompatActivity {

    String id, barcode, name, quantity, Sprice, Pprice, Wsprice;

    private TextView textvID;
    private TextView textvBarcode;
    private TextView textvName;
    private TextView textvQuantity;
    private TextView textvPPrice;
    private TextView textvSPrice;
    private TextView textvWhPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);

        textvID = (TextView) findViewById(R.id.textv_ID);
        textvBarcode = (TextView) findViewById(R.id.textv_barcode);
        textvName = (TextView) findViewById(R.id.textv_name);
        textvQuantity = (TextView) findViewById(R.id.textv_quantity);
        textvPPrice = (TextView) findViewById(R.id.textv_PPrice);
        textvSPrice = (TextView) findViewById(R.id.textv_SPrice);
        textvWhPrice = (TextView) findViewById(R.id.textv_WhPrice);


        getAllIntentData();
    }

    private void getAllIntentData() {

        try {
            if (getIntent() != null) {

                id = getIntent().getStringExtra("id");
                barcode = getIntent().getStringExtra("barcode");
                name = getIntent().getStringExtra("name");
                quantity = getIntent().getStringExtra("quantity");
                Sprice = getIntent().getStringExtra("Sprice");
                Pprice = getIntent().getStringExtra("Pprice");
                Wsprice = getIntent().getStringExtra("Wsprice");

                textvID.setText(id);
                textvBarcode.setText(barcode);
                textvName.setText(name);
                textvQuantity.setText(quantity);
                textvPPrice.setText(Sprice);
                textvSPrice.setText(Pprice);
                textvWhPrice.setText(Wsprice);

            }
        } catch (Exception e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), UpdateAndDeleteProductRView.class));
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

        if (!textvBarcode.getText().toString().equals("-") && !textvBarcode.getText().toString().equals("")) {

            barcode.setText(textvBarcode.getText().toString());
            name.setText(textvName.getText().toString());
            pPrice.setText(textvPPrice.getText().toString());
            sPrice.setText(textvSPrice.getText().toString());
            wPrice.setText(textvWhPrice.getText().toString());
            quantity.setText(textvQuantity.getText().toString());

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


                    textvBarcode.setText(barcode.getText().toString());
                    textvName.setText(name.getText().toString());
                    textvPPrice.setText(pPrice.getText().toString());
                    textvSPrice.setText(sPrice.getText().toString());
                    textvWhPrice.setText(wPrice.getText().toString());
                    textvQuantity.setText(quantity.getText().toString());

                    dialog.dismiss();


                } catch (Exception e) {

                    Toast.makeText(UpdateOrDeleteProducts.this, "" + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    public void deleteProductFromDB(View view) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Delete Product");
        dialog.setMessage("Are You Sure You Want To Remove This Product?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                try {

                    int id1 = Integer.parseInt(textvID.getText().toString());
                    ProductDb db = new ProductDb(UpdateOrDeleteProducts.this);

                    db.delteFromProductTable(id1);

                    Toast.makeText(UpdateOrDeleteProducts.this, "Product Removed", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(UpdateOrDeleteProducts.this, "Error Removing Product", Toast.LENGTH_SHORT).show();
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

    public void updateProductInDB(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Update Product");
        dialog.setMessage("Are You Sure You Want To Update This Product?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                try {

                    int id2 = Integer.parseInt(textvID.getText().toString());
                    ProductDb db = new ProductDb(UpdateOrDeleteProducts.this);

                    Products products = new Products();

                    products.setBarCode(textvBarcode.getText().toString());
                    products.setName(textvName.getText().toString());
                    products.setPurchasePrice(Double.parseDouble(textvPPrice.getText().toString()));
                    products.setSalePrice(Double.parseDouble(textvSPrice.getText().toString()));
                    products.setWholeSalePrice(Double.parseDouble(textvWhPrice.getText().toString()));
                    products.setQuantity(Integer.parseInt(textvQuantity.getText().toString()));



                    db.UpdateProductTable(products,id2);



                    Toast.makeText(UpdateOrDeleteProducts.this, "Product Updated", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(UpdateOrDeleteProducts.this, "Error Updating Product", Toast.LENGTH_SHORT).show();
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

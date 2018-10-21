package usama.utech.lect1.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

import usama.utech.lect1.Model.Products;

public class ProductDb  extends SQLiteOpenHelper{

    private static final String DB_NAME = "Products.db";
    private static final int DB_VER = 1;

    private static final String TABLE_PRODUCTS = "ProductDetail";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_BUYER = "Buyer";
    private static final String TABLE_PROFIT = "Profit";
    private static final String TABLE_SALE = "Sale";
    private static final String TABLE_EXPENSE = "Expense";

    public ProductDb(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String productTableCreateQuery = "CREATE TABLE "+TABLE_PRODUCTS+" (Id INTEGER ,BarCode TEXT, Name TEXT,Quantity TEXT" +
        ",SalePrice TEXT ,PurchasePrice TEXT,WholeSalePrice TEXT) ";
        String userTableCreateQuery = "CREATE TABLE "+TABLE_USERS+" (Id INTEGER PRIMARY KEY, Username TEXT, Password TEXT )";
        String buyerTableCreateQuery = "CREATE TABLE "+TABLE_BUYER+" (Id INTEGER PRIMARY KEY , BuyerName text, TotalAmount text, PaidAmount text, RemainingAmount text, LastPaidDate text)";
        String profitTableCreateQuery = "CREATE TABLE "+TABLE_PROFIT+" (Id INTEGER PRIMARY KEY, \"Date\" text, \"Time\" text, Profit text)";
        String saleTableCreateQuery = "CREATE TABLE "+TABLE_SALE+" (Id INTEGER PRIMARY KEY,CustomerName text, ProductName text, QuantitySold text, \"Date\" text, \"Time\" text)";
        String expenseTableCreateQuery = "CREATE TABLE "+TABLE_EXPENSE+" (Id INTEGER PRIMARY KEY,ItemName text,AmountPaid text, \"Date\" text, \"Time\" text)" ;

        String allQuerys[] = {productTableCreateQuery, userTableCreateQuery, buyerTableCreateQuery, profitTableCreateQuery, saleTableCreateQuery,  expenseTableCreateQuery};

        //to test
        for (String querys: allQuerys) {
            sqLiteDatabase.execSQL(querys);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ProductDetail");
        onCreate(sqLiteDatabase);
    }

    public void addToCart(Products order)
    {
        SQLiteDatabase db = getReadableDatabase();
         String query  = String.format("INSERT INTO ProductDetail(Id,BarCode,Name,Quantity,SalePrice," +
                        "PurchasePrice,WholeSalePrice)" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s');",
                order.getId()
                ,order.getBarCode()
                ,order.getName()
                ,order.getQuantity()
                ,order.getSalePrice()
                ,order.getPurchasePrice()
                ,order.getWholeSalePrice());

        db.execSQL(query);
    }

    public List<Products> getProducts(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "BarCode", "Name", "Quantity", "SalePrice","PurchasePrice","WholeSalePrice"};
        String sqlTable = "OrderDetail";
        qb.setTables(sqlTable);

        Cursor c = qb.query(db, sqlSelect, null,null,null,null,null);

        final List<Products> result = new ArrayList<>();

        if(c.moveToFirst()){

            do{
                result.add(new Products(c.getString(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("BarCode")),
                        c.getString(c.getColumnIndex("Name")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("SalePrice")),
                        c.getString(c.getColumnIndex("PurchasePrice")),
                        c.getString(c.getColumnIndex("WholeSalePrice"))
                ));
            }while (c.moveToNext());
        }


        return result;
    }
}

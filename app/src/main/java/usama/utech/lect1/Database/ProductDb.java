package usama.utech.lect1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

import usama.utech.lect1.Model.Buyer;
import usama.utech.lect1.Model.Expense;
import usama.utech.lect1.Model.Products;
import usama.utech.lect1.Model.Profit;
import usama.utech.lect1.Model.Sale;
import usama.utech.lect1.Model.Users;

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
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_PRODUCTS+" (Id INTEGER ,BarCode TEXT, Name TEXT,Quantity TEXT" + ",SalePrice TEXT ,PurchasePrice TEXT,WholeSalePrice TEXT) ");
        db.execSQL("CREATE TABLE "+TABLE_USERS+" (Id INTEGER PRIMARY KEY, Username TEXT, Password TEXT )");
        db.execSQL("CREATE TABLE "+TABLE_BUYER+" (Id INTEGER PRIMARY KEY , BuyerName text, TotalAmount text, PaidAmount text, RemainingAmount text, LastPaidDate text)");
        db.execSQL("CREATE TABLE "+TABLE_PROFIT+" (Id INTEGER PRIMARY KEY, \"Date\" text, \"Time\" text, Profit text)");
        db.execSQL("CREATE TABLE "+TABLE_SALE+" (Id INTEGER PRIMARY KEY,CustomerName text, ProductName text, QuantitySold text, \"Date\" text, \"Time\" text)");
        db.execSQL("CREATE TABLE "+TABLE_EXPENSE+" (Id INTEGER PRIMARY KEY,ItemName text,AmountPaid text, \"Date\" text, \"Time\" text)" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_BUYER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_PROFIT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SALE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_EXPENSE);

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


    //used to get all data from tables

    public ArrayList<Users> getAllUserTableData(){

        ArrayList<Users> resultCard = new ArrayList<Users>();

        String selectDataQuery = "select * from "+TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDataQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Users content = new Users();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setUsername(cursor.getString(1));
                content.setPassword(cursor.getString(2));
                // Adding Data to list
                resultCard.add(content);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        return resultCard;
    }

    public ArrayList<Profit> getAllProfitTableData(){

        ArrayList<Profit> resultCard = new ArrayList<Profit>();

        String selectDataQuery = "select * from "+TABLE_PROFIT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDataQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Profit content = new Profit();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setDate(cursor.getString(1));
                content.setTime(cursor.getString(2));
                content.setProfit(Double.parseDouble(cursor.getString(3)));
                // Adding Data to list
                resultCard.add(content);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        return resultCard;
    }

    public ArrayList<Sale> getAllSaleTableData(){

        ArrayList<Sale> resultCard = new ArrayList<Sale>();

        String selectDataQuery = "select * from "+TABLE_SALE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDataQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Sale content = new Sale();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setCustomerName(cursor.getString(1));
                content.setProductName(cursor.getString(2));
                content.setQuantitySold(Integer.parseInt(cursor.getString(3)));
                content.setDate(cursor.getString(4));
                content.setTime(cursor.getString(5));
                // Adding Data to list
                resultCard.add(content);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        return resultCard;
    }

    public ArrayList<Expense> getAllExpenseTableData(){

        ArrayList<Expense> resultCard = new ArrayList<Expense>();

        String selectDataQuery = "select * from "+TABLE_EXPENSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDataQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Expense content = new Expense();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setItemName(cursor.getString(1));
                content.setAmountPaid(Double.parseDouble(cursor.getString(2)));
                content.setDate(cursor.getString(3));
                content.setTime(cursor.getString(4));
                // Adding Data to list
                resultCard.add(content);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        return resultCard;
    }

    public ArrayList<Buyer> getAllBuyerTableData(){

        ArrayList<Buyer> resultCard = new ArrayList<Buyer>();

        String selectDataQuery = "select * from "+TABLE_BUYER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDataQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Buyer content = new Buyer();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setBuyerName(cursor.getString(1));
                content.setTotalAmount(Double.parseDouble(cursor.getString(2)));
                content.setPaidAmount(Double.parseDouble(cursor.getString(3)));
                content.setRemainingAmount(Double.parseDouble(cursor.getString(4)));
                content.setLastPaidDate(cursor.getString(5));
                // Adding Data to list
                resultCard.add(content);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        return resultCard;
    }



    //All tables insert quserys

    public void insertInToUserTable(Users users){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Username", users.getUsername());
        values.put("Password", users.getPassword());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    public void insertInToProfitTable(Profit profit){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Date", profit.getDate());
        values.put("Time", profit.getTime());
        values.put("Profit", profit.getProfit());

        // Inserting Row
        db.insert(TABLE_PROFIT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    public void insertInToSaleTable(Sale sale){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("CustomerName", sale.getCustomerName());
        values.put("ProductName", sale.getProductName());
        values.put("QuantitySold", sale.getQuantitySold());
        values.put("Date", sale.getDate());
        values.put("Time", sale.getTime());

        // Inserting Row
        db.insert(TABLE_SALE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    public void insertInToExpenseTable(Expense expense){



        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ItemName", expense.getItemName());
        values.put("AmountPaid", expense.getAmountPaid());
        values.put("Date", expense.getDate());
        values.put("Time", expense.getTime());

        // Inserting Row
        db.insert(TABLE_EXPENSE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    public void insertInToBuyerTable(Buyer buyer){



        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("BuyerName", buyer.getBuyerName());
        values.put("TotalAmount", buyer.getTotalAmount());
        values.put("PaidAmount", buyer.getPaidAmount());
        values.put("RemainingAmount", buyer.getRemainingAmount());
        values.put("LastPaidDate", buyer.getLastPaidDate());

        // Inserting Row
        db.insert(TABLE_BUYER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }



}

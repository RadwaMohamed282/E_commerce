package com.example.e_commerce.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.e_commerce.Models.CustomerModel;
import com.example.e_commerce.Models.ProductModel;

import java.net.PortUnreachableException;

public class EcommerceDatabase extends SQLiteOpenHelper {

    private static String DbName = "ecommereDB";
    SQLiteDatabase ecommerceDatabase;
    public EcommerceDatabase(Context context){
        super(context,DbName,null,16);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customers(custid integer primary key, custname text not null, custUsername text not null," +
                "custpassword text not null, custBirthdate text not null, custjob text not null, custGenger text not null )");

        db.execSQL("create table orders(ordid integer primary key, orddate text not null, ordAddress text," +
                "CustomerID integer not null, foreign key(CustomerID) references customers (custid))");

        db.execSQL("create table categories(catID integer primary key, catName text not null)");

        db.execSQL("create table products(proID integer primary key, proName text not null, proPrice integer not null," +
                "proQuantity integer,prodescription text not null ,productQrcode text ,cat_id integer not null, foreign key(cat_id) references categories(catID))");

        db.execSQL("create table orderDetails(orderID integer not null,productID integer not null,quntity integer,primary key(orderID,productID)," +
                " foreign key(orderID) references orders(ordid)," +
                "foreign key(productID) references products(proID))");

        db.execSQL("create table cartproducts (productid integer not null, productname text not null, productprice text not null, " +
                "productdescription text not null,productquantity integer not null )");




        ContentValues cat1 = new ContentValues();
        cat1.put("catName","Mobile");
        db.insert("categories",null,cat1);
        ContentValues cat2 = new ContentValues();
        cat1.put("catName","Camera");
        db.insert("categories",null,cat2);
        ContentValues cat3 = new ContentValues();
        cat1.put("catName","Laptop");
        db.insert("categories",null,cat3);

        ContentValues p1 = new ContentValues();
        p1.put("proName","Samsung Galaxy S21");
        p1.put("proPrice","14899");
        p1.put("proQuantity","3");
        p1.put("prodescription","Dual SIM Mobile - 6.2 inches, 128 GB, 8 GB RAM, 5G - White");
        p1.put("cat_id","1");
        db.insert("products",null,p1);

        ContentValues p2 = new ContentValues();
        p2.put("proName","Samsung Galaxy Z Flip");
        p2.put("proPrice","18000");
        p2.put("proQuantity","7");
        p2.put("prodescription"," Dual SIM - 256GB, 8GB RAM, 4G LTE - Black");
        p2.put("cat_id","1");
        db.insert("products",null,p2);

        ContentValues p3 = new ContentValues();
        p3.put("proName","Apple iPhone 12");
        p3.put("proPrice","23799");
        p3.put("proQuantity","5");
        p3.put("prodescription"," Pro Max 256GB 6 GB RAM, Graphite");
        p3.put("cat_id","1");
        db.insert("products",null,p3);

        ContentValues p4 = new ContentValues();
        p4.put("proName","Xiaomi Redmi Note 9S");
        p4.put("proPrice","3700");
        p4.put("proQuantity","4");
        p4.put("prodescription","Dual SIM - 6.67 Inch, 128 GB, 6 GB RAM, 4G LTE - Glacier White");
        p4.put("productQrcode","6223007311694");
        p4.put("cat_id","1");
        db.insert("products",null,p4);

        ContentValues p5 = new ContentValues();
        p5.put("proName","Huawei Nova 7i");
        p5.put("proPrice","4000");
        p5.put("proQuantity","8");
        p5.put("prodescription","Dual SIM - 128 GB, 8 GB RAM, 4G LTE - Crush Green");
        p5.put("cat_id","1");
        db.insert("products",null,p5);

        ContentValues p6 = new ContentValues();
        p6.put("proName","Xiaomi POCO X3");
        p6.put("proPrice","4210");
        p6.put("proQuantity","5");
        p6.put("prodescription","Dual SIM Mobile, 6.67 Inches, 128 GB, 6 GB RAM, 4G LTE - Cobalt Blue");
        p6.put("cat_id","1");
        db.insert("products",null,p6);

        ContentValues p7 = new ContentValues();
        p7.put("proName","Samsung Galaxy Note 10 Lite");
        p7.put("proPrice","7570");
        p7.put("proQuantity","9");
        p7.put("prodescription","Dual SIM - 128GB, 8GB RAM, 4G LTE, Aura Red");
        p7.put("cat_id","1");
        db.insert("products",null,p7);

        ContentValues p8 = new ContentValues();
        p8.put("proName","Realme 6");
        p8.put("proPrice","4300");
        p8.put("proQuantity","7");
        p8.put("prodescription","Dual SIM - 128GB, 8GB RAM, 4G LTE - Comet Blue");
        p8.put("cat_id","1");
        db.insert("products",null,p8);

        ContentValues p9 = new ContentValues();
        p9.put("proName","Xiaomi Mi Note 10 Lite");
        p9.put("proPrice","5700");
        p9.put("proQuantity","6");
        p9.put("prodescription","Dual SIM Mobile - 6.47 Inch, 128 GB, 8 GB RAM, 4G LTE - Nebula Purple");
        p9.put("cat_id","1");
        db.insert("products",null,p9);

        ContentValues p10 = new ContentValues();
        p10.put("proName","Infinix X653 Smart 4");
        p10.put("proPrice","1300");
        p10.put("proQuantity","3");
        p10.put("prodescription","Dual SIM, 16 GB, 1 GB RAM, 4G LTE, 6.6 Inch - Purple");
        p10.put("cat_id","1");
        db.insert("products",null,p10);

        ContentValues p11 = new ContentValues();
        p11.put("proName","Canon EOS 2000D");
        p11.put("proPrice","6000");
        p11.put("proQuantity","2");
        p11.put("prodescription","Canon 2000D camera lets you taste the joy of photography");
        p11.put("cat_id","2");
        db.insert("products",null,p11);

        ContentValues p12 = new ContentValues();
        p12.put("proName","Nikon COOLPIX B500");
        p12.put("proPrice","4335");
        p12.put("proQuantity","9");
        p12.put("prodescription","Capture stunning photos and videos and share special memories");
        p12.put("cat_id","2");
        db.insert("products",null,p12);

        ContentValues p13 = new ContentValues();
        p13.put("proName","Fujifilm Finepix XP120");
        p13.put("proPrice","2650");
        p13.put("proQuantity","4");
        p13.put("prodescription"," 16.4MP, 20m UnderWater Digital Camera, Yellow");
        p13.put("cat_id","2");
        db.insert("products",null,p13);

        ContentValues p14 = new ContentValues();
        p14.put("proName","Nikon Coolpix B600");
        p14.put("proPrice","5525");
        p14.put("proQuantity","7");
        p14.put("prodescription","16 MP 60X Optical Zoom Full HD WIFI Digital Camera Black");
        p14.put("cat_id","2");
        db.insert("products",null,p14);

        ContentValues p15 = new ContentValues();
        p15.put("proName","Nikon Body Only");
        p15.put("proPrice","8480");
        p15.put("proQuantity","6");
        p15.put("prodescription","24.2 MP ,1x Optical Zoom and 3.2 Inch Screen - D5600");
        p15.put("productQrcode","6221133002547");
        p15.put("cat_id","2");
        db.insert("products",null,p15);

        ContentValues p16 = new ContentValues();
        p16.put("proName","Nikon SLR Camera");
        p16.put("proPrice","5300");
        p16.put("proQuantity","5");
        p16.put("prodescription","24.2 MP ,No Zoom Optical Zoom and 3.2 Inch Screen - D3500");
        p16.put("cat_id","2");
        db.insert("products",null,p16);

        ContentValues p17 = new ContentValues();
        p17.put("proName","Canon EOS 6D");
        p17.put("proPrice","23500");
        p17.put("proQuantity","10");
        p17.put("prodescription","Camera features a 26.2MP image sensor and Dual Pixel CMOS AF");
        p17.put("cat_id","2");
        db.insert("products",null,p17);

        ContentValues p18 = new ContentValues();
        p18.put("proName","Nikon D3400");
        p18.put("proPrice","9635");
        p18.put("proQuantity","3");
        p18.put("prodescription","24.2 MP SLR Camera Black AF-P 18 - 55mm f/3.5 - 5.6 G VR Lens, Black + Nikon AF-P DX NIKKOR");
        p18.put("cat_id","2");
        db.insert("products",null,p18);

        ContentValues p19 = new ContentValues();
        p19.put("proName","Fujifilm FinePix XP140");
        p19.put("proPrice","3600");
        p19.put("proQuantity","5");
        p19.put("prodescription","16MP- 5X Zoom, 4K, WIFI, BT-25M Underwater");
        p19.put("cat_id","2");
        db.insert("products",null,p19);

        ContentValues p20 = new ContentValues();
        p20.put("proName","FUJIFILM X-A5");
        p20.put("proPrice","7200");
        p20.put("proQuantity","4");
        p20.put("prodescription","Mirrorless Digital Camera (Brown) with 16-50mm Lens");
        p20.put("cat_id","2");
        db.insert("products",null,p20);

        ContentValues p21 = new ContentValues();
        p21.put("proName","Dell VOSTRO 3501");
        p21.put("proPrice","7300");
        p21.put("proQuantity","5");
        p21.put("prodescription","ntel 10th Gen Core i3-1005G1, 4 GBRAM ,1TB HDD,Intel UHD Graphics, 15.6-Inch");
        p21.put("cat_id","3");
        db.insert("products",null,p21);

        ContentValues p22 = new ContentValues();
        p22.put("proName","Lenovo YOGA 730");
        p22.put("proPrice","30000");
        p22.put("proQuantity","3");
        p22.put("prodescription"," Core i7-8550U, 15.6 Inch 4K UHD , 16GB, 1TB, nVidia GTX1050 4GB, Win10");
        p22.put("cat_id","3");
        db.insert("products",null,p22);

        ContentValues p23 = new ContentValues();
        p23.put("proName","Apple MacBook Pro");
        p23.put("proPrice","43000");
        p23.put("proQuantity","3");
        p23.put("prodescription","MVVK2 with Touch Bar and Touch ID Laptop, 9th Gen-Intel Core i9, 2.3 Ghz");
        p23.put("cat_id","3");
        db.insert("products",null,p23);

        ContentValues p24 = new ContentValues();
        p24.put("proName","DELL Latitude 5300");
        p24.put("proPrice","13100");
        p24.put("proQuantity","4");
        p24.put("prodescription","Intel Core i5-8365U, 8GBRAM, 256GB PCIe NVMe M.2 SSD");
        p24.put("cat_id","3");
        db.insert("products",null,p24);

        ContentValues p25 = new ContentValues();
        p25.put("proName","Hp 15-GW0056nia");
        p25.put("proPrice","6700");
        p25.put("proQuantity","5");
        p25.put("prodescription","Athlon 3150U, 4GB RAM , 1TB HDD, AMD Radeon Graphics, 15.6 HD , Dos");
        p25.put("cat_id","3");
        db.insert("products",null,p25);

        ContentValues p26 = new ContentValues();
        p26.put("proName","Lenovo ideapad L340");
        p26.put("proPrice","16450");
        p26.put("proQuantity","1");
        p26.put("prodescription","15IRH Gaming - intel core i5-9300H, 8GB RAM , 1TB SSD, NVIDIA GeForce GTX 1650");
        p26.put("cat_id","3");
        db.insert("products",null,p26);

        ContentValues p27 = new ContentValues();
        p27.put("proName","Acer Aspire 3 A315");
        p27.put("proPrice","7800");
        p27.put("proQuantity","2");
        p27.put("prodescription","15.6 Inch HD, AMD Ryzen 5-3500U, 1 TB HDD, 4 GB RAM, AMD Radeon Graphics, Windows");
        p27.put("cat_id","3");
        db.insert("products",null,p27);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customers");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists products");
        db.execSQL("drop table if exists orderDetails");
        db.execSQL("drop table if exists cartproducts");
        onCreate(db);
    }


    public void customerSignUp(CustomerModel customer){
        ContentValues values = new ContentValues();
        values.put("custname",customer.getName());
        values.put("custUsername",customer.getUsername());
        values.put("custpassword",customer.getPassword());
        values.put("custBirthdate",customer.getBirthdate());
        values.put("custjob",customer.getJob());
        values.put("custGenger",customer.getGender());
        ecommerceDatabase = getWritableDatabase();
        ecommerceDatabase.insert("customers",null,values);
        ecommerceDatabase.close();
    }

    public Cursor customerlogin(String userName, String password){
        ecommerceDatabase = getReadableDatabase();
        String [] args = {userName,password};
        Cursor cursor = ecommerceDatabase.rawQuery("select custid from customers where custUsername=? and custpassword =?",args);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        ecommerceDatabase.close();
        return cursor;
    }

    public String recoverPassword(String username){
        ecommerceDatabase = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = ecommerceDatabase.rawQuery("select custpassword from customers where custUsername =?",arg);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            ecommerceDatabase.close();
            return cursor.getString(0);
        }
        ecommerceDatabase.close();
        cursor.close();
        return null;
    }

    public Cursor getProducts(String catid){
        ecommerceDatabase = getReadableDatabase();
        String [] args = {catid};
        Cursor cursor = ecommerceDatabase.rawQuery("select * from products where cat_id like? ",args);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        ecommerceDatabase.close();
        return cursor;
    }

    public void cartProducts(ProductModel cart){

        ContentValues row = new ContentValues();
        row.put("productid",cart.getId());
        row.put("productname",cart.getName());
        row.put("productprice",cart.getPrice());
        row.put("productdescription",cart.getDescription());
        row.put("productquantity",cart.getQuantity());
        ecommerceDatabase = getWritableDatabase();
        ecommerceDatabase.insert("cartproducts",null,row);
        ecommerceDatabase.close();
    }

    public Cursor getCartProducts(){
        ecommerceDatabase = getReadableDatabase();

        String [] rowDetails = {"productid","productname","productprice","productdescription","productquantity"};
        Cursor cursor = ecommerceDatabase.query("cartproducts",rowDetails,null,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        ecommerceDatabase.close();
        return cursor;
    }

    public Cursor searchProducts(String name){
        ecommerceDatabase = getReadableDatabase();

        String[] args = {"%"+name+"%"};
        Cursor cursor = ecommerceDatabase.rawQuery("select * from products where proName like?",args);

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            ecommerceDatabase.close();
            return cursor;
        }
        ecommerceDatabase.close();
        return null;

    }

    public Cursor SearchQrcode(String Qrcode){
        ecommerceDatabase = getReadableDatabase();

        String[] args = {Qrcode};
        Cursor cursor = ecommerceDatabase.rawQuery("select * from products where productQrcode like?",args);

        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            ecommerceDatabase.close();
            return cursor;
        }
        ecommerceDatabase.close();
        return null;

    }
    public void deleteCartProducts(String productName){
        ecommerceDatabase = getWritableDatabase();
        ecommerceDatabase.delete("cartproducts","productname = '"+productName+"'",null);
        ecommerceDatabase.close();
    }

}

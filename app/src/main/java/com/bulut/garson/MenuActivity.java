package com.bulut.garson;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.bulut.garson.Product.basket;
import static com.bulut.garson.Product.products;


public class MenuActivity extends AppCompatActivity {



    public static ArrayList<Product> starters = new ArrayList<>();
    public static ArrayList<Product> snacks = new ArrayList<>();
    public static ArrayList<Product> mainDishes = new ArrayList<>();
    public static ArrayList<Product> beverages = new ArrayList<>();
    public static ArrayList<Product> desserts = new ArrayList<>();

    String restaurantName;
    ImageView imageView;
    ListView listView;
    public TextView basketText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageView= findViewById(R.id.imageView);
        basketText = (TextView)findViewById(R.id.textView);

        basketText.setText("Sepet Toplamı: " + BasketActivity.getTotalPrice() + "");

        Barcode barcode = getIntent().getParcelableExtra("barcodeValue");

        //setTitle(barcode.displayValue);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#DDCD96\">" + barcode.displayValue + "</font>")));

        products.add(new Product(5, "Mercimek Çorbası", "Limon ile birlikte servis edilir.", "Başlangıçlar") );
        products.add(new Product(10, "Patates Kızartması", "Cajun baharatı ile servis edilir.", "Atıştırmalıklar") );
        products.add(new Product(7, "Soğan Halkası", "16 adet servis edilir.", "Atıştırmalıklar") );
        products.add(new Product(20, "T-Bone Steak", "200gr et.", "Ana Yemekler") );
        products.add(new Product(5, "Fesleğenli Ayran", "Yayık ayranın fesleğenle buluşması.", "İçecekler") );

        products.add(new Product(7, "Domates Çorbası", "Yanında taze kaşarla servis edilir.", "Başlangıçlar") );
        products.add(new Product(20, "Kuzu Şiş", "Izgarada taptaze kuzu eti.", "Ana Yemekler") );
        products.add(new Product(18, "Tavuklu Mantarlı Makarna", "Taze fesleğenle servis edilir.", "Ana Yemekler") );

        products.add(new Product(9, "Coca Cola", "330 ml", "İçecekler") );
        products.add(new Product(9, "Gazoz", "330 ml", "İçecekler") );
        products.add(new Product(12, "Limonata", "Naneli veya sade buz gibi limonata", "İçecekler") );


        if(!( barcode.displayValue.indexOf('B') == -1 ) ) {
            for(Product product : products){
                product.changePrice();
            }
            products.add(new Product(30, "Dana Lokum", "Pirinç pilavı ve haşlanmış sebzelerle servis edilir.", "Ana Yemekler"));
            products.add(new Product(15, "San Sebastian Cheesecake", "İspanya'nın Bask bölgesine ait muhteşem bir Cheesecake", "Tatlılar") );
            products.add(new Product(12, "Baklava", "Cevizli veya fıstıklı antep baklavası.", "Tatlılar") );

            restaurantName = "bigchefs";
        }
        else {
            restaurantName = "mickeys";
            products.add(new Product(12, "Paçanga Böreği", "Sucuklu veya pastırmalı.", "Atıştırmalıklar") );
            products.add(new Product(8, "İşkembe Çorbası", "Sarımsak sos ile servis edilir.", "Başlangıçlar" ));

            products.add(new Product(14, "Sufle", "İçinde akışkan çikolatasıyla.", "Tatlılar") );
            products.add(new Product(10, "Magnolia", "Oreo parçacıklı.", "Tatlılar") );

        }

        imageView.setImageURI(Uri.parse("android.resource://com.bulut.garson/drawable/" + restaurantName));


        for(Product product : products){
            if("Başlangıçlar".equals(product.getType())){
                starters.add(product);
            }
            else if("Atıştırmalıklar".equals(product.getType())){
                snacks.add(product);
            }
            else if("Ana Yemekler".equals(product.getType())){
                mainDishes.add(product);
            }
            else if("İçecekler".equals(product.getType())){
                beverages.add(product);
            }
            else if("Tatlılar".equals(product.getType())){
                desserts.add(product);
            }
        }

        listView = (ListView) findViewById(R.id.listView);

        final String[] categories = {"Başlangıçlar", "Atıştırmalıklar", "Ana Yemekler", "İçecekler", "Tatlılar"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categories);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Clicked: " + parent.getItemAtPosition(position) );
                System.out.println("Products: " + products );

                Intent intent = new Intent(MenuActivity.this, ProductActivity.class);
                intent.putExtra("pressedType", (String) parent.getItemAtPosition(position));

                startActivity(intent);
            }
        });
    }

    public static String[] findProductByType(String type){

        ArrayList<String> arl = new ArrayList<>();

        for(Product product : products){
            if(type.equals(product.getType())){
                arl.add(product.getName());
            }
        }

        String[] newProducts = new String[arl.size()];
        arl.toArray(newProducts);
        return newProducts;
    }

    public static ArrayList<Product> getProductsByType(String type){

        ArrayList<Product> arl = new ArrayList<>();

        for(Product product : products){
            if(type.equals(product.getType())){
                arl.add(product);
            }
        }
        return arl;
    }

    public static Product getProductByName(String name){

        ArrayList<String> arl = new ArrayList<>();

        for(Product product : products){
            if(name.equals(product.getName())){
                return product;
            }
        }
        return null;
    }

    public void onClick(View v) {

        Intent intent = new Intent(MenuActivity.this, BasketActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        basket.clear();
        products.clear();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        basketText.setText("Sepet Toplamı: " + BasketActivity.getTotalPrice() + "₺");
    }
}

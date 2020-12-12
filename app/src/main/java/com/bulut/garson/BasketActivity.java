package com.bulut.garson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bulut.garson.Product.basket;
import static com.bulut.garson.Product.products;

public class BasketActivity extends AppCompatActivity {

    public static float total = 0;
    ListView listView;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#DDCD96\">" + "Sepetiniz" + "</font>")));

        System.out.println(Arrays.toString(getBasket()));
        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Toplam: " + getTotalPrice() + " ₺");
        final BasketAdapter newAdapter = new BasketAdapter(this, basket);

        button = findViewById(R.id.button2);

        button.setText("Siparişi Tamamla");

        if(getTotalPrice()==0 ){
            button.setEnabled(false);
        }
        else{
            button.setEnabled(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        listView.setAdapter(newAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product productToRemove = newAdapter.getItem(position);

                System.out.println("Basket: " + basket.toString());


                Toast.makeText(getApplicationContext(),( newAdapter.getItem(position).getName() + " sepetinizden çıkarıldı!"),Toast.LENGTH_SHORT).show();
                basket.remove(productToRemove);

                listView.setAdapter(newAdapter);
                textView.setText("Toplam: " + getTotalPrice() + " ₺");

                if(getTotalPrice()==0 ){
                    button.setEnabled(false);
                }
                else{
                    button.setEnabled(true);
                }
            }
        });



    }

    public void addToBasket(Product product){
        basket.add(product);
    }

    public static float getTotalPrice() {
        int sum = 0;

        if (basket.size() != 0) {


        for (Product product : basket) {
            sum += product.getPrice();
        }
        total = sum;

    }
        return sum;
    }

    public static String[] getBasket(){

        String[] basketToReturn;
        basketToReturn = new String[basket.size()];

        for(int i=0; i<  basket.size();i++ ){

            basketToReturn[i] = basket.get(i).getName();

        }

        return basketToReturn;
    }

}

package com.bulut.garson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

import static com.bulut.garson.Product.basket;
import static com.bulut.garson.Product.products;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#DDCD96\">" + "Siparişiniz Tamamlandı" + "</font>")));




    }

    @Override
    public void onBackPressed() {
        products.clear();
        basket.clear();
        Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

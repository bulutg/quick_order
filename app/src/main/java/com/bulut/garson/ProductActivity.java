package com.bulut.garson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.bulut.garson.Product.basket;

public class ProductActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        String type = getIntent().getExtras().getString("pressedType");

        getSupportActionBar().setTitle((Html.fromHtml("<font color=\"#DDCD96\">" + type + "</font>")));

        System.out.println("type: " + type);

        listView = (ListView) findViewById(R.id.listView);

        String[] categories = MenuActivity.findProductByType(type);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categories);
        //listView.setAdapter(adapter);

        final customAdapter newAdapter = new customAdapter(this, MenuActivity.getProductsByType(type));

        listView.setAdapter(newAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent.getItemAtPosition(position).toString());

                Product productToAdd = newAdapter.getItem(position);

                basket.add(productToAdd);

                System.out.println("Basket: " + basket.toString());

                BasketActivity.getTotalPrice();

                Toast.makeText(getApplicationContext(),( newAdapter.getItem(position).getName() + " sepetinize eklendi!"),Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package nsl.nuziveedu_seeds.com.restaruents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import nsl.nuziveedu_seeds.com.restaruents.Adapters.CartAdapter;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.ViewModel.MainActivityViewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart_screen extends AppCompatActivity implements CartAdapter.cart{


    RecyclerView cart_list;

    MainActivityViewmodel mainActivityViewmodel;

    Intent intent;

    TextView rate_price,show_more;

    CartAdapter cartAdapter;


    List<Cat_dishitems_model> cat_dishitems_models;


    List<Cat_dishitems_model> sub_cart = new ArrayList<Cat_dishitems_model>();


    int total_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        init();


        total_price = mainActivityViewmodel.getttalprice(cat_dishitems_models);

        rate_price.setText("RS. " + String.valueOf(total_price));


        sub_cart=mainActivityViewmodel.get_cart_item(cat_dishitems_models);

        if (sub_cart.size()<=2)
        {

            cartAdapter=new CartAdapter(Cart_screen.this,sub_cart,Cart_screen.this);
            cart_list.setAdapter(cartAdapter);

            show_more.setVisibility(View.GONE);

        }
        else
        {


            cartAdapter=new CartAdapter(Cart_screen.this,sub_cart.subList(0,2),Cart_screen.this);
            cart_list.setAdapter(cartAdapter);


            show_more.setVisibility(View.VISIBLE);

        }


        show_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_more.setVisibility(View.GONE);


                cartAdapter=new CartAdapter(Cart_screen.this,sub_cart,Cart_screen.this);


                cart_list.setAdapter(cartAdapter);



            }
        });





    }

    private void init() {

        cart_list = (RecyclerView) findViewById(R.id.cart_list);

        rate_price = (TextView) findViewById(R.id.rate_price);

        show_more=(TextView)findViewById(R.id.show_more);


        intent = getIntent();

        cat_dishitems_models = (List<Cat_dishitems_model>) intent.getSerializableExtra("DISHLIST");
        Toast.makeText(Cart_screen.this, cat_dishitems_models.size() + "", Toast.LENGTH_SHORT).show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Cart_screen.this);
        cart_list.setLayoutManager(layoutManager);


        mainActivityViewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel.class);

    }

    public void back_arrow(View view) {

        Intent intent=new Intent();

        intent.putExtra("STREAMDATA",(Serializable) cat_dishitems_models);

        setResult(2,intent);

        finish();    }


    @Override
    public void min(int i, TextView in_txt, Cart_screen cart_screen, List<Cat_dishitems_model> sub_cart) {
        mainActivityViewmodel.change(i, in_txt, null, sub_cart, null);
        total_price = mainActivityViewmodel.getttalprice(sub_cart);
        rate_price.setText("RS. " + String.valueOf(total_price));



    }

    @Override
    public void add(int i, TextView in_txt, Cart_screen cart_screen, List<Cat_dishitems_model> sub_cart) {
        mainActivityViewmodel.change(i, in_txt, null, sub_cart, null);


        total_price = mainActivityViewmodel.getttalprice(sub_cart);

        rate_price.setText("RS. " + String.valueOf(total_price));




    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent();

        intent.putExtra("STREAMDATA",(Serializable) cat_dishitems_models);

        setResult(2,intent);

        finish();

    }
}

package nsl.nuziveedu_seeds.com.restaruents;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import nsl.nuziveedu_seeds.com.restaruents.Adapters.Custom_Dish_items_adapter;
import nsl.nuziveedu_seeds.com.restaruents.Adapters.Custom_cat_dish_items_list;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.Model.Dish_items_model;
import nsl.nuziveedu_seeds.com.restaruents.ViewModel.MainActivityViewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Custom_Dish_items_adapter.dishmenu, Custom_cat_dish_items_list.inc_or_dic {
    RecyclerView recyclerView;
    TextView menu;
    Dialog dialog;

    MainActivityViewmodel mainActivityViewmodel;


    RecyclerView.LayoutManager layoutManager;


    int selectedposition;


    CardView info_card;
    ListView listview_dishes;

    CollapsingToolbarLayout collapsingToolbarLayout;

    AppBarLayout appBarLayout;


    Button cart_button;


    Custom_Dish_items_adapter custom_dish_items;

    Custom_cat_dish_items_list custom_cat_dish_items_list;


    List<Cat_dishitems_model> cat_dishitems_models = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        mainActivityViewmodel.preparedata_catdishitem();

        mainActivityViewmodel.getcat_Dishitems().observe(MainActivity.this, new Observer<List<Cat_dishitems_model>>() {
            @Override
            public void onChanged(@Nullable List<Cat_dishitems_model> cat_dishitems_models) {


                MainActivity.this.cat_dishitems_models = cat_dishitems_models;


                custom_cat_dish_items_list = new Custom_cat_dish_items_list(MainActivity.this, cat_dishitems_models, MainActivity.this);

                recyclerView.setAdapter(custom_cat_dish_items_list);


            }
        });



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                setDialogPosition(menu, dialog);
                mainActivityViewmodel.preparedata();
                mainActivityViewmodel.getDishitems().observe(MainActivity.this, new Observer<List<Dish_items_model>>() {
                    @Override
                    public void onChanged(@Nullable List<Dish_items_model> dish_items_models) {
                        custom_dish_items = new Custom_Dish_items_adapter(MainActivity.this, dish_items_models, MainActivity.this, selectedposition);
                        listview_dishes.setAdapter(custom_dish_items);
                    }
                });
            }
        });


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();


                }
                if (scrollRange + verticalOffset == 0) {


                    info_card.setVisibility(View.GONE);
                } else if (isShow) {

                    info_card.setVisibility(View.VISIBLE);
                }
            }
        });


        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, Cart_screen.class);


                intent.putExtra("DISHLIST",(Serializable) cat_dishitems_models);

                startActivityForResult(intent,3);


            }
        });


    }


    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.dish_layout_list);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        menu = (TextView) findViewById(R.id.type_menu);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_tool_bar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);


        cart_button = (Button) findViewById(R.id.cart_btn);
        cart_button.setVisibility(View.INVISIBLE);

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.items_dialog);
        info_card = (CardView) findViewById(R.id.card_details_rest);
        listview_dishes = (ListView) dialog.findViewById(R.id.listview_dishes);
        mainActivityViewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel.class);

    }


    private void setDialogPosition(View source, Dialog dialog) {
        if (source == null) {
            return; // Leave the dialog in default position
        }

        // Find out location of source component on screen
        // see https://stackoverflow.com/a/6798093/56285
        int[] location = new int[2];
        source.getLocationOnScreen(location);
        int sourceX = location[0];
        int sourceY = location[1];

        Window window = dialog.getWindow();

        // set "origin" to top left corner
        window.setGravity(Gravity.TOP | Gravity.LEFT);

        WindowManager.LayoutParams params = window.getAttributes();
        // Just an example; edit to suit your needs.
        params.x = sourceX - dpToPx(110); // about half of confirm button size left of source view
        params.y = sourceY - dpToPx(150); // above source view

        window.setAttributes(params);
    }

    public int dpToPx(float valueInDp) {
        DisplayMetrics metrics = MainActivity.this.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    @Override
    public void onclick(final int i, View view, TextView dishname, List<Dish_items_model> dish_items_models, MainActivity mainActivity) {


        this.selectedposition = i;


        for (int w = 0; w < cat_dishitems_models.size(); w++) {


            if (dish_items_models.get(i).getId().equals(cat_dishitems_models.get(w).getId())) {


                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(MainActivity.this) {
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };

                smoothScroller.setTargetPosition(w);

                layoutManager.startSmoothScroll(smoothScroller);


                appBarLayout.setExpanded(false, true);

            }

        }





        mainActivityViewmodel.preparedata();
        mainActivityViewmodel.getDishitems().observe(MainActivity.this, new Observer<List<Dish_items_model>>() {
            @Override
            public void onChanged(@Nullable List<Dish_items_model> dish_items_models) {
                custom_dish_items = new Custom_Dish_items_adapter(MainActivity.this, dish_items_models, MainActivity.this, selectedposition);
                listview_dishes.setAdapter(custom_dish_items);
            }
        });



    }

    @Override
    public void min(int i, TextView in_txt, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models) {
        mainActivityViewmodel.change(i, in_txt, activity, cat_dishitems_models, custom_cat_dish_items_list);
        int cartcount = mainActivityViewmodel.get_cart_data(i, activity, cat_dishitems_models, custom_cat_dish_items_list);


        update_ui(cartcount);

    }

    @Override
    public void add(int i, TextView in_txt, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models) {
        mainActivityViewmodel.change(i, in_txt, activity, cat_dishitems_models, custom_cat_dish_items_list);


        int cartcount = mainActivityViewmodel.get_cart_data(i, activity, cat_dishitems_models, custom_cat_dish_items_list);


        update_ui(cartcount);
    }

    private void update_ui(int cartcount) {


        if (cartcount>0)
        {
            cart_button.setVisibility(View.VISIBLE);
            cart_button.setText("View Cart" + " [" + cartcount + "] ");

        }
        else
        {
            cart_button.setVisibility(View.INVISIBLE);


        }




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==2)
        {




            if (data.getSerializableExtra("STREAMDATA")!=null)
            {


                cat_dishitems_models= (List<Cat_dishitems_model>) data.getSerializableExtra("STREAMDATA");


                mainActivityViewmodel.setvalue(cat_dishitems_models);


                int cartcount = mainActivityViewmodel.get_cart_data(0, null, cat_dishitems_models, null);


                update_ui(cartcount);


            }






        }
    }
}

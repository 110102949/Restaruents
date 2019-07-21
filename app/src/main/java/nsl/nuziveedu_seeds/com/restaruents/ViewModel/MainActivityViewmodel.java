package nsl.nuziveedu_seeds.com.restaruents.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.TextView;
import nsl.nuziveedu_seeds.com.restaruents.Adapters.Custom_cat_dish_items_list;
import nsl.nuziveedu_seeds.com.restaruents.MainActivity;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.Model.Dish_items_model;
import nsl.nuziveedu_seeds.com.restaruents.Repository.Cat_dishitems_Repository;
import nsl.nuziveedu_seeds.com.restaruents.Repository.MainRepostory;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewmodel extends ViewModel {


    int cart_count;

    MutableLiveData<List<Dish_items_model>> dish_items_modelMutableLiveData = new MutableLiveData<>();

    MutableLiveData<List<Cat_dishitems_model>> cat_dish_item_repo = new MutableLiveData<>();

    MainRepostory mainRepostory;

    Cat_dishitems_Repository cat_dishitems_repository;


    public static MainActivityViewmodel mainActivityViewmodel;


    public MainActivityViewmodel getintialize() {

        if (mainActivityViewmodel != null) {


            return mainActivityViewmodel;
        } else {

            return new MainActivityViewmodel();
        }


    }

    public void preparedata() {
        mainRepostory = MainRepostory.getInstance();
        dish_items_modelMutableLiveData = mainRepostory.getDishescat();
    }


    public LiveData<List<Dish_items_model>> getDishitems() {

        return dish_items_modelMutableLiveData;
    }


    public void preparedata_catdishitem() {
        cat_dishitems_repository = Cat_dishitems_Repository.getInstance();
        cat_dish_item_repo = cat_dishitems_repository.getDishescat();
    }


    public LiveData<List<Cat_dishitems_model>> getcat_Dishitems() {

        return cat_dish_item_repo;
    }


    public void change(final int i, final TextView in_txt, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models, Custom_cat_dish_items_list custom_cat_dish_items_list) {


        if (in_txt!=null)
        {

            cat_dishitems_models.get(i).setCount(in_txt.getText().toString());




        }
        else
        {
            cat_dishitems_models.get(i).setCount("0");




        }



    }


    public int get_cart_data(int i, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models, Custom_cat_dish_items_list custom_cat_dish_items_list) {


        cart_count=0;
        for (int a = 0; a < cat_dishitems_models.size(); a++) {
            if (Integer.parseInt(cat_dishitems_models.get(a).getCount()) > 0) {

                cart_count=cart_count+Integer.parseInt(cat_dishitems_models.get(a).getCount());

            }
        }
        return cart_count;
    }


    public int getttalprice(List<Cat_dishitems_model> cat_dishitems_models) {

        int total_price=0;


        for (int i = 0; i < cat_dishitems_models.size(); i++) {

            if (Integer.parseInt(cat_dishitems_models.get(i).getCount()) > 0) {


                total_price = total_price + Integer.parseInt(cat_dishitems_models.get(i).getDishprice()) * Integer.parseInt(cat_dishitems_models.get(i).getCount());




            }

        }


        return  total_price;

    }

    public List<Cat_dishitems_model> get_cart_item(List<Cat_dishitems_model> cat_dishitems_models) {


        List<Cat_dishitems_model> subcart = new ArrayList<>();



        for (Cat_dishitems_model cat_dishitems_model:cat_dishitems_models)
        {


            if (Integer.parseInt(cat_dishitems_model.getCount())>0)
            {


                subcart.add(cat_dishitems_model);

            }

        }



        return subcart;
    }

    public void setvalue(List<Cat_dishitems_model> cat_dishitems_models) {


        cat_dish_item_repo.setValue(cat_dishitems_models);

    }
}

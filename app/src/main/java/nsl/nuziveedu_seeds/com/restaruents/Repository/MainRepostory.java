package nsl.nuziveedu_seeds.com.restaruents.Repository;

import android.arch.lifecycle.MutableLiveData;
import nsl.nuziveedu_seeds.com.restaruents.Model.Dish_items_model;

import java.util.ArrayList;
import java.util.List;

public class MainRepostory {


    List<Dish_items_model> dish_items_models = new ArrayList<Dish_items_model>();

    private static MainRepostory instance;

    public static MainRepostory getInstance() {
        if (instance == null) {
            instance = new MainRepostory();
        }
        return instance;
    }


    public MutableLiveData<List<Dish_items_model>> getDishescat() {
        MutableLiveData<List<Dish_items_model>> dish_cat =
                new MutableLiveData<>();
        dish_items_models.clear();
        dish_items_models.add(new Dish_items_model("Starters", "3", "1"));
        dish_items_models.add(new Dish_items_model("Main Course", "3", "2"));
        dish_items_models.add(new Dish_items_model("Dessert", "3", "3"));
        dish_items_models.add(new Dish_items_model("Shakes", "3", "4"));
        dish_cat.setValue(dish_items_models);

        return dish_cat;
    }


}

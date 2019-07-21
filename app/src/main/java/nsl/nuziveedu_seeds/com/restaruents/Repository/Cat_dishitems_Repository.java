package nsl.nuziveedu_seeds.com.restaruents.Repository;

import android.arch.lifecycle.MutableLiveData;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.Model.Dish_items_model;

import java.util.ArrayList;
import java.util.List;

public class Cat_dishitems_Repository {



    List<Cat_dishitems_model> cart_dish_item_models = new ArrayList<Cat_dishitems_model>();

    private static Cat_dishitems_Repository instance;

    public static Cat_dishitems_Repository getInstance() {
        if (instance == null) {
            instance = new Cat_dishitems_Repository();
        }
        return instance;
    }


    public MutableLiveData<List<Cat_dishitems_model>> getDishescat() {
        MutableLiveData<List<Cat_dishitems_model>> dish_cat =
                new MutableLiveData<>();
        cart_dish_item_models.clear();
        cart_dish_item_models.add(new Cat_dishitems_model("Starter","","","",0,"0","1"));
        cart_dish_item_models.add(new Cat_dishitems_model("","Paneer Tikka","220","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Veg Manchrian","200","",1,"0",""));

        cart_dish_item_models.add(new Cat_dishitems_model("","Chicken Tikka","220","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Chilli Chicken","200","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Pepper Chicken","220","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Chicken Fry","200","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Mutton 65","220","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Alu  65","200","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Gobi Chilli","220","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Paneer Chilli","200","",1,"0",""));




        cart_dish_item_models.add(new Cat_dishitems_model("Main Course","","","",0,"0","2"));
        cart_dish_item_models.add(new Cat_dishitems_model("","Dal Fry","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Kadai Paneer","350","",1,"0",""));


        cart_dish_item_models.add(new Cat_dishitems_model("","Alu Fry","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Alu Gobi Masala","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Kadai Masala","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Butter Veg","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Veg Chatpata","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Veg Kolhapuri","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Paneer Butter Masala","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Kaju Paneer","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Palak Paneer","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Alu Palak","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Malai Kofta","230","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Veg Kofta","350","",1,"0",""));


        cart_dish_item_models.add(new Cat_dishitems_model("Dessert","","","",0,"0","3"));
        cart_dish_item_models.add(new Cat_dishitems_model("","Serve Strawberry","80","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Flurry Oreo","76","",1,"0",""));

        cart_dish_item_models.add(new Cat_dishitems_model("","Nuetella Strawberry","80","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Chocolate Oreo","76","",1,"0",""));














        cart_dish_item_models.add(new Cat_dishitems_model("Shakes","","","",0,"0","4"));
        cart_dish_item_models.add(new Cat_dishitems_model("","Mango Shake","250","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Walnut Brownie Shake ","350","",1,"0",""));


        cart_dish_item_models.add(new Cat_dishitems_model("","Strawberry Shake","250","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Badam Shake ","350","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Anjeer Shake","250","",1,"0",""));
        cart_dish_item_models.add(new Cat_dishitems_model("","Fruit Punch Shake ","350","",1,"0",""));



        dish_cat.setValue(cart_dish_item_models);

        return dish_cat;
    }

}

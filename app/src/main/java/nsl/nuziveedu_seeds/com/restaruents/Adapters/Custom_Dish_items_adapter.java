package nsl.nuziveedu_seeds.com.restaruents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import nsl.nuziveedu_seeds.com.restaruents.MainActivity;
import nsl.nuziveedu_seeds.com.restaruents.Model.Dish_items_model;
import nsl.nuziveedu_seeds.com.restaruents.R;

import java.util.List;

public class Custom_Dish_items_adapter extends BaseAdapter {


    MainActivity mainActivity;
    List<Dish_items_model> dish_items_models;
    MainActivity activity;
    int selectedposition;
    public Custom_Dish_items_adapter(MainActivity mainActivity, List<Dish_items_model> dish_items_models, MainActivity activity, int selectedposition) {


        this.mainActivity = mainActivity;

        this.activity = activity;

        this.dish_items_models = dish_items_models;


        this.selectedposition=selectedposition;

    }

    @Override
    public int getCount() {
        return dish_items_models.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.dish_items, viewGroup, false);
        final TextView dishname, dishcount;
        dishname = (TextView) view1.findViewById(R.id.dish_cat_name);
        dishcount = (TextView) view1.findViewById(R.id.count_text);


        if (selectedposition==i)
        {

            dishname.setTextColor(mainActivity.getResources().getColor(R.color.touch));

        }
        else
        {

            dishname.setTextColor(mainActivity.getResources().getColor(R.color.cardview_dark_background));


        }

        dishname.setText(dish_items_models.get(i).getDishname());
        dishcount.setText(dish_items_models.get(i).getCount());
        dishname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dishname.setTextColor(mainActivity.getResources().getColor(R.color.touch));

                activity.onclick(i, view, dishname, dish_items_models, mainActivity);
            }
        });



        return view1;
    }


    public interface dishmenu {


        void onclick(int i, View view, TextView dishname, List<Dish_items_model> dish_items_models, MainActivity mainActivity);
    }
}

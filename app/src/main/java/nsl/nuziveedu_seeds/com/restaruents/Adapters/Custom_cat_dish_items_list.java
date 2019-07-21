package nsl.nuziveedu_seeds.com.restaruents.Adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import nsl.nuziveedu_seeds.com.restaruents.MainActivity;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.R;

import java.util.List;

public class Custom_cat_dish_items_list extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int Dish_Cat = 0;
    public static final int Dish_Name = 1;




    MainActivity mainActivity;
    List<Cat_dishitems_model> cat_dishitems_models;


    MainActivity activity;


    public Custom_cat_dish_items_list(MainActivity mainActivity, List<Cat_dishitems_model> cat_dishitems_models, MainActivity activity) {


        this.mainActivity = mainActivity;

        this.cat_dishitems_models = cat_dishitems_models;

        this.activity=activity;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(mainActivity);

        switch (i) {


            case Dish_Cat:


                View v1 = inflater.inflate(R.layout.cat_name, viewGroup,
                        false);
                viewHolder = new Cat_dish(v1);


                break;


            case Dish_Name:

                View v2 = inflater.inflate(R.layout.dish, viewGroup, false);
                viewHolder = new Dish(v2);

                break;

        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {


        switch (viewHolder.getItemViewType()) {

            case Dish_Cat:


                ((Cat_dish) viewHolder).dish_cat_name.setText(String.valueOf(cat_dishitems_models.get(i).getCategory()));


                break;


            case Dish_Name:


                ((Dish) viewHolder).dishname.setText(String.valueOf(cat_dishitems_models.get(i).getDishname()));
                ((Dish) viewHolder).dish_price.setText(String.valueOf(cat_dishitems_models.get(i).getDishprice()));

                if (Integer.parseInt(cat_dishitems_models.get(i).getCount())>0)
                {



                    ((Dish) viewHolder).last_end.setClickable(false);


                    ((Dish) viewHolder).in_txt.setText(String.valueOf(cat_dishitems_models.get(i).getCount()));

                    ((Dish) viewHolder).btn_add.setVisibility(View.VISIBLE);


                    ((Dish) viewHolder).btn_min.setVisibility(View.VISIBLE);
                }

                else
                {

                    ((Dish) viewHolder).last_end.setClickable(true);


                    ((Dish) viewHolder).in_txt.setText(String.valueOf("ADD"));

                    ((Dish) viewHolder).btn_add.setVisibility(View.GONE);


                    ((Dish) viewHolder).btn_min.setVisibility(View.GONE);





                }


                ((Dish) viewHolder).last_end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        ((Dish) viewHolder).last_end.setClickable(false);


                        ((Dish) viewHolder).in_txt.setText(String.valueOf(Integer.parseInt(cat_dishitems_models.get(i).getCount())+1));

                        ((Dish) viewHolder).btn_add.setVisibility(View.VISIBLE);


                        ((Dish) viewHolder).btn_min.setVisibility(View.VISIBLE);

                        activity.add(i, ((Dish) viewHolder).in_txt,mainActivity,cat_dishitems_models);



                    }
                });


                ((Dish) viewHolder).btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        if (Integer.parseInt(cat_dishitems_models.get(i).getCount())>=20)
                        {


                            Toast.makeText(mainActivity,"Maximum 20 Items only",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {


                            ((Dish) viewHolder).in_txt.setText(String.valueOf(Integer.parseInt(cat_dishitems_models.get(i).getCount())+1));


                            activity.add(i, ((Dish) viewHolder).in_txt,mainActivity,cat_dishitems_models);
                        }



                    }
                });


                ((Dish) viewHolder).btn_min.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if ((Integer.parseInt(cat_dishitems_models.get(i).getCount())) == 1) {



                            ((Dish) viewHolder).last_end.setClickable(true);


                            int a=Integer.parseInt((cat_dishitems_models.get(i).getCount()))-1;




                            ((Dish) viewHolder).btn_add.setVisibility(View.GONE);


                            ((Dish) viewHolder).btn_min.setVisibility(View.GONE);


                            activity.min(i, null,mainActivity,cat_dishitems_models);


                            ((Dish) viewHolder).in_txt.setText(String.valueOf("ADD"));
                        } else {

                            ((Dish) viewHolder).last_end.setClickable(true);

                            ((Dish) viewHolder).in_txt.setText(String.valueOf(Integer.parseInt(cat_dishitems_models.get(i).getCount())-1));

                            activity.min(i, ((Dish) viewHolder).in_txt,mainActivity,cat_dishitems_models);
                        }

                    }
                });


                break;


        }


    }

    @Override
    public int getItemCount() {
        return cat_dishitems_models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return cat_dishitems_models.get(position).getType();
    }

    public class Cat_dish extends RecyclerView.ViewHolder {

        TextView dish_cat_name;

        public Cat_dish(View v1) {
            super(v1);

            this.dish_cat_name = (TextView) v1.findViewById(R.id.dishname_catname);

        }
    }

    public class Dish extends RecyclerView.ViewHolder {


        TextView dishname, in_txt, btn_add, btn_min,dish_price;

        ConstraintLayout last_end;


        public Dish(View v2) {
            super(v2);


            this.dishname = (TextView) v2.findViewById(R.id.dish_name);

            this.dish_price=(TextView)v2.findViewById(R.id.dish_price);

            this.btn_add = (TextView) v2.findViewById(R.id.btn_add);
            this.in_txt = (TextView) v2.findViewById(R.id.btn_txt);
            this.btn_min = (TextView) v2.findViewById(R.id.btn_min);


            this.last_end = (ConstraintLayout) v2.findViewById(R.id.last_end);
        }
    }

    public interface inc_or_dic {


        void min(int i, TextView in_txt, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models);

        void add(int i, TextView in_txt, MainActivity activity, List<Cat_dishitems_model> cat_dishitems_models);
    }
}


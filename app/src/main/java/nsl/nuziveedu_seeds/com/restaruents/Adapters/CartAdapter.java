package nsl.nuziveedu_seeds.com.restaruents.Adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import nsl.nuziveedu_seeds.com.restaruents.Cart_screen;
import nsl.nuziveedu_seeds.com.restaruents.Model.Cat_dishitems_model;
import nsl.nuziveedu_seeds.com.restaruents.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {


    Cart_screen cart_screen;


    List<Cat_dishitems_model> sub_cart;

    Cart_screen activity;

    public CartAdapter(Cart_screen cart_screen, List<Cat_dishitems_model> sub_cart, Cart_screen activity) {


        this.cart_screen = cart_screen;
        this.sub_cart = sub_cart;
        this.activity=activity;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(cart_screen).inflate(R.layout.dish, viewGroup, false);


        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int i) {



        viewholder.dishname.setText(String.valueOf(sub_cart.get(i).getDishname()));
        viewholder.dish_price.setText(String.valueOf(sub_cart.get(i).getDishprice()));

        if (Integer.parseInt(sub_cart.get(i).getCount()) > 0) {


            viewholder.last_end.setClickable(false);


            viewholder.in_txt.setText(String.valueOf(sub_cart.get(i).getCount()));

            viewholder.btn_add.setVisibility(View.VISIBLE);


            viewholder.btn_min.setVisibility(View.VISIBLE);
        }


        viewholder.last_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                viewholder.last_end.setClickable(false);


                viewholder.in_txt.setText(String.valueOf(Integer.parseInt(sub_cart.get(i).getCount())+1));

                viewholder.btn_add.setVisibility(View.VISIBLE);


                viewholder.btn_min.setVisibility(View.VISIBLE);

                activity.add(i, viewholder.in_txt,cart_screen,sub_cart);



            }
        });


        viewholder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Integer.parseInt(sub_cart.get(i).getCount())>=20)
                {


                    Toast.makeText(cart_screen,"Maximum 20 Items only",Toast.LENGTH_SHORT).show();

                }
                else
                {


                    viewholder.in_txt.setText(String.valueOf(Integer.parseInt(sub_cart.get(i).getCount())+1));


                    activity.add(i, viewholder.in_txt,cart_screen,sub_cart);


                }


            }
        });


        viewholder.btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ((Integer.parseInt(sub_cart.get(i).getCount())) == 1) {



                    viewholder.last_end.setClickable(true);


                    int a=Integer.parseInt((sub_cart.get(i).getCount()))-1;




                    viewholder.btn_add.setVisibility(View.GONE);


                    viewholder.btn_min.setVisibility(View.GONE);


                    activity.min(i, null,cart_screen,sub_cart);


                    viewholder.in_txt.setText(String.valueOf("ADD"));

                    sub_cart.remove(i);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i, sub_cart.size());







                } else {

                    viewholder.last_end.setClickable(true);

                    viewholder.in_txt.setText(String.valueOf(Integer.parseInt(sub_cart.get(i).getCount())-1));

                    activity.min(i, viewholder.in_txt,cart_screen,sub_cart);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return sub_cart.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {


        TextView dishname, in_txt, btn_add, btn_min, dish_price;

        ConstraintLayout last_end,cart_layout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            dishname = (TextView) itemView.findViewById(R.id.dish_name);

            dish_price = (TextView) itemView.findViewById(R.id.dish_price);


            cart_layout=(ConstraintLayout)itemView.findViewById(R.id.cart_layout);

            btn_add = (TextView) itemView.findViewById(R.id.btn_add);
            in_txt = (TextView) itemView.findViewById(R.id.btn_txt);
            btn_min = (TextView) itemView.findViewById(R.id.btn_min);


            last_end = (ConstraintLayout) itemView.findViewById(R.id.last_end);
        }
    }
    


   public  interface cart
   {


       void min(int i, TextView in_txt, Cart_screen cart_screen, List<Cat_dishitems_model> sub_cart);

       void add(int i, TextView in_txt, Cart_screen cart_screen, List<Cat_dishitems_model> sub_cart);

   }
}

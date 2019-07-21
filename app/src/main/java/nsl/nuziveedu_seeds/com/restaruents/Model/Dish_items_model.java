package nsl.nuziveedu_seeds.com.restaruents.Model;

public class Dish_items_model {


    String dishname,count,id;


    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dish_items_model(String dishname, String count, String id) {
        this.dishname = dishname;
        this.count = count;
        this.id = id;
    }
}

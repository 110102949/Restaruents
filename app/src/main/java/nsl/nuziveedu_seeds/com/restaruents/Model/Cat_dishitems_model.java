package nsl.nuziveedu_seeds.com.restaruents.Model;

import java.io.Serializable;

public class Cat_dishitems_model implements Serializable {


    String category,dishname,dishprice,dishcontent,count,id;

    int type;



    public Cat_dishitems_model(String category, String dishname, String dishprice, String dishcontent, int type, String count,String id) {
        this.category = category;
        this.dishname = dishname;
        this.dishprice = dishprice;
        this.dishcontent = dishcontent;
        this.type = type;
        this.count = count;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDishprice() {
        return dishprice;
    }

    public void setDishprice(String dishprice) {
        this.dishprice = dishprice;
    }

    public String getDishcontent() {
        return dishcontent;
    }

    public void setDishcontent(String dishcontent) {
        this.dishcontent = dishcontent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

package com.example.bagpack;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {
    RoomDB database ;
    String category;
    Context context;
    public static final String LastVersion = "LAST_VERSION";
    public static final int NewVersion =1;
    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData(){
        category = "Basic Need";
        List<Items> basicItem = new ArrayList<>();
        basicItem.add(new Items("Visa",category,false));
        basicItem.add(new Items("Passport",category,false));
        basicItem.add(new Items("Ticket",category,false));
        basicItem.add(new Items("Wallet",category,false));
        basicItem.add(new Items("Currency",category,false));
        return basicItem;
    }

    public List<Items> TechnologyData(){
        String [] data = {"Mobile Phone","Phone charger","Laptop","Power Bank"};
        return ItemList(Constants.TECHNOLOGY_CAMEL_CASE,data);
    }
    public List<Items> CarData(){
        String [] data = {"Car Key","Car Fuel","Car Tiers","Battery"};
        return ItemList(Constants.CAR_SUPPLIES_CAMEL_CASE,data);
    }
    public List<Items> FoodData(){
        String [] data = {"Bun","Fork","Spoon","Bread","Water","Juice"};
        return ItemList(Constants.FOOD_CAMEL_CASE,data);
    }
    public List<Items> BabyData(){
        String [] data = {"Lotion","Pants","Socks","Diaper","Milk","Bottle"};
        return ItemList(Constants.BABY_NEEDS_CAMEL_CASE,data);
    }

    public List<Items> ItemList(String category, String[] data){
        List<String> list = Arrays.asList(data);
        List<Items> dataList  = new ArrayList<>();
        dataList.clear();

        for (int i=0; i< list.size(); i++){
            dataList.add(new Items(list.get(i),category,false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData(){
        List<List<Items>> listOFAllitems = new ArrayList<>();
        listOFAllitems.clear();
        listOFAllitems.add(getBasicData());
        listOFAllitems.add(TechnologyData());
        listOFAllitems.add(BabyData());
        listOFAllitems.add(CarData());
        listOFAllitems.add(FoodData());
        return listOFAllitems;
    }

    public void pushAllData(){
        List<List<Items>> listOfAllItems = getAllData();
        for (List<Items> list : listOfAllItems){
            for (Items items: list){
                database.mainDao().saveItem(items);

            }
        }
        System.out.println("Data Added");
    }
}

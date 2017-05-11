package kr.co.pnu.sqlitepet;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by A on 2017-04-21.
 */

public class User {
    public User() {
        Money = 0;
        item = new ArrayList<Item>();
        food = new ArrayList<Food>();
    };

    public void insert_money(int _Money )
    {
        this.Money = _Money;
    }

    public void insert_item(String name, int money, int count)
    {
        Item temp_item = new Item();
        temp_item.name = name;
        temp_item.price = money;
        temp_item.count = count;

        item.add(temp_item);
    }

    public void insert_food(String name, int money, int count)
    {
        Food temp_food = new Food();
        temp_food.name = name;
        temp_food.price = money;
        temp_food.count = count;

        food.add(temp_food);
    }

    DBHelper helper;
    SQLiteDatabase database;

    public int Money;
    public ArrayList<Item> item;
    public ArrayList<Food> food;

}

package kr.co.pnu.itemlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AllOfItem consist of name, price, Health, Hungry, Happiness, category...
        AllOfItem item1 = new AllOfItem("water", 300, 2, 4, 0, "food");
        AllOfItem item2 = new AllOfItem("banana", 1000, 6, 10, 0, "food");
        AllOfItem item3 = new AllOfItem("apple", 800, 5, 9, 0, "food");
        AllOfItem item4 = new AllOfItem("rice", 1500, 10, 13, 0, "food");
        AllOfItem item5 = new AllOfItem("bread", 2000, 3, 20, 0, "food");
        AllOfItem item6 = new AllOfItem("hamburger", 3000, 0, 30, 0, "food");
        AllOfItem item7 = new AllOfItem("pizza", 3000, 0, 30, 0, "food");

        HashMap<String, AllOfItem> map = new HashMap<String, AllOfItem>();

        map.put(item1.getName(), item1);
        map.put(item2.getName(), item2);

        AllOfItem itemDetail = map.get("blue jean");
        System.out.print(itemDetail.getName());
        System.out.println("Test");
        setContentView(R.layout.activity_main);
    }
}

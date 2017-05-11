package kr.co.pnu.sqlitepet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db, db2;

    Pet pet = new Pet();
    User user = new User();

    boolean AfterInstall = false; // 설치 후 첫 접속인지 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(MainActivity.this, "pet.db", null, 1);
        db = helper.getReadableDatabase();
        helper.onCreate(db);

        Cursor c = db.query("pet", null, null, null, null, null, null, null);


        while(c.moveToNext()) {
            pet.insert_data(c.getInt(c.getColumnIndex("level")),
                    c.getInt(c.getColumnIndex("health")),
                    c.getInt(c.getColumnIndex("hungry")),
                    c.getInt(c.getColumnIndex("happiness")),
                    c.getInt(c.getColumnIndex("exp"))
            );

            if(c.getInt(c.getColumnIndex("level")) >= 1) {
                AfterInstall = true;
            }
        }

        SimpleCursorAdapter adapter = null;
        adapter = new SimpleCursorAdapter(MainActivity.this,
                android.R.layout.simple_list_item_2, c,
                new String[] {"level", "hungry", "health", "happiness"},
                new int[] {android.R.id.text1, android.R.id.text2},0);

        ListView list = (ListView)findViewById(R.id.simple_list_item);
        list.setAdapter(adapter);

        c = db.query("user", null, null, null, null, null, null, null);

        while(c.moveToNext()) {
            switch (c.getString(c.getColumnIndex("class"))) {

            case "money" : user.insert_money(c.getInt(c.getColumnIndex("money")));
                            break;
            case "item" : user.insert_item(c.getString(c.getColumnIndex("name")),
                                        c.getInt(c.getColumnIndex("money")),
                                        c.getInt(c.getColumnIndex("count")));
                            break;
            case "food" : user.insert_food(c.getString(c.getColumnIndex("name")),
                                        c.getInt(c.getColumnIndex("money")),
                                        c.getInt(c.getColumnIndex("count")));
                            break;
            default: break;
            }
        }


        db.close();
    }

    @Override
    protected void onDestroy() {

        helper = new DBHelper(MainActivity.this, "pet.db", null, 1);

        // pet Table 에 수정된(새로운) 값을 넣기 위한 호출....
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        ContentValues values = new ContentValues();

        values.put("level", pet.Level);
        values.put("health", pet.Health);
        values.put("hungry", pet.Hungry);
        values.put("happiness", pet.Happiness);
        values.put("exp", pet.exp);

        // user Table에 수정된(새로운) 값을 넣기 위한 호출....
        db2 = helper.getWritableDatabase();
        helper.onCreate(db2);

        ContentValues values2 = new ContentValues();

        values2.put("class", "money");
        values2.put("name", "money");
        values2.put("money", user.Money);
        values2.put("count", 0);

        if(AfterInstall) { // 첫 실행이 아닐 경우
            db.update("pet", values, null, null);
        }
        else { // 첫 실행일 경우
            db.insert("pet", null, values);
            db2.insert("user",null, values2);
        }
        super.onDestroy();

        db.close();
    }
}

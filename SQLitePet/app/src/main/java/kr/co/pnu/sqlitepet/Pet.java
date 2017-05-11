package kr.co.pnu.sqlitepet;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by A on 2017-04-21.
 */

public class Pet {
    public Pet() {
        Level = 1;
        Health = Hungry = Happiness = 100;
        exp = 0;
    };

    public void insert_data(int _Level, int _Health,
               int _Hungry, int _Happiness
            , int _exp
                            ) {
        this.Level = _Level;
        this.Health = _Health;
        this.Hungry = _Hungry;
        this.Happiness = _Happiness;
        this.exp = _exp;
    }

    DBHelper helper;
    SQLiteDatabase database;

//    public void setUp() {
//        helper = new DBHelper(
//                Pet.this, DBHelper., null, version);
//        database = helper.getWritableDatabase());
//
//    }

    public int Level;
    public int Health;
    public int Hungry;
    public int Happiness;
    public int exp;
}

package kr.co.pnu.sqlitepet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A on 2017-04-21.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_pet = "create table if not exists pet("
                + "_id integer primary key autoincrement, "
                + "level integer DEFAULT 1, "
                + "health integer DEFAULT 100, "
                + "hungry integer DEFAULT 100, "
                + "happiness integer DEFAULT 100,"
                + "exp integer DEFAULT 1"
                +");";

        String sql_user = "create table if not exists user("
                + "__id integer primary key autoincrement, "
                + "class text DEFAULT 'money', "
                + "name text DEFAULT 'money', "
                + "money integer DEFAULT 0, "
                + "count integer DEFAULT 0"
                + ");";

        db.execSQL(sql_pet);
        db.execSQL(sql_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists pet";
        db.execSQL(sql);
        db.execSQL("drop table if exists user");

        onCreate(db);
    }
}

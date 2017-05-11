package com.example.cse.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    // DB�� ���� ������ �� ȣ��Ǵ� �Լ�
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists MONEYBOOK("
                + "_id integer primary key autoincrement, "
                + "item text, "
                + "price integer, "
                + "create_at text);";

        db.execSQL(sql);
        // ���ο� ���̺� ����
        /* �̸��� MONEYBOOK�̰�, �ڵ����� ���� �����ϴ� _id ������ �⺻Ű �÷���
        item ���ڿ� �÷�, price ������ �÷�, create_at ���ڿ� �÷����� ������ ���̺��� ����. */
        /* db.execSQL(~sql�� �Է�~); */
    }

    // DB ���׷��̵带 ���� ������ ����� �� ȣ��Ǵ� �Լ�
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists student";
        db.execSQL(sql);

        onCreate(db);
    }

    public void insert(String create_at, String item, int price) {
        // �а� ���Ⱑ �����ϰ� DB ����
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("item", item);
        values.put("price", price);
        values.put("create_at", create_at);
        db.insert("MONEYBOOK", null, values);
        // DB�� �Է��� ������ �� �߰�
        /* db.execSQL(~sql�� �Է�~); */
        db.close();
    }

    public void update(String item, int price) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put("item", item);
        values.put("price", price);

        db.update("MONEYBOOK", values, "item=?", new String[] {item});

        // �Է��� �׸�� ��ġ�ϴ� ���� ���� ���� ����
        /* db.execSQL(~sql�� �Է�~); */
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        // �Է��� �׸�� ��ġ�ϴ� �� ����
        db.delete("MONEYBOOK", "item=?", new String[]{item});

        /* db.execSQL(~sql�� �Է�~); */
        db.close();
    }

    public String getResult() {
        // �бⰡ �����ϰ� DB ����
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB�� �ִ� �����͸� ���� ó���ϱ� ���� Cursor�� ����Ͽ� ���̺� �ִ� ��� ������ ���
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getInt(2)
                    + "�� "
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }
}
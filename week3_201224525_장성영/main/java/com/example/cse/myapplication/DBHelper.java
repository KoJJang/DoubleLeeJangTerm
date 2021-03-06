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

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists MONEYBOOK("
                + "_id integer primary key autoincrement, "
                + "item text, "
                + "price integer, "
                + "create_at text);";

        db.execSQL(sql);
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        /* db.execSQL(~sql문 입력~); */
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists student";
        db.execSQL(sql);

        onCreate(db);
    }

    public void insert(String create_at, String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("item", item);
        values.put("price", price);
        values.put("create_at", create_at);
        db.insert("MONEYBOOK", null, values);
        // DB에 입력한 값으로 행 추가
        /* db.execSQL(~sql문 입력~); */
        db.close();
    }

    public void update(String item, int price) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put("item", item);
        values.put("price", price);

        db.update("MONEYBOOK", values, "item=?", new String[] {item});

        // 입력한 항목과 일치하는 행의 가격 정보 수정
        /* db.execSQL(~sql문 입력~); */
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.delete("MONEYBOOK", "item=?", new String[]{item});

        /* db.execSQL(~sql문 입력~); */
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getInt(2)
                    + "원 "
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }
}
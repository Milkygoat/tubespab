package com.example.tugasbesar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper115 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME115 = "ExpenseTracker115.db";
    private static final int DATABASE_VERSION115 = 1;

    // User Table
    private static final String TABLE_USER115 = "users115";
    private static final String COL_USER_ID115 = "_id"; // Using _id for CursorAdapter compatibility
    private static final String COL_USERNAME115 = "username115";
    private static final String COL_PASSWORD115 = "password115";

    // Transaction Table
    private static final String TABLE_TRANS115 = "transactions115";
    private static final String COL_TRANS_ID115 = "_id"; // Using _id for CursorAdapter compatibility
    private static final String COL_TRANS_USER_ID115 = "user_id115";
    private static final String COL_KATEGORI115 = "kategori115";
    private static final String COL_NOMINAL115 = "nominal115";
    private static final String COL_KETERANGAN115 = "keterangan115";
    private static final String COL_TANGGAL115 = "tanggal115";

    public DatabaseHelper115(Context context115) {
        super(context115, DATABASE_NAME115, null, DATABASE_VERSION115);
    }

    @Override
    public void onCreate(SQLiteDatabase db115) {
        String createTableUser115 = "CREATE TABLE " + TABLE_USER115 + " (" +
                COL_USER_ID115 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME115 + " TEXT, " +
                COL_PASSWORD115 + " TEXT)";
        db115.execSQL(createTableUser115);

        String createTableTrans115 = "CREATE TABLE " + TABLE_TRANS115 + " (" +
                COL_TRANS_ID115 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TRANS_USER_ID115 + " INTEGER, " +
                COL_KATEGORI115 + " TEXT, " +
                COL_NOMINAL115 + " REAL, " +
                COL_KETERANGAN115 + " TEXT, " +
                COL_TANGGAL115 + " TEXT)";
        db115.execSQL(createTableTrans115);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db115, int oldVersion115, int newVersion115) {
        db115.execSQL("DROP TABLE IF EXISTS " + TABLE_USER115);
        db115.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS115);
        onCreate(db115);
    }

    // User operations
    public long registerUser115(String username115, String password115) {
        SQLiteDatabase db115 = this.getWritableDatabase();
        ContentValues values115 = new ContentValues();
        values115.put(COL_USERNAME115, username115);
        values115.put(COL_PASSWORD115, password115);
        return db115.insert(TABLE_USER115, null, values115);
    }

    public int checkUser115(String username115, String password115) {
        SQLiteDatabase db115 = this.getReadableDatabase();
        Cursor cursor115 = db115.query(TABLE_USER115, new String[]{COL_USER_ID115},
                COL_USERNAME115 + "=? AND " + COL_PASSWORD115 + "=?",
                new String[]{username115, password115}, null, null, null);
        int userId115 = -1;
        if (cursor115 != null && cursor115.moveToFirst()) {
            userId115 = cursor115.getInt(0);
            cursor115.close();
        }
        return userId115;
    }

    // Transaction operations
    public long addTransaction115(int userId115, String kategori115, double nominal115, String keterangan115, String tanggal115) {
        SQLiteDatabase db115 = this.getWritableDatabase();
        ContentValues values115 = new ContentValues();
        values115.put(COL_TRANS_USER_ID115, userId115);
        values115.put(COL_KATEGORI115, kategori115);
        values115.put(COL_NOMINAL115, nominal115);
        values115.put(COL_KETERANGAN115, keterangan115);
        values115.put(COL_TANGGAL115, tanggal115);
        return db115.insert(TABLE_TRANS115, null, values115);
    }

    public Cursor getAllTransactions115(int userId115) {
        SQLiteDatabase db115 = this.getReadableDatabase();
        return db115.rawQuery("SELECT * FROM " + TABLE_TRANS115 + " WHERE " + COL_TRANS_USER_ID115 + "=? ORDER BY " + COL_TRANS_ID115 + " DESC", new String[]{String.valueOf(userId115)});
    }

    public Cursor getTransactionsByCategory115(int userId115, String kategori115) {
        SQLiteDatabase db115 = this.getReadableDatabase();
        return db115.rawQuery("SELECT * FROM " + TABLE_TRANS115 + " WHERE " + COL_TRANS_USER_ID115 + "=? AND " + COL_KATEGORI115 + "=?", new String[]{String.valueOf(userId115), kategori115});
    }

    public int updateTransaction115(int id115, String kategori115, double nominal115, String keterangan115, String tanggal115) {
        SQLiteDatabase db115 = this.getWritableDatabase();
        ContentValues values115 = new ContentValues();
        values115.put(COL_KATEGORI115, kategori115);
        values115.put(COL_NOMINAL115, nominal115);
        values115.put(COL_KETERANGAN115, keterangan115);
        values115.put(COL_TANGGAL115, tanggal115);
        return db115.update(TABLE_TRANS115, values115, COL_TRANS_ID115 + "=?", new String[]{String.valueOf(id115)});
    }

    public int deleteTransaction115(int id115) {
        SQLiteDatabase db115 = this.getWritableDatabase();
        return db115.delete(TABLE_TRANS115, COL_TRANS_ID115 + "=?", new String[]{String.valueOf(id115)});
    }
}

package com.example.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {
    public Banco(@Nullable Context context) {
        super(context, "tb_users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tb_users(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR, telefone INTEGER(11),UNIQUE(email))");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();


        try{
            Cursor cursor = db.query("tb_users",new String[]{"id","email","nome","telefone"},
                    "id"+ " = ?",
                    new String[]{"id"},null,null,null,null);
            if(cursor !=null){
                cursor.moveToFirst();
            }

            ContentValues values = new ContentValues();
            values.put("nome", user.getNome());
            values.put("email", user.getEmail());
            values.put("telefone", user.getTelefone());

            db.insert("tb_users",null,values);

        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();




    }
    public User selecionarUser(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_users",new String[]{"id","nome","email","telefone"},
                "id"+ " = ?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        return new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getInt(3));

    }
    public void setUserString(String var, String val, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE tb_users SET "+ var+" = '"+ val+"' WHERE ID = "+id;
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToNext();

    }
    public void setUserInt(String var, int val, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE tb_users SET " + var + " = '" + val + "' WHERE ID = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
    }
    public int getDbSize(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id FROM tb_users ORDER BY id DESC LIMIT 1";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToNext();

        return cursor.getInt(0);


    }
}

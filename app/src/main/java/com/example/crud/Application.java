package com.example.crud;

public class Application extends android.app.Application {
    public Banco db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new Banco(this);
    }
    public Banco getDb() {
        return db;
    }

}

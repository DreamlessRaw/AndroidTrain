package com.example.myapplication.utils;

public class SQLiteUtil {

    public CustomSqliteHelper Instance = initDb();

    public CustomSqliteHelper initDb() {
        if (Instance == null) {
            Instance = new CustomSqliteHelper(CustomApplication.getContext(), "zcitsc.db", null, 1);
        }
        return Instance;
    }
}

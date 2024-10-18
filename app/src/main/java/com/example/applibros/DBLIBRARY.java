package com.example.applibros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBLIBRARY extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DBLIBRARY.db";
    private static final int DATABASE_VERSION = 1;

    public DBLIBRARY( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        db.execSQL("CREATE TABLE user (" +
                "iduser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "passwrd TEXT NOT NULL," +
                "status TEXT NOT NULL)");

        // Crear tabla de libros
        db.execSQL("CREATE TABLE book (" +
                "idbook INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "coste REAL NOT NULL," +
                "available INTEGER NOT NULL)");

        // Crear tabla de rentas
        db.execSQL("CREATE TABLE rent (" +
                "idrent INTEGER PRIMARY KEY AUTOINCREMENT," +
                "iduser INTEGER NOT NULL," +
                "idbook INTEGER NOT NULL," +
                "date TEXT NOT NULL," +
                "FOREIGN KEY(iduser) REFERENCES user(iduser)," +
                "FOREIGN KEY(idbook) REFERENCES book(idbook))");

        // Crear tabla de devoluciones
        db.execSQL("CREATE TABLE devolucion (" +
                "iddev INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idrent INTEGER NOT NULL," +
                "date TEXT NOT NULL," +
                "FOREIGN KEY(idrent) REFERENCES rent(idrent))");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la base de datos ya existe, eliminar las tablas y recrear
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS book");
        db.execSQL("DROP TABLE IF EXISTS rent");
        db.execSQL("DROP TABLE IF EXISTS devolucion");
        onCreate(db);
    }
}

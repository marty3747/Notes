package com.demo.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotesDBHelper extends SQLiteOpenHelper { // класс, который помогает в работе с базами данных

    private static final String DB_NAME = "notes.db"; // сохранение имени базы данных в переменную
    private static final int DB_VERSION = 1; // версия базы данных

    public NotesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NotesContract.NotesEntry.CREATE_COMMAND); // создание таблицы базы данных, в качестве параметра передается запрос на создание таблицы
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NotesContract.NotesEntry.DROP_COMMAND); // в случае обновления базы данных она пересоздается через это удаление
        onCreate(db); // а здесь создается занаво
    }
}

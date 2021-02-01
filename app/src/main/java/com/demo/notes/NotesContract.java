package com.demo.notes;

import android.provider.BaseColumns;

public class NotesContract { // класс хранит в себе название таблицы, заголовки столбцов и прочее, класс служит для хранения данных о базе данных
    public static final class NotesEntry implements BaseColumns { // столько вложенных классов, сколько таблиц в базе данных, наследование потому, что это таблица базы данных(показываем так). BaseColumns автоматически создает ID
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title"; // первый столбец таблицы
        public static final String COLUMN_DESCRIPTION = "description"; // второй столбец таблицы и т.д ...
        public static final String COLUMN_DAY_OF_WEEK = "day_of_week";
        public static final String COLUMN_PRIORITY = "priority";

        public static final String TYPE_TEXT = "TEXT"; // типы данных для таблиц
        public static final String TYPE_INTEGER = "INTEGER";

        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE +
                " " + TYPE_TEXT + ", " + COLUMN_DESCRIPTION + " " + TYPE_TEXT + ", " + COLUMN_DAY_OF_WEEK +
                " " + TYPE_TEXT + ", " + COLUMN_PRIORITY + " " + TYPE_INTEGER + ")"; // команда, которая создает таблицу

        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}

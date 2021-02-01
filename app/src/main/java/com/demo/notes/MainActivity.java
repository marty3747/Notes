package com.demo.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;
    private NotesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNotes = findViewById(R.id.recycleViewNotes);
        dbHelper = new NotesDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase(); // получение базы данных для записи в неё
        if(notes.isEmpty()) {
            notes.add(new Note("Данные о спортзале", "Собрать детальные данные о том сколько человек ходит в сопртзал", "Понедельник", 2));
            notes.add(new Note("Данные о магазине", "Собрать данные о том сколько человек ходит в спортзал", "Понедельник", 1));
            notes.add(new Note("Данные о танцевальной секции", "Собрать данные о количестве человек, записанных на занятия по танцам", "Вторник", 3));
            notes.add(new Note("Данные о компьютерном клуб", "Собрать данные о том, сколько человек посетило клуб", "Четверг", 3));
            notes.add(new Note("Данные о преподавании", "Собрать данные о количестве преподавателей в школах", "Воскресенье", 3));
            notes.add(new Note("Данные о производстве", "Собрать данные о производстве инженерных станков", "Пятница", 3));
            notes.add(new Note("Данные о игровых автоматах", "Собрать данные о том, сколько человек подпольно играют в казино", "Вторник", 3));
            notes.add(new Note("Эйнштейн", "Прочитать автобиографию", "Среда", 3));
            notes.add(new Note("Посмотреть", "Человек-паук", "Суббота", 3));
        }
        for(Note note: notes) {
            ContentValues contentValues = new ContentValues();// для вставки новой записи используется обьект класса contentValues
            contentValues.put(NotesContract.NotesEntry.COLUMN_TITLE, note.getTitle()); // добавление элементов парами ключ-значение
            contentValues.put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, note.getDescription());
            contentValues.put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, note.getDayOfWeek());
            contentValues.put(NotesContract.NotesEntry.COLUMN_PRIORITY, note.getPriority());
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues); // вставка данных в базу данных
        }
        ArrayList<Note> notesFromDB = new ArrayList<>();

        //получение данных из базы данных при помощи курсора
        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            String dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));
            Note note = new Note(title, description, dayOfWeek, priority);
            notesFromDB.add(note);
        }
        cursor.close();
        adapter = new NotesAdapter(notesFromDB);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
    }

    private void remove(int position) {
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void addClickNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}

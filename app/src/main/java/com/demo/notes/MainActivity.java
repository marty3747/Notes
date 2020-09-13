package com.demo.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleViewNotes);
        notes.add(new Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
        notes.add(new Note("Магазин", "Сделать прическу", "Понедельник", 1));
        notes.add(new Note("Секс-шоп", "Сделать прическу", "Понедельник", 3));
        notes.add(new Note("Аляска", "Сделать прическу", "Понедельник", 4));
        notes.add(new Note("Марцепан", "Сделать прическу", "Понедельник", 5));
        notes.add(new Note("Карандаш", "Сделать прическу", "Понедельник", 6));
        notes.add(new Note("Диплом", "Сделать прическу", "Понедельник", 7));
        notes.add(new Note("Мольберт", "Сделать прическу", "Понедельник", 8));
        notes.add(new Note("Эйнштейн", "Сделать прическу", "Понедельник", 9));
        notes.add(new Note("Граццио", "Сделать прическу", "Понедельник", 10)); 
        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}

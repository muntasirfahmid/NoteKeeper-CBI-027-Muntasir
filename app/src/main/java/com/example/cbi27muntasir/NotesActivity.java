package com.example.cbi27muntasir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Note> notesList;
    private DatabaseHelper databaseHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        listView = findViewById(R.id.notesListView);
        Button addNoteButton = findViewById(R.id.addNoteButton); // Add Note button
        databaseHelper = new DatabaseHelper(this);
        notesList = databaseHelper.getAllNotes();

        // Convert notes to a string list for the ListView
        ArrayList<String> notesTitles = new ArrayList<>();
        for (Note note : notesList) {
            notesTitles.add(note.getTitle());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesTitles);
        listView.setAdapter(adapter);

        // On item click, open NoteDetailsActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = notesList.get(position);
                Intent intent = new Intent(NotesActivity.this, NoteDetailsActivity.class);
                intent.putExtra("noteId", selectedNote.getId());
                startActivity(intent);
            }
        });

        // Add Note button click listener
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshNotesList();
    }

    private void refreshNotesList() {
        notesList.clear();
        notesList.addAll(databaseHelper.getAllNotes());
        adapter.clear();
        for (Note note : notesList) {
            adapter.add(note.getTitle());
        }
        adapter.notifyDataSetChanged();
    }
}
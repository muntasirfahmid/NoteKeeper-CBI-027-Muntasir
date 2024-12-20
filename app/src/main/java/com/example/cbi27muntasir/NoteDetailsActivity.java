package com.example.cbi27muntasir;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailsActivity extends AppCompatActivity {

    private TextView noteTitleTextView;
    private TextView noteContentTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        noteTitleTextView = findViewById(R.id.noteTitleTextView);
        noteContentTextView = findViewById(R.id.noteContentTextView);
        databaseHelper = new DatabaseHelper(this);

        int noteId = getIntent().getIntExtra("noteId", -1);
        if (noteId != -1) {
            Note note = databaseHelper.getNoteById(noteId);
            if (note != null) {
                noteTitleTextView.setText(note.getTitle());
                noteContentTextView.setText(note.getContent());
            }
        }
    }
}
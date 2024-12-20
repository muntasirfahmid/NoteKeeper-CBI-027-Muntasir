package com.example.cbi27muntasir;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText noteTitleEditText;
    private EditText noteContentEditText;
    private Button saveNoteButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        saveNoteButton = findViewById(R.id.saveNoteButton);
        databaseHelper = new DatabaseHelper(this);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noteTitleEditText.getText().toString().trim();
                String content = noteContentEditText.getText().toString().trim();

                // Validate that the input fields are not empty
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(AddNoteActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(AddNoteActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add the note to the database
                databaseHelper.addNote(new Note(title, content));

                // Show a success message
                Toast.makeText(AddNoteActivity.this, "Note added successfully!", Toast.LENGTH_SHORT).show();

                // Redirect back to NotesActivity
                Intent intent = new Intent(AddNoteActivity.this, NotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                // Finish this activity
                finish();
            }
        });
    }
}
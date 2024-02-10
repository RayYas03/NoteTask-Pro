package com.example.notetaskpro;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNote extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);



        Button doneButton = view.findViewById(R.id.btnDone);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        AppCompatButton shareButton = view.findViewById(R.id.share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

        return view;
    }

//    public void addNote() {
//        EditText dateEditText = getView().findViewById(R.id.Date);
//        EditText titleEditText = getView().findViewById(R.id.NoteTitle);
//        EditText notesEditText = getView().findViewById(R.id.Notes);
//
//        String date = dateEditText.getText().toString();
//        String title = titleEditText.getText().toString();
//        String description = notesEditText.getText().toString();
//
//        if (!date.isEmpty() && !title.isEmpty() && !description.isEmpty()) {
//            // Create a ContentValues object to store the data
//            ContentValues values = new ContentValues();
//            values.put(DBHelper.COLUMN_DATE, date);
//            values.put(DBHelper.COLUMN_TITLE, title);
//            values.put(DBHelper.COLUMN_DESC, description);
//
//            DBHelper dbHelper = new DBHelper(requireContext());
//            // Get a writable database
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//            // Insert the data into the database
//            long newRowId = db.insert(DBHelper.NOTES_TABLE, null, values);
//
//            // Check if the data was inserted successfully
//            if (newRowId != -1) {
//                // Data inserted successfully
//                Log.d("AddNoteFragment", "Note added successfully");
//            } else {
//                // Failed to insert data
//                Log.e("AddNoteFragment", "Error inserting note");
//            }
//
//            // Close the database
//            db.close();
//        } else {
//            // Handle empty fields
//            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void addNote() {
        EditText dateEditText = getView().findViewById(R.id.Date);
        EditText titleEditText = getView().findViewById(R.id.NoteTitle);
        EditText notesEditText = getView().findViewById(R.id.Notes);

        String date = dateEditText.getText().toString();
        String title = titleEditText.getText().toString();
        String description = notesEditText.getText().toString();

        if (!date.isEmpty() && !title.isEmpty() && !description.isEmpty()) {
            // Create a ContentValues object to store the data
            ContentValues values = new ContentValues();
            values.put(DBHelper.COLUMN_DATE, date);
            values.put(DBHelper.COLUMN_TITLE, title);
            values.put(DBHelper.COLUMN_DESC, description);

            DBHelper dbHelper = new DBHelper(requireContext());
            // Get a writable database
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Insert the data into the database
            long newRowId = db.insert(DBHelper.NOTES_TABLE, null, values);

            // Check if the data was inserted successfully
            if (newRowId != -1) {
                // Data inserted successfully
                Log.d("AddNoteFragment", "Note added successfully");

                // Refresh the note list in the NoteFragment
                NoteFragment noteFragment = (NoteFragment) getParentFragmentManager().findFragmentByTag("NoteFragment");
                if (noteFragment != null) {
                    noteFragment.updateNoteList();
                }
            } else {
                // Failed to insert data
                Log.e("AddNoteFragment", "Error inserting note");
            }

            // Close the database
            //db.close();
        } else {
            // Handle empty fields
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    public void share() {
        EditText titleEditText = getView().findViewById(R.id.NoteTitle);
        EditText notesEditText = getView().findViewById(R.id.Notes);

        String title = titleEditText.getText().toString();
        String notes = notesEditText.getText().toString();

        // Check if both title and notes are not empty
        if (!title.isEmpty() && !notes.isEmpty()) {
            // Create the text to be shared
            String textToShare = "Title: " + title + "\nNotes: " + notes;

            // Create an intent to share the text
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
            sendIntent.setType("text/plain");

            // Start the activity for sharing
            startActivity(sendIntent);
        } else {
            // Handle empty fields
            Toast.makeText(getContext(), "Please fill in title and notes to share", Toast.LENGTH_SHORT).show();
        }
    }

}
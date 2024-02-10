//package com.example.notetaskpro;
//
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//
//public class DeleteNote extends Fragment {
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.noteentryy, container, false);
//
//        // ... (other initialization code)
//
//        Button deleteButton = view.findViewById(R.id.deleteButton);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView titleTextView = view.findViewById(R.id.texttitle);
//                String noteTitleToDelete = titleTextView.getText().toString().trim();
//
//                if (!noteTitleToDelete.isEmpty()) {
//                    // Call the deleteNoteByTitle method to delete the note
//                    deleteNote(noteTitleToDelete);
//                } else {
//                    // Handle the case where noteTitle is empty
//                    Log.e("DeleteNoteFragment", "Note title is empty");
//                }
//            }
//        });
//
//
//        // ... (other code)
//
//        return view;
//    }
//
////    public void addNote() {
////        EditText dateEditText = getView().findViewById(R.id.Date);
////        EditText titleEditText = getView().findViewById(R.id.NoteTitle);
////        EditText notesEditText = getView().findViewById(R.id.Notes);
////
////        String date = dateEditText.getText().toString();
////        String title = titleEditText.getText().toString();
////        String description = notesEditText.getText().toString();
////
////        if (!date.isEmpty() && !title.isEmpty() && !description.isEmpty()) {
////            // Create a ContentValues object to store the data
////            ContentValues values = new ContentValues();
////            values.put(DBHelper.COLUMN_DATE, date);
////            values.put(DBHelper.COLUMN_TITLE, title);
////            values.put(DBHelper.COLUMN_DESC, description);
////
////            DBHelper dbHelper = new DBHelper(requireContext());
////            // Get a writable database
////            SQLiteDatabase db = dbHelper.getWritableDatabase();
////
////            // Insert the data into the database
////            long newRowId = db.insert(DBHelper.NOTES_TABLE, null, values);
////
////            // Check if the data was inserted successfully
////            if (newRowId != -1) {
////                // Data inserted successfully
////                Log.d("AddNoteFragment", "Note added successfully");
////            } else {
////                // Failed to insert data
////                Log.e("AddNoteFragment", "Error inserting note");
////            }
////
////            // Close the database
////            db.close();
////        } else {
////            // Handle empty fields
////            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
////        }
////    }
//
//
//    // ... other code ...
//
//    public void deleteNote(String noteTitle) {
//        // Initialize DBHelper
//        DBHelper dbHelper = new DBHelper(requireContext());
//
//        // Get a writable database
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        try {
//            // Define the selection criteria (in this case, based on the note's title)
//            String selection = DBHelper.COLUMN_TITLE + "=?";
//            String[] selectionArgs = {String.valueOf(noteTitle)};
//
//            // Delete the note from the database
//            int rowsDeleted = db.delete(DBHelper.NOTES_TABLE, selection, selectionArgs);
//
//            // Check if the deletion was successful
//            if (rowsDeleted > 0) {
//                // Note deleted successfully
//                Log.d("DeleteNoteFragment", "Note deleted successfully");
//
//                // Refresh the note list in the NoteFragment
//                NoteFragment noteFragment = (NoteFragment) getParentFragmentManager().findFragmentByTag("NoteFragment");
//                if (noteFragment != null) {
//                    noteFragment.updateNoteList();
//                }
//            } else {
//                // Failed to delete the note or note not found
//                Log.e("DeleteNoteFragment", "Error deleting note or note not found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Close the database if it is open
//            if (db != null && db.isOpen()) {
//                db.close();
//                Log.d("DeleteNoteFragment", "Database closed");
//            }
//        }
//    }
//
//}
//
//
//
//

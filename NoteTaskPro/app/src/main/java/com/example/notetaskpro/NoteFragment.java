package com.example.notetaskpro;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//public class NoteFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private RecyclerView recyclerView;
//    private MyAdapter myAdapter;
//    private List<Note> noteList;
//
//    public NoteFragment() {
//        // Required empty public constructor
//    }
//
//
//    // TODO: Rename and change types and number of parameters
////    public static NotesFragment newInstance(String param1, String param2) {
////        NotesFragment fragment = new NotesFragment();
////        Bundle args = new Bundle();
////        args.putString(ARG_PARAM1, param1);
////        args.putString(ARG_PARAM2, param2);
////        fragment.setArguments(args);
////        return fragment;
////    }
////
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////            mParam1 = getArguments().getString(ARG_PARAM1);
////            mParam2 = getArguments().getString(ARG_PARAM2);
////        }
////    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_note2, container, false);
//        recyclerView = view.findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        noteList = generateNoteList();
//        myAdapter = new MyAdapter(noteList);
//        myAdapter.notifyDataSetChanged();
//        return view;
//
//    }
//
//    private List<Note> generateNoteList(){
//        DBHelper dbHelper = new DBHelper(requireContext());
//        return dbHelper.getAllItems();
//    }
//}

public class NoteFragment extends Fragment implements MyAdapter.buttonClickListener{

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Note> noteList;

    // ... (other methods and imports)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note2, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        noteList = generateNoteList();
        myAdapter = new MyAdapter(noteList, this);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    private List<Note> generateNoteList() {
        DBHelper dbHelper = new DBHelper(requireContext());
        return dbHelper.getAllItems();
    }

    // Add this method to update the data when a new note is added
    public void updateNoteList() {
        noteList.clear(); // Clear the existing list
        noteList.addAll(generateNoteList()); // Populate the list with updated data
        myAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

//    @Override
//    public void onButtonClick(int position) {
//        deleteNote();
//    }
//
//    public void deleteNote(View view) {
//        // Assuming you have stored the note ID in the tag of the delete button
//        Object tag = view.getTag();
//
//        if (tag != null && tag instanceof Long) {
//            long noteId = (Long) tag;
//
//            DBHelper dbHelper = new DBHelper(requireContext());
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//            // Define the WHERE clause based on the note ID
//            String selection = DBHelper.COLUMN_ID + " = ?";
//            String[] selectionArgs = {String.valueOf(noteId)};
//
//            // Delete the note from the database
//            int deletedRows = db.delete(DBHelper.NOTES_TABLE, selection, selectionArgs);
//
//            if (deletedRows > 0) {
//                // Note deleted successfully
//                Log.d("DeleteNote", "Note deleted successfully");
//
//                // Refresh the note list in the NoteFragment
//                NoteFragment noteFragment = (NoteFragment) getParentFragmentManager().findFragmentByTag("NoteFragment");
//                if (noteFragment != null) {
//                    noteFragment.updateNoteList();
//                }
//            } else {
//                // Failed to delete note
//                Log.e("DeleteNote", "Error deleting note");
//            }
//
//            //db.close();
//        }
//    }

//    public void deleteNote(View view) {
//        // Assuming you have stored the note title in the tag of the delete button
//        Object tag = view.getTag();
//
//        if (tag != null && tag instanceof String) {
//            String noteTitle = (String) tag;
//
//            // Show the confirmation dialog
//            ConfirmationDialogFragment confirmationDialog = ConfirmationDialogFragment.newInstance(noteTitle);
//            confirmationDialog.show(getChildFragmentManager(), "confirmationDialog");
//        }
//    }


    @Override
    public void onButtonClick(int position) {
        deleteNote(position);
    }

    public void deleteNote(int position) {
        // Get the corresponding Note object using the position
        Note noteToDelete = noteList.get(position);

        // Delete the note from the database
        DBHelper dbHelper = new DBHelper(requireContext());
        dbHelper.deleteNote(noteToDelete.getId());

        // Update the note list in the adapter and refresh the UI
        updateNoteList();
    }



}




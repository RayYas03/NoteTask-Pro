//package com.example.notetaskpro;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//import androidx.fragment.app.DialogFragment;
//
//public class ConfirmationDialogFragment extends DialogFragment {
//
//    private static final String ARG_NOTE_TITLE = "noteTitle";
//
//    public static ConfirmationDialogFragment newInstance(String noteTitle) {
//        ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_NOTE_TITLE, noteTitle);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        String noteTitle = getArguments().getString(ARG_NOTE_TITLE);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Delete Note")
//                .setMessage("Are you sure you want to delete the note with title: " + noteTitle + "?")
//                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User confirmed the deletion, perform the delete operation
//                        // Call the deleteNote method or any other appropriate action
//                        // Example: ((DeleteNote) getParentFragment()).deleteNote();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User canceled the operation
//                    }
//                });
//        return builder.create();
//    }
//}

package com.example.notetaskpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.CaptureRequest;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<Note> notes;

    buttonClickListener buttonClickListener;


    public MyAdapter(List<Note> notes, buttonClickListener buttonClickListener) {

        this.notes = notes;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noteentryy,parent,false);
        return new MyViewHolder(v, buttonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Note curNote = notes.get(position);
        holder.dateView.setText(curNote.getDate());
        holder.titleView.setText(curNote.getTitle());
        holder.descView.setText(curNote.getDesc());

        holder.deleteButton.setTag(position);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView dateView, titleView, descView;
        ImageButton deleteButton;

        buttonClickListener buttonClickListener;
        public MyViewHolder(@NonNull View noteView, buttonClickListener buttonClickListener) {
            super(noteView);
            dateView = noteView.findViewById(R.id.textdate);
            titleView = noteView.findViewById(R.id.texttitle);
            descView = noteView.findViewById(R.id.textdesc);
            deleteButton = noteView.findViewById(R.id.deleteButton);

            this.buttonClickListener = buttonClickListener;
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            buttonClickListener.onButtonClick(getAdapterPosition());
        }
    }

    public interface buttonClickListener{
        void onButtonClick(int position);
    }


}

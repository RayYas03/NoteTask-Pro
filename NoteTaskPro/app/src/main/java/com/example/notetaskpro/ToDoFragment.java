package com.example.notetaskpro;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ToDoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        CheckBox checkBox1 = view.findViewById(R.id.Task1);
        CheckBox checkBox2 = view.findViewById(R.id.Task2);
        CheckBox checkBox3 = view.findViewById(R.id.Task3);

        setCheckBoxListener(checkBox1);
        setCheckBoxListener(checkBox2);
        setCheckBoxListener(checkBox3);

        return view;
    }

    private void setCheckBoxListener(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextDecoration(buttonView, isChecked);
            }
        });
    }

    private void updateTextDecoration(CompoundButton buttonView, boolean isChecked) {
        String text = buttonView.getText().toString();
        SpannableString spannableString = new SpannableString(text);

        if (isChecked) {
            // Apply strikethrough style
            spannableString.setSpan(new StrikethroughClickableSpan(), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        buttonView.setText(spannableString);
    }

    private static class StrikethroughClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            // Handle click if needed
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setFlags(ds.getFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}

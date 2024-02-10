package com.example.notetaskpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Switch settingSwitch = view.findViewById(R.id.settingSwitch1);

        settingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The switch is turned on, show a toast message
                    showNotificationTurnedOnToast();
                } else {
                    // The switch is turned off (optional: show another message or perform additional actions)
                }
            }
        });

        return view;
    }

    private void showNotificationTurnedOnToast() {
        Toast.makeText(requireContext(), "Notifications turned on!", Toast.LENGTH_SHORT).show();
    }
}

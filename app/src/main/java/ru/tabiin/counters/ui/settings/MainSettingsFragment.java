package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentMainSettingsBinding;


public class MainSettingsFragment extends Fragment {
    FragmentMainSettingsBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentMainSettingsBinding.inflate(getLayoutInflater());

        b.themeAppSettings.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ThemeSettingsFragment())
                    .commit();
        });

        b.animationFragmentsSettings.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new TransitionsSettingsFragment())
                    .commit();
        });

        b.buttonsSettings.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new ButtonsSettingsFragment())
                    .commit();
        });

        return b.getRoot();
    }
}
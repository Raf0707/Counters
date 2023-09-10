package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentThemeSettingsBinding;
import ru.tabiin.counters.util.SharedPreferencesUtils;

public class ThemeSettingsFragment extends Fragment {
    FragmentThemeSettingsBinding b;
    private SwitchMaterial switchMaterial;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentThemeSettingsBinding.inflate(getLayoutInflater());
        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchMaterial = b.dynamicColorsSwitch;
        b.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        b.appThemeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.setFollowSystemTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 1);
                    requireActivity().recreate();
                    break;
                case R.id.setLightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setLightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 2);
                    requireActivity().recreate();
                    break;
                case R.id.setNightTheme:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkedButton", R.id.setNightTheme);
                    SharedPreferencesUtils.saveInteger(requireContext(), "nightMode", 3);
                    requireActivity().recreate();
                    break;
            }
        });

        switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DynamicColors.applyToActivitiesIfAvailable(requireActivity().getApplication());
            DynamicColors.applyToActivitiesIfAvailable(requireActivity().getApplication(),
                    R.style.Theme_Counters);
            SharedPreferencesUtils.saveBoolean(requireContext(), "useDynamicColors", isChecked);
            requireActivity().recreate();
        });

    }
}
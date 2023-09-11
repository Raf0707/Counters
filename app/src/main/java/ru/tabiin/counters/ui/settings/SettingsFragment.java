package ru.tabiin.counters.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

//import com.google.android.material.color.DynamicColors;
//import com.google.android.material.switchmaterial.SwitchMaterial;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentSettingsBinding;
import ru.tabiin.counters.util.SharedPreferencesUtils;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    //private SwitchMaterial switchMaterial;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container,false);
        //switchMaterial = (SwitchMaterial) binding.dynamicColorsSwitch;
        //binding.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        //binding.dynamicColorsSwitch.setEnabled(DynamicColors.isDynamicColorAvailable());
        //switchMaterial.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "useDynamicColors"));
        //int[] setNightModeDescription = {R.string.auto_theme_description, R.string.system_theme_description, R.string.light_theme_description, R.string.night_theme_description};
        //binding.themeDescription.setText(setNightModeDescription[SharedPreferencesUtils.getInteger(requireContext(), "nightMode", 1)]);
        binding.animationVariantsGroup.setVisibility(View.GONE);
        binding.clickVibratorRadioGroup.setVisibility(View.GONE);
        binding.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
        binding.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
        binding.clickVibratorEndSwitch.setVisibility(View.GONE);
        /*binding.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
        binding.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
        binding.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);*/
        //assert binding.textViewClick != null;
        binding.textViewClickLayout.setVisibility(View.GONE);
        binding.plusValLayout.setVisibility(View.GONE);
        binding.minusValLayout.setVisibility(View.GONE);
        binding.resetValLayout.setVisibility(View.GONE);
        binding.saveClickObserver.setVisibility(View.GONE);

        binding.longPressRadioGroup.setVisibility(View.GONE);

        binding.textViewLongClickLayout.setVisibility(View.GONE);
        binding.plusValLongLayout.setVisibility(View.GONE);
        binding.minusValLongLayout.setVisibility(View.GONE);
        binding.resetValLongLayout.setVisibility(View.GONE);
        binding.saveLongClickObserver.setVisibility(View.GONE);

        binding.clickSwipeGroup.setVisibility(View.GONE);
        binding.longClickSwipeGroup.setVisibility(View.GONE);
        binding.doubleClickSwipeGroup.setVisibility(View.GONE);
        binding.swipeTopGroup.setVisibility(View.GONE);
        binding.swipeDownGroup.setVisibility(View.GONE);
        binding.swipeRightGroup.setVisibility(View.GONE);
        binding.swipeLeftGroup.setVisibility(View.GONE);

        binding.valueClickInput.setVisibility(View.GONE);
        binding.valueLongClickInput.setVisibility(View.GONE);
        binding.valueDoubleClickInput.setVisibility(View.GONE);
        binding.valueSwipeTopInput.setVisibility(View.GONE);
        binding.valueSwipeDownInput.setVisibility(View.GONE);
        binding.valueSwipeRightInput.setVisibility(View.GONE);
        binding.valueSwipeLeftInput.setVisibility(View.GONE);

        binding.swipeCheckVibrateLayout.setVisibility(View.VISIBLE);
        // сделать всю вибрацию от 0 до 1000

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //binding.appThemeRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "checkedButton", R.id.setFollowSystemTheme));
        /*binding.appThemeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
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
        });*/


        /*switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DynamicColors.applyToActivitiesIfAvailable(getActivity().getApplication());
            DynamicColors.applyToActivitiesIfAvailable(getActivity().getApplication(),
                    R.style.Theme_Counters);
            SharedPreferencesUtils.saveBoolean(requireContext(), "useDynamicColors", isChecked);
            requireActivity().recreate();
        });*/

        binding.animationTransactionSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);
                binding.animationVariantsGroup.startAnimation(animation);
                binding.animationVariantsGroup.setVisibility(View.VISIBLE);
            } else {
                binding.animationVariantsGroup.setVisibility(View.GONE);
            }
        });

        binding.vibratorBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);

                binding.clickVibratorRadioGroup.startAnimation(animation);
                binding.clickVibratorEndSwitch.startAnimation(animation);


                binding.clickVibratorRadioGroup.setVisibility(View.VISIBLE);
                binding.clickVibratorEndSwitch.setVisibility(View.VISIBLE);

            } else {
                binding.clickVibratorRadioGroup.setVisibility(View.GONE);
                binding.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                binding.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                binding.clickVibratorEndSwitch.setVisibility(View.GONE);
            }
        });

        binding.clickVibratorRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.fullVibration:
                    //full vibration
                    binding.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                    binding.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                    /*binding.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
                    binding.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                    binding.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);*/
                    break;
                case R.id.partVibration:
                    AlphaAnimation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);
                    binding.vibratorToolBarBtnSwitch.startAnimation(animation);
                    binding.vibratorCounterBtnsSwitch.startAnimation(animation);
                    binding.vibratorToolBarBtnSwitch.setVisibility(View.VISIBLE);
                    binding.vibratorCounterBtnsSwitch.setVisibility(View.VISIBLE);
                    break;
            }

        });

        binding.vibratorCounterBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                /*binding.counterBtnsCheckVibrateLayout.startAnimation(animation);
                binding.counterBtnsCheckVibrateLayout.setVisibility(View.VISIBLE);*/
            } else {
                //binding.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
            }
        });

        binding.vibratorToolBarBtnSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                /*binding.toolbarBtnsCheckVibrateLayoutRight.startAnimation(animation);
                binding.toolbarBtnsCheckVibrateLayoutLeft.startAnimation(animation);
                binding.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.VISIBLE);
                binding.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.VISIBLE);*/
            } else {
                /*binding.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                binding.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);*/
            }
        });

        binding.clickListenerSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);
                binding.textViewClickLayout.startAnimation(animation);
                binding.plusValLayout.startAnimation(animation);
                binding.minusValLayout.startAnimation(animation);
                binding.resetValLayout.startAnimation(animation);
                binding.saveClickObserver.startAnimation(animation);

                binding.textViewClickLayout.setVisibility(View.VISIBLE);
                binding.plusValLayout.setVisibility(View.VISIBLE);
                binding.minusValLayout.setVisibility(View.VISIBLE);
                binding.resetValLayout.setVisibility(View.VISIBLE);
                binding.saveClickObserver.setVisibility(View.VISIBLE);
            } else {
                binding.textViewClickLayout.setVisibility(View.GONE);
                binding.plusValLayout.setVisibility(View.GONE);
                binding.minusValLayout.setVisibility(View.GONE);
                binding.resetValLayout.setVisibility(View.GONE);
                binding.saveClickObserver.setVisibility(View.GONE);
            }
        });

        binding.longPressSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);

                binding.longPressRadioGroup.startAnimation(animation);
                binding.longPressRadioGroup.setVisibility(View.VISIBLE);

            } else {
                binding.longPressRadioGroup.setVisibility(View.GONE);

                binding.textViewLongClickLayout.setVisibility(View.GONE);
                binding.plusValLongLayout.setVisibility(View.GONE);
                binding.minusValLongLayout.setVisibility(View.GONE);
                binding.resetValLongLayout.setVisibility(View.GONE);
                binding.saveLongClickObserver.setVisibility(View.GONE);
            }
        });

        binding.longPressRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.oneLongClick:
                    //one long click
                    binding.textViewLongClickLayout.setVisibility(View.GONE);
                    binding.plusValLongLayout.setVisibility(View.GONE);
                    binding.minusValLongLayout.setVisibility(View.GONE);
                    binding.resetValLongLayout.setVisibility(View.GONE);
                    binding.saveLongClickObserver.setVisibility(View.GONE);

                    break;
                case R.id.regularLongClick:

                    AlphaAnimation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);

                    binding.textViewLongClickLayout.startAnimation(animation);
                    binding.plusValLongLayout.startAnimation(animation);
                    binding.minusValLongLayout.startAnimation(animation);
                    binding.resetValLongLayout.startAnimation(animation);
                    binding.saveLongClickObserver.startAnimation(animation);

                    binding.textViewLongClickLayout.setVisibility(View.VISIBLE);
                    binding.plusValLongLayout.setVisibility(View.VISIBLE);
                    binding.minusValLongLayout.setVisibility(View.VISIBLE);
                    binding.resetValLongLayout.setVisibility(View.VISIBLE);
                    binding.saveLongClickObserver.setVisibility(View.VISIBLE);
                    break;
            }
        });

        // поочередное открытие и закрытие
        binding.clickSwipeBtn.setOnClickListener(v -> {
            if (binding.clickSwipeGroup.getVisibility() == View.GONE) {
                binding.clickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                binding.clickSwipeGroup.setVisibility(View.GONE);
                binding.valueClickInput.setVisibility(View.GONE);
            }
        });

        binding.clickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusClickSwipeGroup:
                    binding.valueClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusClickSwipeGroup:
                    binding.valueClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetClickSwipeGroup:
                    binding.valueClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.longClickSwipeBtn.setOnClickListener(v -> {
            if (binding.longClickSwipeGroup.getVisibility() == View.GONE) {
                binding.longClickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                binding.longClickSwipeGroup.setVisibility(View.GONE);
                binding.valueLongClickInput.setVisibility(View.GONE);
            }
        });

        binding.longClickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.pluslongClickSwipeGroup:
                    binding.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minuslongClickSwipeGroup:
                    binding.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetlongClickSwipeGroup:
                    binding.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.doubleClickSwipeBtn.setOnClickListener(v -> {
            if (binding.doubleClickSwipeGroup.getVisibility() == View.GONE) {
                binding.doubleClickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                binding.doubleClickSwipeGroup.setVisibility(View.GONE);
                binding.valueDoubleClickInput.setVisibility(View.GONE);
            }
        });

        binding.doubleClickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusDoubleClickSwipeGroup:
                    binding.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusDoubleClickSwipeGroup:
                    binding.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetDoubleClickSwipeGroup:
                    binding.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.swipeTopBtn.setOnClickListener(v -> {
            if (binding.swipeTopGroup.getVisibility() == View.GONE) {
                binding.swipeTopGroup.setVisibility(View.VISIBLE);
            } else {
                binding.swipeTopGroup.setVisibility(View.GONE);
                binding.valueSwipeTopInput.setVisibility(View.GONE);
            }
        });

        binding.swipeTopGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeTopGroup:
                    binding.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeTopGroup:
                    binding.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeTopGroup:
                    binding.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.swipeDownBtn.setOnClickListener(v -> {
            if (binding.swipeDownGroup.getVisibility() == View.GONE) {
                binding.swipeDownGroup.setVisibility(View.VISIBLE);
            } else {
                binding.swipeDownGroup.setVisibility(View.GONE);
                binding.valueSwipeDownInput.setVisibility(View.GONE);
            }
        });

        binding.swipeDownGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeDownGroup:
                    binding.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeDownGroup:
                    binding.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeDownGroup:
                    binding.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.swipeRightBtn.setOnClickListener(v -> {
            if (binding.swipeRightGroup.getVisibility() == View.GONE) {
                binding.swipeRightGroup.setVisibility(View.VISIBLE);
            } else {
                binding.swipeRightGroup.setVisibility(View.GONE);
                binding.valueSwipeRightInput.setVisibility(View.GONE);
            }
        });

        binding.swipeRightGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeRightGroup:
                    binding.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeRightGroup:
                    binding.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeRightGroup:
                    binding.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.swipeLeftBtn.setOnClickListener(v -> {
            if (binding.swipeLeftGroup.getVisibility() == View.GONE) {
                binding.swipeLeftGroup.setVisibility(View.VISIBLE);
            } else {
                binding.swipeLeftGroup.setVisibility(View.GONE);
                binding.valueSwipeLeftInput.setVisibility(View.GONE);
            }
        });

        binding.swipeLeftGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeLeftGroup:
                    binding.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeLeftGroup:
                    binding.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeLeftGroup:
                    binding.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
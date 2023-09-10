package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentButtonsSettingsBinding;

public class ButtonsSettingsFragment extends Fragment {
    FragmentButtonsSettingsBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentButtonsSettingsBinding.inflate(getLayoutInflater());

        b.clickVibratorRadioGroup.setVisibility(View.GONE);
        b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
        b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
        b.clickVibratorEndSwitch.setVisibility(View.GONE);
        b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
        b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
        b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
        b.textViewClickLayout.setVisibility(View.GONE);
        b.plusValLayout.setVisibility(View.GONE);
        b.minusValLayout.setVisibility(View.GONE);
        b.resetValLayout.setVisibility(View.GONE);
        b.saveClickObserver.setVisibility(View.GONE);

        b.longPressRadioGroup.setVisibility(View.GONE);

        b.textViewLongClickLayout.setVisibility(View.GONE);
        b.plusValLongLayout.setVisibility(View.GONE);
        b.minusValLongLayout.setVisibility(View.GONE);
        b.resetValLongLayout.setVisibility(View.GONE);
        b.saveLongClickObserver.setVisibility(View.GONE);

        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        b.vibratorBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);

                b.clickVibratorRadioGroup.startAnimation(animation);
                b.clickVibratorEndSwitch.startAnimation(animation);


                b.clickVibratorRadioGroup.setVisibility(View.VISIBLE);
                b.clickVibratorEndSwitch.setVisibility(View.VISIBLE);

            } else {
                b.clickVibratorRadioGroup.setVisibility(View.GONE);
                b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                b.clickVibratorEndSwitch.setVisibility(View.GONE);
            }
        });

        b.clickVibratorRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.fullVibration:
                    //full vibration
                    b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                    b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                    b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
                    b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                    b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
                    break;
                case R.id.partVibration:
                    AlphaAnimation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);
                    b.vibratorToolBarBtnSwitch.startAnimation(animation);
                    b.vibratorCounterBtnsSwitch.startAnimation(animation);
                    b.vibratorToolBarBtnSwitch.setVisibility(View.VISIBLE);
                    b.vibratorCounterBtnsSwitch.setVisibility(View.VISIBLE);
                    break;
            }

        });

        b.vibratorCounterBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                b.counterBtnsCheckVibrateLayout.startAnimation(animation);
                b.counterBtnsCheckVibrateLayout.setVisibility(View.VISIBLE);
            } else {
                b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
            }
        });

        b.vibratorToolBarBtnSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                b.toolbarBtnsCheckVibrateLayoutRight.startAnimation(animation);
                b.toolbarBtnsCheckVibrateLayoutLeft.startAnimation(animation);
                b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.VISIBLE);
                b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.VISIBLE);
            } else {
                b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
            }
        });

        b.clickListenerSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);
                b.textViewClickLayout.startAnimation(animation);
                b.plusValLayout.startAnimation(animation);
                b.minusValLayout.startAnimation(animation);
                b.resetValLayout.startAnimation(animation);
                b.saveClickObserver.startAnimation(animation);

                b.textViewClickLayout.setVisibility(View.VISIBLE);
                b.plusValLayout.setVisibility(View.VISIBLE);
                b.minusValLayout.setVisibility(View.VISIBLE);
                b.resetValLayout.setVisibility(View.VISIBLE);
                b.saveClickObserver.setVisibility(View.VISIBLE);
            } else {
                b.textViewClickLayout.setVisibility(View.GONE);
                b.plusValLayout.setVisibility(View.GONE);
                b.minusValLayout.setVisibility(View.GONE);
                b.resetValLayout.setVisibility(View.GONE);
                b.saveClickObserver.setVisibility(View.GONE);
            }
        });

        b.longPressSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);

                b.longPressRadioGroup.startAnimation(animation);
                b.longPressRadioGroup.setVisibility(View.VISIBLE);

            } else {
                b.longPressRadioGroup.setVisibility(View.GONE);

                b.textViewLongClickLayout.setVisibility(View.GONE);
                b.plusValLongLayout.setVisibility(View.GONE);
                b.minusValLongLayout.setVisibility(View.GONE);
                b.resetValLongLayout.setVisibility(View.GONE);
                b.saveLongClickObserver.setVisibility(View.GONE);
            }
        });

        b.longPressRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.oneLongClick:
                    //one long click
                    b.textViewLongClickLayout.setVisibility(View.GONE);
                    b.plusValLongLayout.setVisibility(View.GONE);
                    b.minusValLongLayout.setVisibility(View.GONE);
                    b.resetValLongLayout.setVisibility(View.GONE);
                    b.saveLongClickObserver.setVisibility(View.GONE);

                    break;
                case R.id.regularLongClick:

                    AlphaAnimation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);

                    b.textViewLongClickLayout.startAnimation(animation);
                    b.plusValLongLayout.startAnimation(animation);
                    b.minusValLongLayout.startAnimation(animation);
                    b.resetValLongLayout.startAnimation(animation);
                    b.saveLongClickObserver.startAnimation(animation);

                    b.textViewLongClickLayout.setVisibility(View.VISIBLE);
                    b.plusValLongLayout.setVisibility(View.VISIBLE);
                    b.minusValLongLayout.setVisibility(View.VISIBLE);
                    b.resetValLongLayout.setVisibility(View.VISIBLE);
                    b.saveLongClickObserver.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }
}
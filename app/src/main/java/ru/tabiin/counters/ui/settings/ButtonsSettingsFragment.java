package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CompoundButton;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.slider.Slider;

import java.util.Objects;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentButtonsSettingsBinding;
import ru.tabiin.counters.util.SharedPreferencesUtils;

public class ButtonsSettingsFragment extends Fragment {
    FragmentButtonsSettingsBinding b;
    String vibRadioFullOrPart;
    String longclickOneOrRegular;

    private int plusClickValue = 1;
    private int minusClickValue = 1;
    private int resetClickValue = 0;

    private int plusLongClickValue = 1;
    private int minusLongClickValue = 1;
    private int resetLongClickValue = 0;


    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return binding.getRoot()
     */


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
        b.clickVibrateSlider.setVisibility(View.GONE);
        b.endTargetVibrateSlider.setVisibility(View.GONE);

        b.longPressRadioGroup.setVisibility(View.GONE);

        b.vibratorLongBtnsSwitch.setVisibility(View.GONE);
        b.clickLongVibrateSlider.setVisibility(View.GONE);

        b.textViewLongClickLayout.setVisibility(View.GONE);
        b.plusValLongLayout.setVisibility(View.GONE);
        b.minusValLongLayout.setVisibility(View.GONE);
        b.resetValLongLayout.setVisibility(View.GONE);
        b.saveLongClickObserver.setVisibility(View.GONE);

        plusClickValue = SharedPreferencesUtils.getInteger(requireContext(), "plusValueClickInput");
        minusClickValue = SharedPreferencesUtils.getInteger(requireContext(), "minusValueClickInput");
        resetClickValue = SharedPreferencesUtils.getInteger(requireContext(), "resetValueClickInput");

        plusLongClickValue = SharedPreferencesUtils.getInteger(requireContext(), "plusValueLongClickInput");
        minusLongClickValue = SharedPreferencesUtils.getInteger(requireContext(), "minusValueLongClickInput");
        resetLongClickValue = SharedPreferencesUtils.getInteger(requireContext(), "resetValueLongClickInput");

        b.vibratorBtnsSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorBtnsSwitch"));

        if (b.vibratorBtnsSwitch.isChecked()) {
            b.clickVibrateSlider.setVisibility(View.VISIBLE);
            b.clickVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "clickVibrateSlider", 50));

            b.clickVibratorRadioGroup.setVisibility(View.VISIBLE);
            b.clickVibratorRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "vibration"));

            if (b.partVibration.isChecked()) {

                b.vibratorCounterBtnsSwitch.setVisibility(View.VISIBLE);
                b.vibratorCounterBtnsSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorCounterBtnsSwitch"));
                if (b.vibratorCounterBtnsSwitch.isChecked()) {
                    b.counterBtnsCheckVibrateLayout.setVisibility(View.VISIBLE);
                    b.plusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "plusBtnSetVib") == 1);
                    b.minusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "minusBtnSetVib") == 1);
                    b.resetBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "resetBtnSetVib") == 1);
                    SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorCounterBtnsSwitch", b.vibratorCounterBtnsSwitch.isChecked());
                }

                b.vibratorToolBarBtnSwitch.setVisibility(View.VISIBLE);
                b.vibratorToolBarBtnSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorToolBarBtnSwitch"));
                if (b.vibratorToolBarBtnSwitch.isChecked()) {
                    b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.VISIBLE);
                    b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.VISIBLE);

                    b.settingsBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "settingsBtnSetVib") == 1);
                    b.tutorialBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "tutorialBtnSetVib") == 1);
                    b.saveBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "saveBtnSetVib") == 1);

                    b.editBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "editBtnSetVib") == 1);
                    b.deleteBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "deleteBtnSetVib") == 1);
                    b.listBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "listBtnSetVib") == 1);

                }

            } else if (b.fullVibration.isChecked()) {
                b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
                b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
                b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
            }

            b.clickVibratorEndSwitch.setVisibility(View.VISIBLE);
            b.clickVibratorEndSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "clickVibratorEndSwitch"));
            if (b.clickVibratorEndSwitch.isChecked()) {
                b.endTargetVibrateSlider.setVisibility(View.VISIBLE);
                b.endTargetVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "endTargetVibrateSlider", 500));
            }

        } else {
            b.clickVibratorRadioGroup.setVisibility(View.GONE);
            b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
            b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
            b.clickVibratorEndSwitch.setVisibility(View.GONE);
            b.clickVibrateSlider.setVisibility(View.GONE);
            b.endTargetVibrateSlider.setVisibility(View.GONE);
            b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
            b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
            b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
            b.plusBtnSetVib.setVisibility(View.GONE);
            b.minusBtnSetVib.setVisibility(View.GONE);
            b.resetBtnSetVib.setVisibility(View.GONE);
            b.settingsBtnSetVib.setVisibility(View.GONE);
            b.tutorialBtnSetVib.setVisibility(View.GONE);
            b.saveBtnSetVib.setVisibility(View.GONE);
            b.editBtnSetVib.setVisibility(View.GONE);
            b.deleteBtnSetVib.setVisibility(View.GONE);
            b.listBtnSetVib.setVisibility(View.GONE);
        }

        b.clickListenerSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "clickListenerSwitch"));

        if (b.clickListenerSwitch.isChecked()) {
            b.textViewClickLayout.setVisibility(View.VISIBLE);
            b.plusValLayout.setVisibility(View.VISIBLE);
            b.minusValLayout.setVisibility(View.VISIBLE);
            b.resetValLayout.setVisibility(View.VISIBLE);
            b.saveClickObserver.setVisibility(View.VISIBLE);

            b.plusValueClickInput.setText(String.valueOf(plusClickValue));
            b.minusValueClickInput.setText(String.valueOf(minusClickValue));
            b.resetValueClickInput.setText(String.valueOf(resetClickValue));

        } else {
            b.textViewClickLayout.setVisibility(View.GONE);
            b.plusValLayout.setVisibility(View.GONE);
            b.minusValLayout.setVisibility(View.GONE);
            b.resetValLayout.setVisibility(View.GONE);
            b.saveClickObserver.setVisibility(View.GONE);

        }

        b.longPressSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "longPressSwitch"));

        if (b.longPressSwitch.isChecked()) {
            b.vibratorLongBtnsSwitch.setVisibility(View.VISIBLE);
            b.vibratorLongBtnsSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorLongBtnsSwitch"));
            if (b.vibratorLongBtnsSwitch.isChecked()) {
                b.clickLongVibrateSlider.setVisibility(View.VISIBLE);
                b.clickLongVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "clickLongVibrateSlider"));
            }
            b.longPressRadioGroup.setVisibility(View.VISIBLE);
            if (SharedPreferencesUtils.getBoolean(requireContext(), "regularLongClick")) {
                b.textViewLongClickLayout.setVisibility(View.VISIBLE);
                b.plusValLongLayout.setVisibility(View.VISIBLE);
                b.minusValLongLayout.setVisibility(View.VISIBLE);
                b.resetValLongLayout.setVisibility(View.VISIBLE);
                b.saveLongClickObserver.setVisibility(View.VISIBLE);

                b.plusValueLongClickInput.setVisibility(View.VISIBLE);
                b.minusValueLongClickInput.setVisibility(View.VISIBLE);
                b.resetValueLongClickInput.setVisibility(View.VISIBLE);

                b.plusValueLongClickInput.setText(String.valueOf(plusLongClickValue));
                b.minusValueLongClickInput.setText(String.valueOf(minusLongClickValue));
                b.resetValueLongClickInput.setText(String.valueOf(resetLongClickValue));

            }
        } else {
            b.longPressRadioGroup.setVisibility(View.GONE);
            b.textViewLongClickLayout.setVisibility(View.GONE);
            b.plusValLongLayout.setVisibility(View.GONE);
            b.minusValLongLayout.setVisibility(View.GONE);
            b.resetValLongLayout.setVisibility(View.GONE);
            b.plusValueLongClickInput.setVisibility(View.GONE);
            b.minusValueLongClickInput.setVisibility(View.GONE);
            b.resetValueLongClickInput.setVisibility(View.GONE);
            b.saveLongClickObserver.setVisibility(View.GONE);
            b.vibratorLongBtnsSwitch.setVisibility(View.GONE);
            b.clickLongVibrateSlider.setVisibility(View.GONE);
        }

        if (b.regularLongClick.isChecked()) {
            b.textViewLongClickLayout.setVisibility(View.VISIBLE);
            b.plusValLongLayout.setVisibility(View.VISIBLE);
            b.minusValLongLayout.setVisibility(View.VISIBLE);
            b.resetValLongLayout.setVisibility(View.VISIBLE);
            b.saveLongClickObserver.setVisibility(View.VISIBLE);
        }

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
                b.clickVibrateSlider.startAnimation(animation);

                b.clickVibrateSlider.setVisibility(View.VISIBLE);
                b.clickVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "clickVibrateSlider", 50));
                b.clickVibratorRadioGroup.setVisibility(View.VISIBLE);

                b.vibratorCounterBtnsSwitch.setVisibility(View.VISIBLE);
                b.vibratorToolBarBtnSwitch.setVisibility(View.VISIBLE);

                if (b.vibratorCounterBtnsSwitch.isChecked()) {
                    b.plusBtnSetVib.setVisibility(View.VISIBLE);
                    b.minusBtnSetVib.setVisibility(View.VISIBLE);
                    b.resetBtnSetVib.setVisibility(View.VISIBLE);
                }

                if (b.vibratorToolBarBtnSwitch.isChecked()) {
                    b.settingsBtnSetVib.setVisibility(View.VISIBLE);
                    b.tutorialBtnSetVib.setVisibility(View.VISIBLE);
                    b.saveBtnSetVib.setVisibility(View.VISIBLE);
                    b.editBtnSetVib.setVisibility(View.VISIBLE);
                    b.deleteBtnSetVib.setVisibility(View.VISIBLE);
                    b.listBtnSetVib.setVisibility(View.VISIBLE);
                }
                b.clickVibratorEndSwitch.setVisibility(View.VISIBLE);
                b.clickVibratorEndSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "clickVibratorEndSwitch"));

                if (b.clickVibratorEndSwitch.isChecked()) {
                    b.endTargetVibrateSlider.setVisibility(View.VISIBLE);
                    b.endTargetVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "endTargetVibrateSlider", 500));
                }

            } else {
                b.clickVibratorRadioGroup.setVisibility(View.GONE);
                b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                b.clickVibratorEndSwitch.setVisibility(View.GONE);
                b.clickVibrateSlider.setVisibility(View.GONE);
                b.endTargetVibrateSlider.setVisibility(View.GONE);

                b.plusBtnSetVib.setVisibility(View.GONE);
                b.minusBtnSetVib.setVisibility(View.GONE);
                b.resetBtnSetVib.setVisibility(View.GONE);
                b.settingsBtnSetVib.setVisibility(View.GONE);
                b.tutorialBtnSetVib.setVisibility(View.GONE);
                b.saveBtnSetVib.setVisibility(View.GONE);
                b.editBtnSetVib.setVisibility(View.GONE);
                b.deleteBtnSetVib.setVisibility(View.GONE);
                b.listBtnSetVib.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorBtnsSwitch", isChecked);
            //requireActivity().recreate();
        });

        b.clickVibrateSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                SharedPreferencesUtils.saveInteger(requireContext(), "clickVibrateSlider", (int) slider.getValue());
                requireActivity().recreate();
            }
        });

        b.clickVibratorRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "vibration"));
        b.clickVibratorRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.fullVibration:
                    //full vibration
                    b.vibratorToolBarBtnSwitch.setVisibility(View.GONE);
                    b.vibratorCounterBtnsSwitch.setVisibility(View.GONE);
                    b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
                    b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                    b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);

                    b.plusBtnSetVib.setVisibility(View.GONE);
                    b.minusBtnSetVib.setVisibility(View.GONE);
                    b.resetBtnSetVib.setVisibility(View.GONE);
                    b.settingsBtnSetVib.setVisibility(View.GONE);
                    b.tutorialBtnSetVib.setVisibility(View.GONE);
                    b.saveBtnSetVib.setVisibility(View.GONE);
                    b.editBtnSetVib.setVisibility(View.GONE);
                    b.deleteBtnSetVib.setVisibility(View.GONE);
                    b.listBtnSetVib.setVisibility(View.GONE);

                    vibRadioFullOrPart = "fullVib";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkVib", 0);
                    SharedPreferencesUtils.saveInteger(requireContext(), "vibration", R.id.fullVibration);
                    //requireActivity().recreate();
                    break;
                case R.id.partVibration:
                    AlphaAnimation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(1000);
                    b.vibratorToolBarBtnSwitch.startAnimation(animation);
                    b.vibratorCounterBtnsSwitch.startAnimation(animation);
                    b.vibratorToolBarBtnSwitch.setVisibility(View.VISIBLE);
                    b.vibratorToolBarBtnSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorToolBarBtnSwitch"));
                    if (b.vibratorToolBarBtnSwitch.isChecked()) {
                        b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.VISIBLE);
                        b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.VISIBLE);

                        b.settingsBtnSetVib.setVisibility(View.VISIBLE);
                        b.tutorialBtnSetVib.setVisibility(View.VISIBLE);
                        b.saveBtnSetVib.setVisibility(View.VISIBLE);
                        b.editBtnSetVib.setVisibility(View.VISIBLE);
                        b.deleteBtnSetVib.setVisibility(View.VISIBLE);
                        b.listBtnSetVib.setVisibility(View.VISIBLE);

                        b.settingsBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "settingsBtnSetVib") == 1);
                        b.tutorialBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "tutorialBtnSetVib") == 1);
                        b.saveBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "saveBtnSetVib") == 1);

                        b.editBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "editBtnSetVib") == 1);
                        b.deleteBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "deleteBtnSetVib") == 1);
                        b.listBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "listBtnSetVib") == 1);

                    } else {
                        b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                        b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
                        b.settingsBtnSetVib.setVisibility(View.GONE);
                        b.tutorialBtnSetVib.setVisibility(View.GONE);
                        b.saveBtnSetVib.setVisibility(View.GONE);
                        b.editBtnSetVib.setVisibility(View.GONE);
                        b.deleteBtnSetVib.setVisibility(View.GONE);
                        b.listBtnSetVib.setVisibility(View.GONE);
                    }

                    b.vibratorCounterBtnsSwitch.setVisibility(View.VISIBLE);
                    b.vibratorCounterBtnsSwitch.setChecked(SharedPreferencesUtils.getBoolean(requireContext(), "vibratorCounterBtnsSwitch"));
                    if (b.vibratorCounterBtnsSwitch.isChecked()) {
                        b.counterBtnsCheckVibrateLayout.setVisibility(View.VISIBLE);

                        b.plusBtnSetVib.setVisibility(View.VISIBLE);
                        b.minusBtnSetVib.setVisibility(View.VISIBLE);
                        b.resetBtnSetVib.setVisibility(View.VISIBLE);

                        b.plusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "plusBtnSetVib") == 1);
                        b.minusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "minusBtnSetVib") == 1);
                        b.resetBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "resetBtnSetVib") == 1);
                    } else {
                        b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
                        b.plusBtnSetVib.setVisibility(View.GONE);
                        b.minusBtnSetVib.setVisibility(View.GONE);
                        b.resetBtnSetVib.setVisibility(View.GONE);
                    }

                    SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorCounterBtnsSwitch", b.vibratorCounterBtnsSwitch.isChecked());

                    vibRadioFullOrPart = "partVib";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkVib", 1);
                    SharedPreferencesUtils.saveInteger(requireContext(), "vibration", R.id.partVibration);
                    //requireActivity().recreate();
                    //break;
            }

        });

        b.vibratorCounterBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                b.counterBtnsCheckVibrateLayout.startAnimation(animation);
                b.counterBtnsCheckVibrateLayout.setVisibility(View.VISIBLE);

                b.plusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "plusBtnSetVib") == 1);
                b.minusBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "minusBtnSetVib") == 1);
                b.resetBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "resetBtnSetVib") == 1);


            } else {
                b.counterBtnsCheckVibrateLayout.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorCounterBtnsSwitch", isChecked);
            //requireActivity().recreate();
        });

        b.plusBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "plusBtnSetVib", state);
            requireActivity().recreate();
        });

        b.minusBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "minusBtnSetVib", state);
            requireActivity().recreate();
        });

        b.resetBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "resetBtnSetVib", state);
            requireActivity().recreate();
        });

        b.vibratorToolBarBtnSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                b.toolbarBtnsCheckVibrateLayoutRight.startAnimation(animation);
                b.toolbarBtnsCheckVibrateLayoutLeft.startAnimation(animation);

                b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.VISIBLE);
                b.settingsBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "settingsBtnSetVib") == 1);
                b.tutorialBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "tutorialBtnSetVib") == 1);
                b.saveBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "saveBtnSetVib") == 1);

                b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.VISIBLE);
                b.editBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "editBtnSetVib") == 1);
                b.deleteBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "deleteBtnSetVib") == 1);
                b.listBtnSetVib.setChecked(SharedPreferencesUtils.getInteger(requireContext(), "listBtnSetVib") == 1);

            } else {
                b.toolbarBtnsCheckVibrateLayoutRight.setVisibility(View.GONE);
                b.toolbarBtnsCheckVibrateLayoutLeft.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorToolBarBtnSwitch", isChecked);
            //requireActivity().recreate();
        });

        b.settingsBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "settingsBtnSetVib", state);
            requireActivity().recreate();
        });

        b.tutorialBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "tutorialBtnSetVib", state);
            requireActivity().recreate();
        });

        b.saveBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "saveBtnSetVib", state);
            requireActivity().recreate();
        });

        b.editBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "editBtnSetVib", state);
            requireActivity().recreate();
        });

        b.deleteBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "deleteBtnSetVib", state);
            requireActivity().recreate();
        });

        b.listBtnSetVib.addOnCheckedStateChangedListener((checkBox, state) -> {
            SharedPreferencesUtils.saveInteger(requireContext(), "listBtnSetVib", state);
            requireActivity().recreate();
        });

        b.clickVibratorEndSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Animation animation = new AlphaAnimation(0, 1);
                b.counterBtnsCheckVibrateLayout.startAnimation(animation);
                b.endTargetVibrateSlider.setVisibility(View.VISIBLE);
                b.endTargetVibrateSlider.setValue(SharedPreferencesUtils.getInteger(requireContext(), "endTargetVibrateSlider", 500));
            } else {
                b.endTargetVibrateSlider.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "clickVibratorEndSwitch", isChecked);
            //requireActivity().recreate();

        });

        b.endTargetVibrateSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                SharedPreferencesUtils.saveInteger(requireContext(), "endTargetVibrateSlider", (int) slider.getValue());
                requireActivity().recreate();
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


                /*b.plusValueClickInput.setText(plusClickValue);
                b.minusValueClickInput.setText(minusClickValue);
                b.resetValueClickInput.setText(resetClickValue);*/

            } else {
                b.textViewClickLayout.setVisibility(View.GONE);
                b.plusValLayout.setVisibility(View.GONE);
                b.minusValLayout.setVisibility(View.GONE);
                b.resetValLayout.setVisibility(View.GONE);
                b.saveClickObserver.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "clickListenerSwitch", isChecked);
            //requireActivity().recreate();
        });

        b.saveClickObserver.setOnClickListener(v -> {
            if (b.plusValueClickInput.getText() == null || b.plusValueClickInput.getText().toString() == "") {
                b.plusValueClickInput.setText(plusClickValue);
            }
            SharedPreferencesUtils.saveInteger(requireContext(), "plusValueClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.plusValueClickInput.getText()).toString()));

            plusClickValue = Integer.parseInt(b.plusValueClickInput.getText().toString());

            if (b.minusValueClickInput.getText() == null || b.minusValueClickInput.getText().toString() == "") {
                b.minusValueClickInput.setText(minusClickValue);
            }
            SharedPreferencesUtils.saveInteger(requireContext(), "minusValueClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.minusValueClickInput.getText()).toString()));

            minusClickValue = Integer.parseInt(b.minusValueClickInput.getText().toString());

            if (b.resetValueClickInput.getText() == null || b.resetValueClickInput.getText().toString() == "") {
                b.resetValueClickInput.setText(resetClickValue);
            }
            SharedPreferencesUtils.saveInteger(requireContext(), "resetValueClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.resetValueClickInput.getText()).toString()));

            resetClickValue = Integer.parseInt(b.resetValueClickInput.getText().toString());

            b.plusValueClickInput.setCursorVisible(false);
            b.minusValueClickInput.setCursorVisible(false);
            b.resetValueClickInput.setCursorVisible(false);
        });

        b.plusValueClickInput.setOnClickListener(v -> {
            b.plusValueClickInput.setCursorVisible(true);
        });

        b.minusValueClickInput.setOnClickListener(v -> {
            b.minusValueClickInput.setCursorVisible(true);
        });

        b.resetValueClickInput.setOnClickListener(v -> {
            b.resetValueClickInput.setCursorVisible(true);
        });

        b.longPressSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);

                b.vibratorLongBtnsSwitch.setVisibility(View.VISIBLE);
                if (b.vibratorLongBtnsSwitch.isChecked()) {
                    b.clickLongVibrateSlider.setVisibility(View.VISIBLE);
                }

                b.longPressRadioGroup.startAnimation(animation);
                b.longPressRadioGroup.setVisibility(View.VISIBLE);
                if (b.regularLongClick.isChecked()) {
                    b.textViewLongClickLayout.setVisibility(View.VISIBLE);
                    b.plusValLongLayout.setVisibility(View.VISIBLE);
                    b.minusValLongLayout.setVisibility(View.VISIBLE);
                    b.resetValLongLayout.setVisibility(View.VISIBLE);
                    b.plusValueLongClickInput.setVisibility(View.VISIBLE);
                    b.minusValueLongClickInput.setVisibility(View.VISIBLE);
                    b.resetValueLongClickInput.setVisibility(View.VISIBLE);
                    b.saveLongClickObserver.setVisibility(View.VISIBLE);
                } else if (b.oneLongClick.isChecked()) {
                    b.textViewLongClickLayout.setVisibility(View.GONE);
                    b.plusValLongLayout.setVisibility(View.GONE);
                    b.minusValLongLayout.setVisibility(View.GONE);
                    b.resetValLongLayout.setVisibility(View.GONE);
                    b.plusValueLongClickInput.setVisibility(View.GONE);
                    b.minusValueLongClickInput.setVisibility(View.GONE);
                    b.resetValueLongClickInput.setVisibility(View.GONE);
                    b.saveLongClickObserver.setVisibility(View.GONE);
                }
            } else {
                b.longPressRadioGroup.setVisibility(View.GONE);
                b.textViewLongClickLayout.setVisibility(View.GONE);
                b.plusValLongLayout.setVisibility(View.GONE);
                b.minusValLongLayout.setVisibility(View.GONE);
                b.resetValLongLayout.setVisibility(View.GONE);
                b.saveLongClickObserver.setVisibility(View.GONE);
                b.vibratorLongBtnsSwitch.setVisibility(View.GONE);
                b.clickLongVibrateSlider.setVisibility(View.GONE);
                b.plusValueLongClickInput.setVisibility(View.GONE);
                b.minusValueLongClickInput.setVisibility(View.GONE);
                b.resetValueLongClickInput.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "longPressSwitch", isChecked);
            //requireActivity().recreate();
        });

        b.vibratorLongBtnsSwitch.setOnCheckedChangeListener((v, isChecked) -> {

            if (b.vibratorLongBtnsSwitch.isChecked()) {
                b.clickLongVibrateSlider.setVisibility(View.VISIBLE);
            } else {
                b.clickLongVibrateSlider.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "vibratorLongBtnsSwitch", isChecked);
        });

        b.clickLongVibrateSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                SharedPreferencesUtils.saveInteger(requireContext(), "clickLongVibrateSlider", (int) slider.getValue());
                requireActivity().recreate();
            }
        });


        b.longPressRadioGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "longclick"));
        b.longPressRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.oneLongClick:
                    //one long click
                    b.textViewLongClickLayout.setVisibility(View.GONE);
                    b.plusValLongLayout.setVisibility(View.GONE);
                    b.minusValLongLayout.setVisibility(View.GONE);
                    b.resetValLongLayout.setVisibility(View.GONE);
                    b.saveLongClickObserver.setVisibility(View.GONE);

                    longclickOneOrRegular = "oneLongClick";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkLongClick", 0);
                    SharedPreferencesUtils.saveInteger(requireContext(), "longclick", R.id.oneLongClick);
                    //requireActivity().recreate();
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

                    b.textViewLongClickLayout.setVisibility(View.VISIBLE);
                    b.plusValLongLayout.setVisibility(View.VISIBLE);
                    b.minusValLongLayout.setVisibility(View.VISIBLE);
                    b.resetValLongLayout.setVisibility(View.VISIBLE);
                    b.saveLongClickObserver.setVisibility(View.VISIBLE);

                    b.plusValueLongClickInput.setText(String.valueOf(plusLongClickValue));
                    b.minusValueLongClickInput.setText(String.valueOf(minusLongClickValue));
                    b.resetValueLongClickInput.setText(String.valueOf(resetLongClickValue));

                    longclickOneOrRegular = "regularLongClick";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkLongClick", 1);
                    SharedPreferencesUtils.saveInteger(requireContext(), "longclick", R.id.regularLongClick);
                    //requireActivity().recreate();
                    break;
            }
        });

        b.saveLongClickObserver.setOnClickListener(v -> {
            if (b.plusValueLongClickInput.getText() == null || b.plusValueLongClickInput.getText().toString() == "") {
                b.plusValueLongClickInput.setText(plusLongClickValue);
            }

            SharedPreferencesUtils.saveInteger(requireContext(), "plusValueLongClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.plusValueLongClickInput.getText()).toString()));

            plusLongClickValue = Integer.parseInt(b.plusValueLongClickInput.getText().toString());

            if (b.minusValueLongClickInput.getText() == null || b.minusValueLongClickInput.getText().toString() == "") {
                b.minusValueLongClickInput.setText(minusLongClickValue);
            }
            SharedPreferencesUtils.saveInteger(requireContext(), "minusValueLongClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.minusValueLongClickInput.getText()).toString()));

            minusLongClickValue = Integer.parseInt(b.minusValueLongClickInput.getText().toString());

            if (b.resetValueLongClickInput.getText() == null || b.resetValueLongClickInput.getText().toString() == "") {
                b.resetValueLongClickInput.setText(resetLongClickValue);
            }
            SharedPreferencesUtils.saveInteger(requireContext(), "resetValueLongClickInput",
                    Integer.parseInt(Objects.requireNonNull(b.resetValueLongClickInput.getText()).toString()));

            resetLongClickValue = Integer.parseInt(b.resetValueLongClickInput.getText().toString());

            b.plusValueLongClickInput.setCursorVisible(false);
            b.minusValueLongClickInput.setCursorVisible(false);
            b.resetValueLongClickInput.setCursorVisible(false);
        });

        b.plusValueLongClickInput.setOnClickListener(v -> {
            b.plusValueLongClickInput.setCursorVisible(true);
            b.plusValueLongClickInput.setFocusableInTouchMode(true);
        });

        b.minusValueLongClickInput.setOnClickListener(v -> {
            b.minusValueLongClickInput.setCursorVisible(true);
            b.minusValueLongClickInput.setFocusableInTouchMode(true);
        });

        b.resetValueLongClickInput.setOnClickListener(v -> {
            b.resetValueLongClickInput.setCursorVisible(true);
            b.resetValueLongClickInput.setFocusableInTouchMode(true);
        });
    }
}
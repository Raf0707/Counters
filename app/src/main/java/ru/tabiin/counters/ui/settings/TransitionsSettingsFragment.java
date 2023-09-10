package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.RadioGroup;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentTransitionsSettingsBinding;
import ru.tabiin.counters.util.SharedPreferencesUtils;

public class TransitionsSettingsFragment extends Fragment {
    FragmentTransitionsSettingsBinding b;
    private String anim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentTransitionsSettingsBinding.inflate(getLayoutInflater());

        b.animationVariantsGroup.setVisibility(View.GONE);

        b.animationTransactionSwitch.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                AlphaAnimation animation = new AlphaAnimation(0, 1);
                animation.setDuration(1000);
                b.animationVariantsGroup.startAnimation(animation);
                b.animationVariantsGroup.setVisibility(View.VISIBLE);
            } else {
                b.animationVariantsGroup.setVisibility(View.GONE);
            }

            SharedPreferencesUtils.saveBoolean(requireContext(), "useAnimations", isChecked);
            requireActivity().recreate();
        });

        b.animationVariantsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fadeAnim:
                        anim = "fade";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 1);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                    case R.id.explodeAnim:
                        anim = "explode";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 2);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                    case R.id.slideTopAnim:
                        anim = "slideTop";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 3);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                    case R.id.slideBottomAnim:
                        anim = "slideBottom";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 4);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                    case R.id.slideRightAnim:
                        anim = "slideRight";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 5);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                    case R.id.slideLeftAnim:
                        anim = "slideLeft";
                        SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 6);
                        SharedPreferencesUtils.saveString(requireContext(), "animation", anim);
                        requireActivity().recreate();
                        break;

                }
            }
        });

        return b.getRoot();
    }
}
package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        b = FragmentTransitionsSettingsBinding.inflate(inflater, container, false);

        b.animationVariantsGroup.check(SharedPreferencesUtils.getInteger(requireContext(), "animation"));

        b.animationVariantsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.noAnim:
                    anim = "no anim";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 0);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.noAnim);
                    requireActivity().recreate();
                    break;

                case R.id.fadeAnim:
                    anim = "fade";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 1);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.fadeAnim);
                    requireActivity().recreate();
                    break;

                case R.id.explodeAnim:
                    anim = "explode";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 2);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.explodeAnim);
                    requireActivity().recreate();
                    break;

                case R.id.slideTopAnim:
                    anim = "slideTop";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 3);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.slideTopAnim);
                    requireActivity().recreate();
                    break;

                case R.id.slideBottomAnim:
                    anim = "slideBottom";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 4);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.slideBottomAnim);
                    requireActivity().recreate();
                    break;

                case R.id.slideRightAnim:
                    anim = "slideRight";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 5);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.slideRightAnim);
                    requireActivity().recreate();
                    break;

                case R.id.slideLeftAnim:
                    anim = "slideLeft";
                    SharedPreferencesUtils.saveInteger(requireContext(), "checkAnim", 6);
                    SharedPreferencesUtils.saveInteger(requireContext(), "animation", R.id.slideLeftAnim);
                    requireActivity().recreate();
                    break;

            }
        });

        return b.getRoot();
    }
}
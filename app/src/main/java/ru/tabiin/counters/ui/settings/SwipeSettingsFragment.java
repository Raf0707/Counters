package ru.tabiin.counters.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentSwipeSettingsBinding;

public class SwipeSettingsFragment extends Fragment {
    FragmentSwipeSettingsBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentSwipeSettingsBinding.inflate(getLayoutInflater());

        b.clickSwipeGroup.setVisibility(View.GONE);
        b.longClickSwipeGroup.setVisibility(View.GONE);
        b.doubleClickSwipeGroup.setVisibility(View.GONE);
        b.swipeTopGroup.setVisibility(View.GONE);
        b.swipeDownGroup.setVisibility(View.GONE);
        b.swipeRightGroup.setVisibility(View.GONE);
        b.swipeLeftGroup.setVisibility(View.GONE);

        b.valueClickInput.setVisibility(View.GONE);
        b.valueLongClickInput.setVisibility(View.GONE);
        b.valueDoubleClickInput.setVisibility(View.GONE);
        b.valueSwipeTopInput.setVisibility(View.GONE);
        b.valueSwipeDownInput.setVisibility(View.GONE);
        b.valueSwipeRightInput.setVisibility(View.GONE);
        b.valueSwipeLeftInput.setVisibility(View.GONE);

        b.clickSwipeBtn.setOnClickListener(v -> {
            if (b.clickSwipeGroup.getVisibility() == View.GONE) {
                b.clickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                b.clickSwipeGroup.setVisibility(View.GONE);
                b.valueClickInput.setVisibility(View.GONE);
            }
        });

        b.clickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusClickSwipeGroup:
                    b.valueClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusClickSwipeGroup:
                    b.valueClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetClickSwipeGroup:
                    b.valueClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.longClickSwipeBtn.setOnClickListener(v -> {
            if (b.longClickSwipeGroup.getVisibility() == View.GONE) {
                b.longClickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                b.longClickSwipeGroup.setVisibility(View.GONE);
                b.valueLongClickInput.setVisibility(View.GONE);
            }
        });

        b.longClickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.pluslongClickSwipeGroup:
                    b.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minuslongClickSwipeGroup:
                    b.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetlongClickSwipeGroup:
                    b.valueLongClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.doubleClickSwipeBtn.setOnClickListener(v -> {
            if (b.doubleClickSwipeGroup.getVisibility() == View.GONE) {
                b.doubleClickSwipeGroup.setVisibility(View.VISIBLE);
            } else {
                b.doubleClickSwipeGroup.setVisibility(View.GONE);
                b.valueDoubleClickInput.setVisibility(View.GONE);
            }
        });

        b.doubleClickSwipeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusDoubleClickSwipeGroup:
                    b.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusDoubleClickSwipeGroup:
                    b.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetDoubleClickSwipeGroup:
                    b.valueDoubleClickInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.swipeTopBtn.setOnClickListener(v -> {
            if (b.swipeTopGroup.getVisibility() == View.GONE) {
                b.swipeTopGroup.setVisibility(View.VISIBLE);
            } else {
                b.swipeTopGroup.setVisibility(View.GONE);
                b.valueSwipeTopInput.setVisibility(View.GONE);
            }
        });

        b.swipeTopGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeTopGroup:
                    b.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeTopGroup:
                    b.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeTopGroup:
                    b.valueSwipeTopInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.swipeDownBtn.setOnClickListener(v -> {
            if (b.swipeDownGroup.getVisibility() == View.GONE) {
                b.swipeDownGroup.setVisibility(View.VISIBLE);
            } else {
                b.swipeDownGroup.setVisibility(View.GONE);
                b.valueSwipeDownInput.setVisibility(View.GONE);
            }
        });

        b.swipeDownGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeDownGroup:
                    b.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeDownGroup:
                    b.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeDownGroup:
                    b.valueSwipeDownInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.swipeRightBtn.setOnClickListener(v -> {
            if (b.swipeRightGroup.getVisibility() == View.GONE) {
                b.swipeRightGroup.setVisibility(View.VISIBLE);
            } else {
                b.swipeRightGroup.setVisibility(View.GONE);
                b.valueSwipeRightInput.setVisibility(View.GONE);
            }
        });

        b.swipeRightGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeRightGroup:
                    b.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeRightGroup:
                    b.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeRightGroup:
                    b.valueSwipeRightInput.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.swipeLeftBtn.setOnClickListener(v -> {
            if (b.swipeLeftGroup.getVisibility() == View.GONE) {
                b.swipeLeftGroup.setVisibility(View.VISIBLE);
            } else {
                b.swipeLeftGroup.setVisibility(View.GONE);
                b.valueSwipeLeftInput.setVisibility(View.GONE);
            }
        });

        b.swipeLeftGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.plusSwipeLeftGroup:
                    b.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.minusSwipeLeftGroup:
                    b.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
                case R.id.resetSwipeLeftGroup:
                    b.valueSwipeLeftInput.setVisibility(View.VISIBLE);
                    break;
            }
        });



        return b.getRoot();
    }
}
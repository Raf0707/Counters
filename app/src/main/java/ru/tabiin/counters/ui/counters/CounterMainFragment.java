package ru.tabiin.counters.ui.counters;

import static ru.tabiin.counters.util.UtilFragment.changeFragment;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import ru.tabiin.counters.R;
import ru.tabiin.counters.adapters.CounterAdapter;
import ru.tabiin.counters.databinding.FragmentCounterMainBinding;
import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.domain.repository.CounterRepository;
import ru.tabiin.counters.ui.main.MainFragment;
import ru.tabiin.counters.ui.settings.SettingsFragment;
import ru.tabiin.counters.ui.settings.TutorialFragment;
import ru.tabiin.counters.util.CallBack;

public class CounterMainFragment extends Fragment {

    private FragmentCounterMainBinding binding;
    private String selectMode = "Linear counter";
    private CounterViewModel counterViewModel;
    private CounterAdapter counterAdapter;
    private CounterItem counterItem;
    private int currentCount = 0;

    CounterMainFragment cmf;
    CounterBetaFragment cbf;
    GestureCounterFragment gcf;

    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR =
            new DecelerateInterpolator(2);

    private static final long GAUGE_ANIMATION_DURATION = 10;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCounterMainBinding
                .inflate(inflater, container, false);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);

        cmf = new CounterMainFragment();
        cbf = new CounterBetaFragment();
        gcf = new GestureCounterFragment();

        counterAdapter = new CounterAdapter(getContext(), null);

        binding.saveCounterEditions.setOnClickListener(view -> {
            // saveText()
            binding.counterTarget.setText(binding
                    .counterTarget
                    .getText()
                    .toString()
                    .replaceAll("[\\.\\-,\\s]+", ""));

            binding.counterTarget.setCursorVisible(false);
            binding.counterTarget.setFocusableInTouchMode(false);
            binding.counterTarget.setEnabled(false);

            binding.counterTitle.setCursorVisible(false);
            binding.counterTitle.setFocusableInTouchMode(false);
            binding.counterTitle.setEnabled(false);

            binding.counterDescription.setCursorVisible(false);
            binding.counterDescription.setFocusableInTouchMode(false);
            binding.counterDescription.setEnabled(false);

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterTarget.setText("10");
                binding.counterProgress.setMax(Integer
                        .parseInt(binding.counterTarget.getText().toString()));

                Snackbar.make(requireView(),
                                new StringBuilder().append("Вы не ввели цель. По умолчанию: ")
                                        .append("10"), Snackbar.LENGTH_LONG).show();

            } else {

                if (Integer.parseInt(binding.counterTarget.getText().toString()) <= 0) {
                    Snackbar.make(requireView(),
                                    new StringBuilder()
                                            .append("Введите число больше нуля!"),
                                    Snackbar.LENGTH_LONG)
                            .show();

                } else {

                    Snackbar.make(requireView(),
                                    new StringBuilder()
                                            .append("Цель установлена"),
                                    Snackbar.LENGTH_LONG)
                            .show();

                    binding.counterProgress.setMax(Integer
                            .parseInt(binding.counterTarget.getText().toString()));
                    binding
                            .counterProgressTv
                            .setText(MessageFormat
                                    .format("{0} / {1}",
                                            currentCount,
                                            binding
                                                    .counterTarget
                                                    .getText()
                                                    .toString()));

                }
            }

            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);

        });

        binding.editCounterBtn.setOnClickListener(view -> {

            binding.counterTarget.setCursorVisible(true);
            binding.counterTarget.setFocusableInTouchMode(true);
            binding.counterTarget.setEnabled(true);

            binding.counterTitle.setCursorVisible(true);
            binding.counterTitle.setFocusableInTouchMode(true);
            binding.counterTitle.setEnabled(true);

            binding.counterDescription.setCursorVisible(true);
            binding.counterDescription.setFocusableInTouchMode(true);
            binding.counterDescription.setEnabled(true);

            binding.counterTarget.requestFocus();

            binding.counterTarget.setSelection(
                    binding.counterTarget.getText().length()
            );

            getActivity().getWindow().setFlags(WindowManager.LayoutParams
                            .FLAG_NOT_FOCUSABLE,
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams
                                    .SOFT_INPUT_STATE_VISIBLE);

            getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            InputMethodManager inputMethodManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(binding.counterTarget,
                        InputMethodManager.SHOW_FORCED);
            }



        });

        binding.counterBtnPlus.setOnClickListener(view -> {

            binding.counterTarget.setText(binding
                    .counterTarget
                    .getText()
                    .toString()
                    .replaceAll("[\\.\\-,\\s]+", ""));

            binding.counterTarget.setCursorVisible(false);
            binding.counterTarget.setFocusableInTouchMode(false);
            binding.counterTarget.setEnabled(false);

            binding.counterTitle.setCursorVisible(false);
            binding.counterTitle.setFocusableInTouchMode(false);
            binding.counterTitle.setEnabled(false);

            binding.counterDescription.setCursorVisible(false);
            binding.counterDescription.setFocusableInTouchMode(false);
            binding.counterDescription.setEnabled(false);

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterTarget.setText("10");
                binding.counterProgress.setMax(10);

                Snackbar.make(requireView(),
                                new StringBuilder()
                                        .append("Вы не ввели цель. По умолчанию: ").append("10"),
                                Snackbar.LENGTH_LONG).show();

            } else {

                if (Integer.parseInt(binding.counterTarget.getText().toString()) <= 0) {
                    Snackbar.make(requireView(),
                                    new StringBuilder().append("Введите число больше нуля!"),
                                    Snackbar.LENGTH_LONG).show();

                } else {

                    binding.counterProgress.setMax(Integer.parseInt(binding.counterTarget
                            .getText().toString()));
                    binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                                            currentCount, binding.counterTarget
                                                .getText().toString()));

                }
            }

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterTarget.setText("10");
                binding.counterProgress.setMax(10);
                binding.counterProgressTv
                        .setText(MessageFormat.format("{0} / {1}",
                                currentCount, 10));
            }
            if (currentCount == Integer.parseInt(binding.counterTarget.getText().toString())) {
                binding.counterProgressTv
                        .setText(MessageFormat.format("{0} / {1}", binding.counterTarget
                                                .getText().toString(),
                                binding.counterTarget.getText().toString()));

                Snackbar.make(requireView(),
                                new StringBuilder().append("Цель достигнута! " +
                                                "Да вознаградит вас Аллах!"),
                                Snackbar.LENGTH_LONG).show();
            }

            if (binding.counterTarget.getText().toString() != null) {
                currentCount++;
                if (currentCount <= Integer
                        .parseInt(binding.counterTarget.getText().toString())) {
                    binding.counterProgressTv.setText(MessageFormat
                                    .format("{0} / {1}", currentCount,
                                            binding.counterTarget.getText().toString()));
                }

                ObjectAnimator animator = ObjectAnimator
                        .ofInt(binding.counterProgress,
                                "progress",
                                currentCount, currentCount);

                animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
                animator.setDuration(GAUGE_ANIMATION_DURATION);
                animator.start();


                if (binding.counterTarget.length() != 0) {

                    if (currentCount == Integer.parseInt(binding.counterTarget
                            .getText().toString())) {
                        Snackbar.make(requireView(),
                                        new StringBuilder().append("Цель достигнута! " +
                                                        "Да вознаградит вас Аллах!"),
                                        Snackbar.LENGTH_LONG).show();
                    }
                }
            } else {
                Snackbar.make(requireView(),
                                new StringBuilder().append("Введите цель!"),
                                Snackbar.LENGTH_LONG).show();
            }

            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            

        });

        binding.deleteCounterBtn.setOnClickListener(v -> {
            removeCounterAlert();
            /**
             * сделать сохранение
             */
            /*
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.delete(counterItem);

             */
            

        });

        binding.counterBtnMinus.setOnClickListener(view -> {
            //saveText();
            currentCount--;
            if (currentCount < 0) {
                currentCount = 0;
            }

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                                currentCount, 100));
            } else if (currentCount <= Integer
                    .parseInt(binding.counterTarget.getText().toString())) {
                binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                                        currentCount, binding.counterTarget.getText().toString()));
            }
            ObjectAnimator animatorMinus = ObjectAnimator
                    .ofInt(binding.counterProgress,
                            "progress",
                            currentCount, currentCount);

            animatorMinus.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
            animatorMinus.setDuration(GAUGE_ANIMATION_DURATION);
            animatorMinus.start();

            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            

        });

        binding.openCounterListBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new MainFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            
        });

        binding.openTutorialBtn.setOnClickListener(v -> {
            changeFragment(requireActivity(),
                    new TutorialFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            
        });

        binding.changeCounterModeBtn.setOnClickListener(v -> {
            changeModeCounterAlert();
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
        });

        binding.counterResetBtn.setOnClickListener(view -> {
            if (currentCount != 0) resetCounterAlert();
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            
        });

        binding.openSettingsBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new SettingsFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);
            
        });

        return binding.getRoot();
    }

    public void resetCounterAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme).setTitle("Reset")
                .setMessage("Вы уверены, что хотите обновить счетчик?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    currentCount = 0;
                    binding.counterProgressTv.setText(new StringBuilder()
                                    .append(binding.counterProgress.getProgress()).append(" / ")
                                    .append(binding.counterTarget.getText().toString())
                                    .toString());

                    ObjectAnimator animatorMaterial = ObjectAnimator
                            .ofInt(binding.counterProgress,
                                    "progress", currentCount);
                    animatorMaterial.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
                    animatorMaterial.setDuration(GAUGE_ANIMATION_DURATION);
                    animatorMaterial.start();
                })
                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel()).show();
    }

    public void changeModeCounterAlert() {

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putString("title", binding.counterTitle.getText().toString());
        bundle.putInt("target",
                Integer.parseInt(binding.counterTarget.getText().toString()));
        bundle.putInt("progress", currentCount);

        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);

        final String[] counterModes = {"Linear counter", "Circle counter", "Swipe counter"};
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Сменить режим счетчика")
                //.setMessage("Выберете новый режим")
                .setSingleChoiceItems(counterModes, 0, (dialogInterface, i) -> {
                    selectMode = counterModes[i];
                })
                .setPositiveButton("Сменить", (dialogInterface, i) -> {
                    if (selectMode == "Linear counter") {
                        dialogInterface.cancel();
                    } else if (selectMode == "Circle counter") {
                        counterViewModel.update(counterItem);
                        cbf.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerFragment, cbf).commit();
                    } else if (selectMode == "Swipe counter") {
                        counterViewModel.update(counterItem);
                        gcf.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerFragment, gcf).commit();
                    }
                    Snackbar.make(requireView(), "Вы выбрали " + selectMode,
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                })
                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    public void removeCounterAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Remove")
                .setMessage("Вы уверены, что хотите удалить счетчик? " +
                        "Чтобы удалить счетчик, вернитесь на главную страницу")
                .setPositiveButton("Удалить", (dialogInterface, i) -> {
                    counterItem.title = binding.counterTitle.getText().toString();
                    counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
                    counterItem.progress = binding.counterProgress.getProgress();
                    counterViewModel.delete(counterItem);
                    changeFragment(requireActivity(),
                            new MainFragment(),
                            R.id.containerFragment,
                            null
                    );
                })
                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onStop();
    }

    @Override
    public void onPause() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = binding.counterProgress.getProgress();
        counterViewModel.update(counterItem);
        super.onDetach();
    }

}


package ru.tabiin.counters.ui.counters.counter_progress;

import static ru.tabiin.counters.util.UtilFragment.changeFragment;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import ru.tabiin.counters.R;
import ru.tabiin.counters.adapters.CounterAdapter;
import ru.tabiin.counters.databinding.FragmentCounterMainBinding;
import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.domain.repository.CounterRepository;
import ru.tabiin.counters.ui.counters.circle_progress.CounterBetaFragment;
import ru.tabiin.counters.ui.counters.swipe_counter.GestureCounterFragment;
import ru.tabiin.counters.ui.main.MainProgressFragment;
import ru.tabiin.counters.ui.settings.MainSettingsFragment;
import ru.tabiin.counters.ui.settings.SettingsFragment;
import ru.tabiin.counters.ui.settings.TutorialFragment;
import ru.tabiin.counters.util.CallBack;

public class CounterMainFragment extends Fragment {

    // дописать счетчик в binding.currentCounterProgressTv

    private ru.tabiin.counters.databinding.FragmentCounterMainBinding binding;
    private int currentCount;
    private String defaultValue = "10";
    private int maxValue;
    private SharedPreferences sPrefs;
    private Handler handler;
    private String selectMode = "Linear counter";
    private CounterBetaFragment cbf;
    private GestureCounterFragment gcf;
    private CounterViewModel counterViewModel;
    private CounterRepository counterRepository;
    private CounterItem counterItem;
    private Bundle tranBundle;
    private FragmentManager fragmentManager;
    private MainProgressFragment mainFragment;
    private CounterAdapter counterAdapter;
    private CounterDatabase counterDatabase;
    private CounterItem counterItemTransaction;

    private CounterMainFragment counterMainFragment;

    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR =
            new DecelerateInterpolator(2);

    private static final long GAUGE_ANIMATION_DURATION = 10;

    private Vibrator vibrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCounterMainBinding
                .inflate(inflater, container, false);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);

        counterAdapter = new CounterAdapter(getContext(), null);

        counterMainFragment = new CounterMainFragment();
        mainFragment = new MainProgressFragment();

        vibrator = vibrator = (Vibrator) requireContext().getSystemService(Context.VIBRATOR_SERVICE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            int target = bundle.getInt("target");
            int progress = bundle.getInt("progress");
            int id = bundle.getInt("id");

            binding.counterTitle.setText(title);
            binding.counterTarget.setText(Integer.toString(target));
            binding.counterProgress.setMax(target);
            currentCount = progress;
            binding.counterProgress.setProgress(progress);
            binding.counterProgressTv.setText(new StringBuilder().append(progress)
                    .append(" / ").append(target));
            binding.currentCounterProgressTv.setText(String.valueOf(currentCount));

            counterItem = new CounterItem(id, title, target, progress);
            counterViewModel.update(counterItem);

        }

        cbf = new CounterBetaFragment();
        gcf = new GestureCounterFragment();

        handler = new Handler();


        binding.saveCounterEditions.setOnClickListener(view -> {
            // saveText()
            binding.counterTarget.setText(binding.counterTarget.getText().toString()
                    .replaceAll("[\\.\\-,\\s]+", ""));

            binding.counterTarget.setCursorVisible(false);
            binding.counterTarget.setFocusableInTouchMode(false);
            binding.counterTarget.setEnabled(false);

            binding.counterTitle.setCursorVisible(false);
            binding.counterTitle.setFocusableInTouchMode(false);
            binding.counterTitle.setEnabled(false);

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterTarget.setText(defaultValue);
                maxValue = Integer.parseInt(binding.counterTarget.getText().toString());

                binding.counterProgress.setMax(maxValue);

                Snackbar.make(requireView(),
                                new StringBuilder().append("Вы не ввели цель. По умолчанию: ")
                                        .append(defaultValue),
                                Snackbar.LENGTH_LONG).show();

            } else {

                if (Integer.parseInt(binding.counterTarget.getText().toString()) <= 0) {
                    Snackbar.make(requireView(), new StringBuilder()
                                            .append("Введите число больше нуля!"),
                                    Snackbar.LENGTH_LONG).show();

                } else {

                    Snackbar.make(requireView(),
                                    new StringBuilder().append("Цель установлена"),
                                    Snackbar.LENGTH_LONG).show();

                    maxValue = Integer.parseInt(binding.counterTarget.getText().toString());
                    binding.counterProgress.setMax(maxValue);
                    binding.counterProgressTv.setText(
                            MessageFormat.format("{0} / {1}", currentCount,
                                            binding.counterTarget.getText().toString()));

                }
            }

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

            binding.counterTarget.requestFocus();

            binding.counterTarget.setSelection(
                    binding.counterTarget.getText().length());

            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

            getActivity().getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context
                            .INPUT_METHOD_SERVICE);

            if (imm != null) {
                imm.showSoftInput(binding.counterTarget, InputMethodManager.SHOW_FORCED);
            }

        });

        binding.counterBtnPlus.setOnClickListener(view -> {

            vibrator.vibrate(50);

            binding.counterTarget.setText(binding.counterTarget.getText().toString()
                    .replaceAll("[\\.\\-,\\s]+", ""));

            binding.counterTarget.setCursorVisible(false);
            binding.counterTarget.setFocusableInTouchMode(false);
            binding.counterTarget.setEnabled(false);

            binding.counterTitle.setCursorVisible(false);
            binding.counterTitle.setFocusableInTouchMode(false);
            binding.counterTitle.setEnabled(false);

            if (binding.counterTarget.getText().toString().length() == 0) {
                binding.counterTarget.setText(defaultValue);
                maxValue = Integer.parseInt(binding.counterTarget.getText().toString());

                binding.counterProgress.setMax(maxValue);

                Snackbar.make(requireView(), new StringBuilder()
                                .append("Вы не ввели цель. По умолчанию: ")
                                        .append(defaultValue),
                                Snackbar.LENGTH_LONG).show();

            } else {

                if (Integer.parseInt(binding.counterTarget.getText().toString()) <= 0) {
                    Snackbar.make(requireView(),
                                    new StringBuilder().append("Введите число больше нуля!"),
                                    Snackbar.LENGTH_LONG).show();

                } else {

                    maxValue = Integer.parseInt(binding.counterTarget.getText().toString());
                    binding.counterProgress.setMax(maxValue);
                    binding.counterProgressTv.setText(
                            MessageFormat.format("{0} / {1}", currentCount,
                            binding.counterTarget.getText().toString()));

                    binding.currentCounterProgressTv.setText(String.valueOf(currentCount));
                }
            }

            if (binding.counterTarget.getText().toString().length() == 0) {
                maxValue = 100;
                binding.counterTarget.setText(Integer.toString(maxValue));
                binding.counterProgress.setMax(100);
                binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                                currentCount, 100));
                binding.currentCounterProgressTv.setText(String.valueOf(currentCount));
            }
            if (currentCount == maxValue) {
                binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                                        binding.counterTarget.getText().toString(),
                                        binding.counterTarget.getText().toString()));

                Snackbar.make(requireView(), new StringBuilder()
                                        .append("Цель достигнута! " + "Да вознаградит вас Аллах!"),
                                Snackbar.LENGTH_LONG).show();
            }

            if (binding.counterTarget.getText().toString() != null) {
                currentCount++;
                if (currentCount <= Integer.parseInt(binding.counterTarget.getText().toString())) {
                    binding.counterProgressTv.setText(MessageFormat.format("{0} / {1}",
                            currentCount, binding.counterTarget.getText().toString()));
                    binding.currentCounterProgressTv.setText(String.valueOf(currentCount));
                }

                ObjectAnimator animator = ObjectAnimator.ofInt(binding.counterProgress,
                        "progress", currentCount, currentCount);

                animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
                animator.setDuration(GAUGE_ANIMATION_DURATION);
                animator.start();

                if (binding.counterTarget.length() != 0) {
                    maxValue = Integer.parseInt(binding.counterTarget.getText().toString());

                    if (currentCount == maxValue) {
                        Snackbar.make(requireView(), new StringBuilder().append(
                                "Цель достигнута! " + "Да вознаградит вас Аллах!"),
                                        Snackbar.LENGTH_LONG).show();
                    }
                }
            } else {
                Snackbar.make(requireView(), new StringBuilder().append("Введите цель!"),
                                Snackbar.LENGTH_LONG).show();
            }

            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);


        });

        binding.deleteCounterBtn.setOnClickListener(v -> {
            removeCounterAlert();
        });

        binding.counterBtnMinus.setOnClickListener(view -> {

            vibrator.vibrate(60);
            
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

                binding.currentCounterProgressTv.setText(String.valueOf(currentCount));
            }

            ObjectAnimator animatorMinus = ObjectAnimator
                    .ofInt(binding.counterProgress, "progress",
                            currentCount, currentCount);

            animatorMinus.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
            animatorMinus.setDuration(GAUGE_ANIMATION_DURATION);
            animatorMinus.start();

            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);

        });

        binding.openCounterListBtn.setOnClickListener(view -> {
            Bundle b = new Bundle();
            FragmentManager fragmentManager = getFragmentManager();
            bundle.putString("title", binding.counterTitle.getText().toString());
            bundle.putInt("target",
                    Integer.parseInt(binding.counterTarget.getText().toString()));
            bundle.putInt("progress", currentCount);

            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);

            //mainFragment.setArguments(b);
            fragmentManager.beginTransaction().replace(R.id.containerFragment,
                    mainFragment).commit();

        });

        binding.openTutorialBtn.setOnClickListener(v -> {
            changeFragment(requireActivity(), new TutorialFragment(), R.id.containerFragment,
                    savedInstanceState
            );

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
            changeFragment(requireActivity(), new MainSettingsFragment(),
                    R.id.containerFragment, savedInstanceState
            );

            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = binding.counterProgress.getProgress();
            counterViewModel.update(counterItem);

        });

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                handler.post(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        return binding.getRoot();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            CallBack.runAllCallbacks();
            handler.postDelayed(runnable, 100);
        }
    };

    public void resetCounterAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Вы уверены, что хотите обновить счетчик?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    currentCount = 0;
                    binding.counterProgressTv.setText(new StringBuilder().append(0).append(" / ")
                                    .append(binding.counterTarget.getText().toString()).toString());

                    binding.currentCounterProgressTv.setText(String.valueOf(currentCount));

                    ObjectAnimator animatorMaterial = ObjectAnimator
                            .ofInt(binding.counterProgress,
                                    "progress", currentCount);
                    animatorMaterial
                            .setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
                    animatorMaterial
                            .setDuration(GAUGE_ANIMATION_DURATION);
                    animatorMaterial.start();
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
                .setMessage("Вы уверены, что хотите удалить счетчик? ")
                .setPositiveButton("Удалить", (dialogInterface, i) -> {
                    counterItem.title = binding.counterTitle.getText().toString();
                    counterItem.target = Integer.parseInt(binding.counterTarget
                            .getText().toString());
                    counterItem.progress = binding.counterProgress.getProgress();
                    counterViewModel.delete(counterItem);
                    changeFragment(requireActivity(),
                            new MainProgressFragment(),
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
package ru.tabiin.counters.ui.counters;

import static ru.tabiin.counters.util.UtilFragment.changeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentGestureCounterBinding;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.ui.main.MainFragment;
import ru.tabiin.counters.ui.settings.SettingsFragment;
import ru.tabiin.counters.ui.settings.TutorialFragment;
import ru.tabiin.counters.util.OnSwipeTouchListener;

public class GestureCounterFragment extends Fragment {

    private FragmentGestureCounterBinding binding;
    private Handler handler;
    private int counter = 0;
    CounterMainFragment cmf;
    CounterBetaFragment cbf;
    private String selectMode = "Circle counter";
    private CounterItem counterItem;
    private CounterViewModel counterViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGestureCounterBinding
                .inflate(inflater, container, false);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            int target = bundle.getInt("target");
            int progress = bundle.getInt("progress");
            int id = bundle.getInt("id");

            binding.counterTitle.setText(title);
            binding.counterTarget.setText(Integer.toString(target));
            counter = progress;
            binding.gestureCounter.setText(Integer.toString(counter));


            counterItem = new CounterItem(id, title, target, progress);

        }

        cmf = new CounterMainFragment();
        cbf = new CounterBetaFragment();

        handler = new Handler();

        binding.openSettingsBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new SettingsFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );

            /**
             * ?????????????? ????????????????????
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.changeCounterModeBtn.setOnClickListener(v -> {
            changeModeCounterAlert();
            /**
             * ?????????????? ????????????????????
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });


        binding.deleteCounter.setOnClickListener(view -> {
            removeCounterAlert();
            /*
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.delete(counterItem);

             */
        });

        binding.openCounterListBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new MainFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * ?????????????? ????????????????????
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.openTutorialBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new TutorialFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * ?????????????? ????????????????????
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.counterGestureView.setOnTouchListener(
                new OnSwipeTouchListener(
                        binding.counterGestureView.getContext()) {

                    @Override
                    public void onClick() {
                        counter++;
                        binding.gestureCounter.setText(Integer.toString(counter));
                        /**
                         * ?????????????? ????????????????????
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                    @Override
                    public void onSwipeDown() {
                        counter--;
                        binding.gestureCounter.setText(Integer.toString(counter));
                        /**
                         * ?????????????? ????????????????????
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                    @Override
                    public void onLongClick() {
                        if (counter != 0 &&
                                binding.gestureCounter.getText().toString() != "0")
                            onMaterialAlert();
                        /**
                         * ?????????????? ????????????????????
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                });

        Thread t = new Thread(() -> {
            try{
                TimeUnit.MILLISECONDS.sleep(100);
                handler.post(r);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        return binding.getRoot();
    }

    Runnable r = new Runnable() {
        public void run(){
            binding.gestureCounter.setText(Integer.toString(counter));
            handler.postDelayed(r,100);
        }
    };

    public void onMaterialAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("???? ??????????????, ?????? ???????????? ???????????????? ???????????????")
                .setPositiveButton("????", (dialogInterface, i) -> {
                    counter = 0;
                    binding.gestureCounter
                            .setText(new StringBuilder()
                                    .append("0"));
                })
                .setNeutralButton("????????????",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    public void changeModeCounterAlert() {

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putString("title", binding.counterTitle.getText().toString());
        bundle.putInt("target",
                Integer.parseInt(binding.counterTarget.getText().toString()));
        bundle.putInt("progress",
                Integer.parseInt(binding.gestureCounter.getText().toString()));
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);

        final String[] counterModes = {"Linear counter", "Circle counter", "Swipe counter"};
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("?????????????? ?????????? ????????????????")
                //.setMessage("???????????????? ?????????? ??????????")
                .setSingleChoiceItems(counterModes, 2, (dialogInterface, i) -> {
                    selectMode = counterModes[i];
                })
                .setPositiveButton("??????????????", (dialogInterface, i) -> {
                    if (selectMode == "Linear counter") {
                        cmf.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerFragment, cmf).commit();

                    } else if (selectMode == "Circle counter") {
                        cbf.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerFragment, cbf).commit();
                    } else if (selectMode == "Swipe counter") {
                        dialogInterface.cancel();
                    }

                    Snackbar.make(requireView(), "???? ?????????????? " + selectMode,
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                })


                .setNeutralButton("????????????",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    public void removeCounterAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Remove")
                .setMessage("???? ??????????????, ?????? ???????????? ?????????????? ??????????????? " +
                        "?????????? ?????????????? ??????????????, ?????????????????? ???? ?????????????? ????????????????")
                .setPositiveButton("??????????????", (dialogInterface, i) -> {
                    counterItem.title = binding.counterTitle.getText().toString();
                    counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
                    counterItem.progress = counter;
                    
                    changeFragment(requireActivity(),
                            new MainFragment(),
                            R.id.containerFragment,
                            null
                    );
                    counterViewModel.delete(counterItem);
                })
                .setNeutralButton("????????????",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /**
         * ?????????????? ????????????????????
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        /**
         * ?????????????? ????????????????????
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onStop();
    }

    @Override
    public void onPause() {
        /**
         * ?????????????? ????????????????????
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        /**
         * ?????????????? ????????????????????
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        /**
         * ?????????????? ????????????????????
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDetach();
    }

}

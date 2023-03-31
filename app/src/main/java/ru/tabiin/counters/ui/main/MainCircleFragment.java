package ru.tabiin.counters.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.tabiin.counters.R;
import ru.tabiin.counters.adapters.CounterAdapter;
import ru.tabiin.counters.databinding.FragmentMainBinding;
import ru.tabiin.counters.databinding.FragmentMainCircleBinding;
import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.ui.counters.circle_progress.CounterBetaFragment;
import ru.tabiin.counters.ui.counters.counter_progress.CounterMainFragment;
import ru.tabiin.counters.ui.counters.counter_progress.CounterViewModel;

public class MainCircleFragment extends Fragment implements CounterAdapter.HandleCounterClick {
    FragmentMainCircleBinding binding;
    private CounterAdapter counterAdapter;
    public static WeakReference<MainProgressFragment> ctx = null;
    private List<CounterItem> counterlist = new ArrayList<>();
    private CounterViewModel counterViewModel;
    private CounterItem counterForEdit;
    //private CounterMainFragment counterMainFragment;
    private CounterBetaFragment counterBetaFragment;
    //private GestureCounterFragment gestureCounterFragment;
    private CounterDatabase counterDatabase;
    private CounterAdapter.MyViewHolder holder;
    private boolean edit;
    private String title;
    private int target;
    private int progress;
    private int id;
    private CounterItem counterItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainCircleBinding.inflate(getLayoutInflater());

        //counterMainFragment = new CounterMainFragment();
        counterBetaFragment = new CounterBetaFragment();
        //gestureCounterFragment = new GestureCounterFragment();

        counterAdapter = new CounterAdapter(getContext(), this);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);



        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title", counterItem.title);
            target = bundle.getInt("target", counterItem.target);
            progress = bundle.getInt("progress", counterItem.progress);
            id = bundle.getInt("id");

            counterItem = new CounterItem(id, title, target, progress);
            counterViewModel.update(counterItem);
        }

        binding.fabAddCounter.setOnClickListener(v -> {
            onMaterialAlert(false);
        });

        binding.searchCounters.clearFocus();
        binding.searchCounters.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //filterList(s);
                //counterDatabase.counterDao().findByNames(binding.searchCounters)
                counterViewModel.findByNames(s);
                return true;
            }
        });

        binding.searchCounters.setOnClickListener(v -> {
            binding.searchCounters.clearFocus();
        });

        initRecycleView();
        initViewModel();
        counterViewModel.getAllCounterList();

        return binding.getRoot();
    }

    public void initRecycleView() {
        binding.recycleCounter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleCounter.setHasFixedSize(true);
        binding.recycleCounter.setAdapter(counterAdapter);
    }

    public void initViewModel() {
        counterViewModel = new ViewModelProvider(this)
                .get(CounterViewModel.class);
        counterViewModel.getCounterlistObserver()
                .observe(requireActivity(), counterItems -> {
                    if (counterItems == null) {
                        binding.noRes.setVisibility(View.VISIBLE);
                        binding.recycleCounter.setVisibility(View.GONE);
                    } else {
                        counterAdapter.setCounterList(counterItems);
                        binding.recycleCounter.setVisibility(View.VISIBLE);
                        binding.noRes.setVisibility(View.GONE);
                    }
                });
    }

    public void onMaterialAlert(boolean isForEdit) {
        MaterialAlertDialogBuilder alert =
                new MaterialAlertDialogBuilder(getContext());

        View dialogView = getLayoutInflater()
                .inflate(R.layout.create_counter_dialog, null);

        alert.setTitle("Новый счетчик");
        alert.setMessage("введите название и цель");
        alert.setCancelable(true);

        EditText counterTitle = dialogView.findViewById(R.id.counterTitle);
        EditText counterTarget = dialogView.findViewById(R.id.counterTarget);
        TextView counterProgress = dialogView.findViewById(R.id.counterProgress);

        if (isForEdit) {
            alert.setTitle("Изменить счетчик");
            counterTitle.setText(counterForEdit.title);
            counterTarget.setText(counterForEdit.target + "");
        }

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {

        });


        alert.setPositiveButton("ОК", (dialogInterface, i) -> {
            if (counterTitle.getText().toString().length() == 0) {
                counterTitle.setText(getRandomString(12));
            }

            if (counterTarget.getText().toString().length() == 0) {
                counterTarget.setText("10");
            }

            if (counterProgress.getText().toString().length() == 0) {
                counterProgress.setText("0");
            }

            if (isForEdit) {
                counterForEdit.title = counterTitle.getText().toString();
                counterForEdit.target = Integer.parseInt(counterTarget
                        .getText().toString());
                counterViewModel.update(counterForEdit);
            } else {
                counterViewModel.insert(counterTitle.getText().toString(),
                        Integer.parseInt(counterTarget.getText().toString()));
            }
        });

        alert.setView(dialogView);
        alert.show();
    }

    public static String getRandomString( int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
            }


        }
        return sb.toString();
    }

    private void filterList(String text) {
        List<CounterItem> filteredList = new ArrayList<>();
        for (CounterItem ci : counterlist) {
            if (ci.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(ci);
            }

            /*
            if (Integer.parseInt(text) >= 1 && Integer.parseInt(text) <= 99) {
                filteredList.add(names.get(Integer.parseInt(text) - 1));
            }

             */
        }

        if (filteredList.isEmpty()) {
            Snackbar.make(binding.getRoot(), "Не найдено", BaseTransientBottomBar.LENGTH_SHORT);
        } else {
            counterAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Проверяем по коду нужный результат
        if(requestCode == 120) {
            if(resultCode == Activity.RESULT_OK) {
                //Действия при возврате результата
                String updateTitle = data.getStringExtra("updateTitle");
                int updateTarget = data.getIntExtra("updateTarget", 10);
                int updateProgress = data.getIntExtra("updateProgress", progress);

                CounterItem counterItem = new CounterItem(updateTitle, updateTarget,
                        updateProgress);

                title =  updateTitle;
                target = updateTarget;
                progress = updateProgress;

                counterViewModel.update(counterItem);
            }
        }
    }


    @Override
    public void itemClick(CounterItem counterItem) {

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putString("title", counterItem.title);
        bundle.putInt("target", counterItem.target);
        bundle.putInt("progress", counterItem.progress);
        bundle.putInt("id", counterItem.id);
        counterBetaFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, counterBetaFragment).commit();

    }

    @Override
    public void deleteItem(CounterItem counterItem) {
        counterViewModel.delete(counterItem);
    }

    @Override
    public void editItem(CounterItem counterItem) {
        this.counterForEdit = counterItem;
        onMaterialAlert(true);
    }
}
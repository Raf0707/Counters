package ru.tabiin.counters.ui.counters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.ActivityCounterBinding;

public class CounterActivity extends AppCompatActivity {
    ActivityCounterBinding binding;

    CounterMainFragment cmf;
    CounterBetaFragment cbf;
    GestureCounterFragment gcf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCounterBinding
                .inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerCountersFragment,
                        new CounterMainFragment())
                .commit();
    }
}
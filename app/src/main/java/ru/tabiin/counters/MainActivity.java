package ru.tabiin.counters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.view.Window;

import com.google.android.material.color.DynamicColors;

import ru.tabiin.counters.databinding.ActivityMainBinding;
import ru.tabiin.counters.ui.about_app.AppAboutFragment;
import ru.tabiin.counters.ui.main.MainCircleFragment;
import ru.tabiin.counters.ui.main.MainProgressFragment;
import ru.tabiin.counters.ui.main.MainSwipeFragment;
import ru.tabiin.counters.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AppAboutFragment appAboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.instance.setNightMode();
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MainProgressFragment())
                    .commit();
        }

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appAboutFragment = new AppAboutFragment();

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.counter_progress:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new MainProgressFragment())
                            .commit();

                    return true;

                case R.id.counter_circle:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new MainCircleFragment())
                            .commit();

                    return true;

                case R.id.counter_swipe:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new MainSwipeFragment())
                            .commit();

                    return true;

                case R.id.about_app:

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AppAboutFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }

}
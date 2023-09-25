package ru.tabiin.counters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionManager;

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

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new MainProgressFragment())
                    .commit();
        }



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appAboutFragment = new AppAboutFragment();

        Transition fade = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        Transition slideTop = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top);
        Transition slideBottom = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        Transition slideLeft = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);
        Transition slideRight = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.counter_progress:

                    //transition2.setDuration(1000);
                    //TransitionManager.beginDelayedTransition(fragmentContainer, transition);

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
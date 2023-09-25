package ru.tabiin.counters.ui.settings;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import ru.tabiin.counters.R;
import ru.tabiin.counters.databinding.FragmentOtherSettingsBinding;

public class OtherSettingsFragment extends Fragment {
    FragmentOtherSettingsBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentOtherSettingsBinding.inflate(getLayoutInflater());

        b.clearCache.setOnClickListener(v -> {
            clearCache(getContext());
            Snackbar.make(v, "Кэш очищен", Snackbar.LENGTH_LONG).show();
        });

        return b.getRoot();
    }

    public void clearCache(Context context) {
        try {
            // Очистка внутреннего кеша приложения
            context.getCacheDir().deleteOnExit();

            // Очистка внешнего кеша приложения, если он доступен
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                context.getExternalCacheDir().deleteOnExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
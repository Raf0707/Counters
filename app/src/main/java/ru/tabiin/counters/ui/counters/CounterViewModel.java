package ru.tabiin.counters.ui.counters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.domain.repository.CounterRepository;

public class CounterViewModel extends AndroidViewModel {
    private MutableLiveData<List<CounterItem>> counterlist;
    private CounterDatabase counterDatabase;
    private CounterRepository counterRepository;

    public CounterViewModel(@NonNull Application application) {
        super(application);
        counterlist = new MutableLiveData<>();
        counterDatabase = CounterDatabase.getInstance(getApplication()
                .getApplicationContext());

        counterRepository = new CounterRepository(application);

    }

    public MutableLiveData<List<CounterItem>> getCounterlistObserver() {
        return counterlist;
    }
    public List<CounterItem> findByNames(String title) {
        return counterRepository.findByName(title);
    }
    public List<CounterItem> getAllCounterList() {
        return counterRepository.getAllData();
    }


    public void insert(CounterItem counterItem) {
        counterRepository.insertData(counterItem);
        getAllCounterList();
    }


    public void insert(String title, int target) {
        //counterRepository.insertData(counterItem);
        CounterItem counterItem = new CounterItem(title, target, 0);
        counterDatabase.counterDao().insertCounter(counterItem);
        getAllCounterList();
    }





    public void update(CounterItem counterItem) {
        counterRepository.updateData(counterItem);
        getAllCounterList();
    }

    public void delete(CounterItem counterItem) {
        counterRepository.deleteData(counterItem);
        getAllCounterList();
    }

}

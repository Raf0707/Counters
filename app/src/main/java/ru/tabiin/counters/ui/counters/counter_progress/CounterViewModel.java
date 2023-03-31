package ru.tabiin.counters.ui.counters.counter_progress;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;
import ru.tabiin.counters.domain.repository.CounterRepository;

public class CounterViewModel extends AndroidViewModel {
    private LiveData<List<CounterItem>> counterlist;
    private CounterRepository counterRepository;
    
    public MutableLiveData<CounterItem> currentCounter;

    public CounterViewModel(@NonNull Application application) {
        super(application);
        //counterlist = new LiveData<>();
        counterRepository = new CounterRepository(application);
        counterlist = counterRepository.getAllData();
    }

    public LiveData<List<CounterItem>> getCounterlistObserver() {
        return counterlist;
    }

    public MutableLiveData<CounterItem> getCurrentCounter(MutableLiveData<CounterItem> currentCounter) {
        return currentCounter;
    }
    public List<CounterItem> findByNames(String title) {
        return counterRepository.findByName(title);
    }
    public LiveData<List<CounterItem>> getAllCounterList() {
        return counterRepository.getAllData();
    }

    public void insert(CounterItem counterItem) {
        counterRepository.insertData(counterItem);
        getAllCounterList();
    }
    public void insert(String title, int target) {
        //counterRepository.insertData(counterItem);
        CounterItem counterItem = new CounterItem(title, target, 0);
        counterRepository.insertData(counterItem);
        //counterDatabase.counterDao().insertCounter(counterItem);
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

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

    public void getAllCounterList() {
        List<CounterItem> categoryList = counterDatabase.counterDao().getAllCounters();
        if(categoryList.size() > 0)
        {
            counterlist.postValue(categoryList);
        } else {
            counterlist.postValue(null);
        }
    }

    public void insert(String title, int target) {
        CounterItem counterItem = new CounterItem(title, target, 0);
        counterDatabase.counterDao().insertCounter(counterItem);
        //counterRepository.insertData(counterItem);
        getAllCounterList();
    }

    public void update(CounterItem counterItem) {
        counterDatabase.counterDao().updateCounter(counterItem);
        //counterRepository.updateData(counterItem);
        getAllCounterList();
    }

    public void delete(CounterItem counterItem) {
        counterDatabase.counterDao().deleteCounter(counterItem);
        //counterRepository.deleteData(counterItem);
        getAllCounterList();
    }

}

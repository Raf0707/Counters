package ru.tabiin.counters.domain.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import ru.tabiin.counters.domain.dao.CounterDao;
import ru.tabiin.counters.domain.database.CounterDatabase;
import ru.tabiin.counters.domain.models.CounterItem;

public class CounterRepository {
    private CounterDao counterDao;
    private List<CounterItem> counterlist;

    public CounterRepository(Application application) {
        CounterDatabase counterDatabase = CounterDatabase.getInstance(application);
        counterDao = counterDatabase.counterDao();
        counterlist = counterDao.getAllCounters();
    }

    public void insertData(CounterItem counterItem) {
        new InsertTask(counterDao).execute(counterItem);
    }
    public void updateData(CounterItem counterItem) {
        new UpdateTask(counterDao).execute(counterItem);
    }
    public void deleteData(CounterItem counterItem) {
        new DeleteTask(counterDao).execute(counterItem);
    }
    public List<CounterItem> getAllData() {
        return counterlist;
    }
    public List<CounterItem> findByName(String title) {
        return counterDao.findByNames(title);
    }

    private static class InsertTask extends AsyncTask<CounterItem, Void, Void> {
        private CounterDao сounterDao;

        public InsertTask(CounterDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(CounterItem... counterItems) {
            сounterDao.insertCounter(counterItems[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<CounterItem, Void, Void> {
        private CounterDao сounterDao;

        public UpdateTask(CounterDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(CounterItem... counterItems) {
            сounterDao.updateCounter(counterItems[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<CounterItem, Void, Void> {
        private CounterDao сounterDao;

        public DeleteTask(CounterDao сounterDao) {
            this.сounterDao = сounterDao;
        }

        @Override
        protected Void doInBackground(CounterItem... counterItems) {
            сounterDao.deleteCounter(counterItems[0]);
            return null;
        }
    }
}

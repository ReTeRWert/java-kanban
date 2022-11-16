package ru.yandex.praktikum.taskmanager.historymanager;

import ru.yandex.praktikum.taskmanager.tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private ArrayList<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if(history.size() > 10) {
            history.remove(0);
        }
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {

        return history;
    }
}

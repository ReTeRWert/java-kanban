package ru.yandex.praktikum.taskmanager.historymanager;

import ru.yandex.praktikum.taskmanager.tasks.Task;

import java.util.List;

public interface HistoryManager {

    public void add(Task task);

    public List<Task> getHistory();
}

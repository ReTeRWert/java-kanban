package ru.yandex.praktikum.taskmanager.managers;

import ru.yandex.praktikum.taskmanager.historymanager.HistoryManager;
import ru.yandex.praktikum.taskmanager.historymanager.InMemoryHistoryManager;
import ru.yandex.praktikum.taskmanager.taskmanager.InMemoryTaskManager;
import ru.yandex.praktikum.taskmanager.taskmanager.TaskManager;
import ru.yandex.praktikum.taskmanager.tasks.Epic;
import ru.yandex.praktikum.taskmanager.tasks.Subtask;
import ru.yandex.praktikum.taskmanager.tasks.Task;

public class Managers {

    private static InMemoryHistoryManager InMemoryHistoryManager = new InMemoryHistoryManager();
    private static InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(InMemoryHistoryManager);



    public static TaskManager getDefault() {
        return inMemoryTaskManager;
    }


    public static HistoryManager getDefaultHistory() {
        return InMemoryHistoryManager;
    }
}

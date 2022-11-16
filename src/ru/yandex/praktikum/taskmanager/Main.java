package ru.yandex.praktikum.taskmanager;

import ru.yandex.praktikum.taskmanager.historymanager.HistoryManager;
import ru.yandex.praktikum.taskmanager.managers.Managers;
import ru.yandex.praktikum.taskmanager.taskmanager.TaskManager;
import ru.yandex.praktikum.taskmanager.tasks.Epic;
import ru.yandex.praktikum.taskmanager.tasks.Subtask;
import ru.yandex.praktikum.taskmanager.tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

    }
}

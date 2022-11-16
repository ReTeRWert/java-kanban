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

        Epic epic1;
        Epic epic2;
        epic1 = new Epic(0, "e1", "sss");
        epic2 = new Epic(0, "e1", "sss");


        Task task1;
        Task task2;
        task1 = new Task(0, "t1", "ssss");
        task2 = new Task(0, "t2", "SSSS");

        Subtask subtask1;
        Subtask subtask2;
        Subtask subtask3;
        subtask1 = new Subtask(0, 0, "st1", "ss");
        subtask2 = new Subtask(0, 0, "st2", "ss");
        subtask3 = new Subtask(0, 0, "st3", "ss");


        inMemoryTaskManager.addTask(task1);
        inMemoryTaskManager.addTask(task2);

        inMemoryTaskManager.addSubtask(subtask1);
        inMemoryTaskManager.addSubtask(subtask2);
        inMemoryTaskManager.addSubtask(subtask3);

        inMemoryTaskManager.addEpic(epic1);
        inMemoryTaskManager.addEpic(epic2);

        epic1.addSubtaskId(subtask1.getTaskId());
        epic1.addSubtaskId(subtask2.getTaskId());
        epic2.addSubtaskId(subtask3.getTaskId());

        inMemoryTaskManager.addSubtaskToEpic(subtask1, epic1);
        inMemoryTaskManager.addSubtaskToEpic(subtask2, epic1);
        inMemoryTaskManager.addSubtaskToEpic(subtask3, epic2);

        return inMemoryTaskManager;
    }


    public static HistoryManager getDefaultHistory() {
        return InMemoryHistoryManager;
    }
}

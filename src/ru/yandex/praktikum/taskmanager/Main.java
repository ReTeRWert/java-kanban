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


        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);



        epic1.addSubtaskId(subtask1.getTaskId());
        epic1.addSubtaskId(subtask2.getTaskId());
        epic2.addSubtaskId(subtask3.getTaskId());

        taskManager.addSubtaskToEpic(subtask1, epic1);
        taskManager.addSubtaskToEpic(subtask2, epic1);
        taskManager.addSubtaskToEpic(subtask3, epic2);

    }
}

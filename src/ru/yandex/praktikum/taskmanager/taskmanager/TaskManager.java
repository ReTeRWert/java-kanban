package ru.yandex.praktikum.taskmanager.taskmanager;

import ru.yandex.praktikum.taskmanager.tasks.Epic;
import ru.yandex.praktikum.taskmanager.tasks.Subtask;
import ru.yandex.praktikum.taskmanager.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {


    public int addEpic(Epic epic);

    public int addSubtask(Subtask subtask);

    public int addTask(Task task);

    public Task getTaskById(int id);

    public Epic getEpicById(int id);

    public Subtask getSubtaskById(int id);

    public void removeAllTasks();

    public void removeAllEpics();

    public void removeAllSubtasks();

    public void removeTaskById(int id);

    public void removeEpicById(int id);

    public void removeSubtaskById(int id);

    public ArrayList<Subtask> getSubtaskOfEpic(int id);

    public void updateTask(Task task);

    public void updateSubtask(Subtask subtask);

    public void updateEpic(Epic epic);

    public void addSubtaskToEpic(Subtask subtask, Epic epic);

}

package ru.yandex.praktikum.taskmanager.tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subtaskId = new ArrayList<>();


    public Epic(int id, String taskName, String descriptionTask) {
        super(id, taskName, descriptionTask);
    }

    public ArrayList<Integer> getSubtaskId() {
        return subtaskId;
    }

    public int getSubtaskIdSize() {
        return subtaskId.size();
    }

    public void addSubtaskId(int id) {
        subtaskId.add(id);
    }

    public void removeSubtaskId(int id) {
        subtaskId.remove(id);
    }


    @Override
    public String toString() {
        return "TasksClass.Epic{" +
                "subtaskId=" + subtaskId +
                ", taskId=" + getTaskId() +
                ", taskName='" + getTaskName() + '\'' +
                ", taskDescription='" + getTaskDescription() + '\'' +
                '}';
    }
}
package ru.yandex.praktikum.taskmanager.tasks;

public class Subtask extends Task {

    private int epicId;


    public Subtask(int epicId, int id, String taskName, String descriptionTask) {
        super(id, taskName, descriptionTask);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "TasksClass.Subtask{" +
                "epicId=" + epicId +
                ", taskId=" + getTaskId() +
                ", taskName='" + getTaskName() + '\'' +
                ", taskDescription='" + getTaskDescription() + '\'' +
                '}';
    }
}

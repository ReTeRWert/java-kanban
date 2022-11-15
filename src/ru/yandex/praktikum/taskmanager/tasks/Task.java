package ru.yandex.praktikum.taskmanager.tasks;

public class Task {
    private Integer taskId;
    private String taskName;
    private String taskDescription;
    private taskStatus taskStatus;

    public Task(Integer taskId, String taskName, String taskDescription) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public taskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(taskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "TasksClass.Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}

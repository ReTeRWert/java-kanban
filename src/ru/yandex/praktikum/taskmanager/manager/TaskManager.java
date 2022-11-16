package ru.yandex.praktikum.taskmanager.manager;

import ru.yandex.praktikum.taskmanager.tasks.Epic;
import ru.yandex.praktikum.taskmanager.tasks.Subtask;
import ru.yandex.praktikum.taskmanager.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    int id = 1;
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();


    public int addEpic(Epic epic) {
        epic.setTaskId(id++);
        epicHashMap.put(epic.getTaskId(), epic);
        updateEpicStatus(epic);
        return epic.getTaskId();
    }

    public int addSubtask(Subtask subtask) {

        if (subtask.getEpicId() == 0) {
            subtask.setTaskId(id++);
            subtaskHashMap.put(subtask.getTaskId(), subtask);
            updateEpicStatus(epicHashMap.get(subtask.getEpicId()));
        } else {
            System.out.println("Подзадача не может существовать без эпика");
        }
        return subtask.getTaskId();
    }

    public int addTask(Task task) {
        task.setTaskId(id++);
        taskHashMap.put(task.getTaskId(), task);
        return task.getTaskId();
    }

    public Task getTaskById(int id) {
        if (!taskHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        return taskHashMap.get(id);
    }

    public Epic getEpicById(int id) {
        if (!epicHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        return epicHashMap.get(id);
    }

    public Subtask getSubtaskById(int id) {
        if (!subtaskHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        return subtaskHashMap.get(id);
    }

    public void removeAllTasks() {
        taskHashMap.clear();
    }

    public void removeAllEpics() {
        subtaskHashMap.clear();
        epicHashMap.clear();
    }

    public void removeAllSubtasks() {
        for(Epic epic: epicHashMap.values()){
            epic.getSubtaskId().clear();
            updateEpicStatus(epic);
        }
        subtaskHashMap.clear();
    }

    public void removeTaskById(int id) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    public void removeEpicById(int id) {
        if (epicHashMap.containsKey(id)) {
            Epic epic = epicHashMap.get(id);
            getSubtaskOfEpic(epic.getTaskId()).clear();
            epicHashMap.remove(id);
        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    public void removeSubTaskById(int id) {
        if (subtaskHashMap.containsKey(id)) {
            Subtask subtask = subtaskHashMap.get(id);
            Epic epic = epicHashMap.get(subtask.getEpicId());
            epic.removeSubtaskId(subtask.getTaskId());
            subtaskHashMap.remove(id);


        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    public ArrayList<Subtask> getSubtaskOfEpic(int id) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        Epic epic = epicHashMap.get(id);
        for (int i : epic.getSubtaskId()) {
            Subtask subtask = subtaskHashMap.get(i);
            System.out.println(subtask.toString());
            subtasks.add(subtask);
        }
        return subtasks;
    }

    public void updateTask(Task task) {
        if (!(task.getTaskId() == 0) && taskHashMap.containsKey(task.getTaskId())) {
            taskHashMap.put(task.getTaskId(), task);
        } else {
            System.out.println("Такой задачи нет.");
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (!(subtask.getTaskId() == 0) && subtaskHashMap.containsKey(subtask.getTaskId())) {
            subtaskHashMap.put(subtask.getTaskId(), subtask);
            Epic epic = epicHashMap.get(subtask.getEpicId());
            updateEpicStatus(epic);
        } else {
            System.out.println("Такой подзадачи нет.");
        }
    }

    public void updateEpic(Epic epic) {
        if (!(epic.getTaskId() == 0) && epicHashMap.containsKey(epic.getTaskId())) {
            epicHashMap.put(epic.getTaskId(), epic);
            updateEpicStatus(epic);
        } else {
            System.out.println("Такого эпика нет.");
        }
    }

    private void updateEpicStatus(Epic epic) {
        int countNew = 0;
        int countDone = 0;
        for (int id : epic.getSubtaskId()) {

            Subtask subtask = subtaskHashMap.get(id);

            if (subtask.getTaskStatus() == "NEW") {
                countNew++;
            } else if (subtask.getTaskStatus() == "DONE") {
                countDone++;
            }
        }
        if (countNew == epic.getSubtaskIdSize() || epic.getSubtaskIdSize() == 0) {
            epic.setTaskStatus("NEW");
        } else if (countDone == epic.getSubtaskIdSize()) {
            epic.setTaskStatus("DONE");
        } else {
            epic.setTaskStatus("IN_PROGRESS");
        }

    }

    public void addSubtaskToEpic(Subtask subtask, Epic epic) {
        epic.addSubtaskId(subtask.getTaskId());
        subtask.setEpicId(epic.getTaskId());
        updateEpicStatus(epic);
    }

}

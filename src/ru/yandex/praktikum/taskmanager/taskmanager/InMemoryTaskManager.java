package ru.yandex.praktikum.taskmanager.taskmanager;

import ru.yandex.praktikum.taskmanager.historymanager.InMemoryHistoryManager;
import ru.yandex.praktikum.taskmanager.tasks.Epic;
import ru.yandex.praktikum.taskmanager.tasks.Subtask;
import ru.yandex.praktikum.taskmanager.tasks.Task;
import ru.yandex.praktikum.taskmanager.tasks.TaskStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    int id = 1;
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    private HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    public InMemoryTaskManager(InMemoryHistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public int addEpic(Epic epic) {
        epic.setTaskId(id++);
        epicHashMap.put(epic.getTaskId(), epic);
        updateEpicStatus(epic);
        return epic.getTaskId();
    }

    @Override
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

    @Override
    public int addTask(Task task) {
        task.setTaskId(id++);
        taskHashMap.put(task.getTaskId(), task);
        return task.getTaskId();
    }

    @Override
    public Task getTaskById(int id) {
        if (!taskHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        historyManager.add(taskHashMap.get(id));
        return taskHashMap.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        if (!epicHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        historyManager.add(epicHashMap.get(id));
        return epicHashMap.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        if (!subtaskHashMap.containsKey(id)) {
            System.out.println("Такого идентификатора нет.");
        }
        historyManager.add(subtaskHashMap.get(id));
        return subtaskHashMap.get(id);
    }

    @Override
    public void removeAllTasks() {
        taskHashMap.clear();
    }

    @Override
    public void removeAllEpics() {
        subtaskHashMap.clear();
        epicHashMap.clear();
    }

    @Override
    public void removeAllSubtasks() {
        for (Epic epic : epicHashMap.values()) {
            epic.getSubtaskId().clear();
            updateEpicStatus(epic);
        }
        subtaskHashMap.clear();
    }

    @Override
    public void removeTaskById(int id) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    @Override
    public void removeEpicById(int id) {
        if (epicHashMap.containsKey(id)) {
            Epic epic = epicHashMap.get(id);
            for(int i: epic.getSubtaskId()) { // в прошлый раз нужно было добавить удаление сабтасков при удалении эпика
                subtaskHashMap.remove(i);
            }
            epicHashMap.remove(id);
        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    @Override
    public void removeSubtaskById(int id) {
        if (subtaskHashMap.containsKey(id)) {
            Subtask subtask = subtaskHashMap.get(id);
            Epic epic = epicHashMap.get(subtask.getEpicId());
            epic.removeSubtaskId(subtask.getTaskId());
            subtaskHashMap.remove(id);


        } else {
            System.out.println("Такого идентификатора нет.");
        }
    }

    @Override
    public List<Subtask> getSubtaskOfEpic(int id) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        Epic epic = epicHashMap.get(id);
        for (int i : epic.getSubtaskId()) {
            Subtask subtask = subtaskHashMap.get(i);
            System.out.println(subtask.toString());
            subtasks.add(subtask);
        }
        return subtasks;
    }

    @Override
    public void updateTask(Task task) {
        if (!(task.getTaskId() == 0) && taskHashMap.containsKey(task.getTaskId())) {
            taskHashMap.put(task.getTaskId(), task);
        } else {
            System.out.println("Такой задачи нет.");
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (!(subtask.getTaskId() == 0) && subtaskHashMap.containsKey(subtask.getTaskId())) {
            subtaskHashMap.put(subtask.getTaskId(), subtask);
            Epic epic = epicHashMap.get(subtask.getEpicId());
            updateEpicStatus(epic);
        } else {
            System.out.println("Такой подзадачи нет.");
        }
    }

    @Override
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
        for (int id : epic.getSubtaskId()) { // вот здесь вылетает NullPointerException, не могу понять почему

            Subtask subtask = subtaskHashMap.get(id);

            if (subtask.getTaskStatus() == TaskStatus.NEW) {
                countNew++;
            } else if (subtask.getTaskStatus() == TaskStatus.DONE) {
                countDone++;
            }
        }
        if (countNew == epic.getSubtaskIdSize() || epic.getSubtaskIdSize() == 0) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (countDone == epic.getSubtaskIdSize()) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }

    }

    @Override
    public void addSubtaskToEpic(Subtask subtask, Epic epic) {
        epic.addSubtaskId(subtask.getTaskId());
        subtask.setEpicId(epic.getTaskId());
        updateEpicStatus(epic);
    }
}

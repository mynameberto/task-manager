package com.bertasso.meisters.service;

import com.bertasso.meisters.domain.Task;
import com.bertasso.meisters.dto.TaskDTO;
import com.bertasso.meisters.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task findTaskById(Long id) throws Exception {
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new Exception("Task not found in database."));
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        this.taskRepository.save(task);
        return task;
    }

    public Task createTask(TaskDTO taskDTO) {
        Task newTask = new Task(taskDTO);
        this.saveTask(newTask);
        return newTask;
    }

    public void deleteTask(Long id) throws Exception {
        this.taskRepository.delete(findTaskById(id));
    }

}

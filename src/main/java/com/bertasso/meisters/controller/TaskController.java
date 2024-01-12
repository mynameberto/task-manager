package com.bertasso.meisters.controller;

import com.bertasso.meisters.domain.Task;
import com.bertasso.meisters.domain.TaskStatus;
import com.bertasso.meisters.dto.TaskDTO;
import com.bertasso.meisters.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO task) throws Exception {
        if (task.dtcreation().getDayOfWeek() == DayOfWeek.SATURDAY || task.dtcreation().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new Exception("Task not created. You need to be in a weekday to create a task.");
        } else {
            Task newTask = taskService.createTask(task);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable Long id) throws Exception {
        return this.taskService.findTaskById(id);

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = this.taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateProduct(@PathVariable(value = "id") Long id, @RequestBody TaskDTO taskDTO) throws Exception {
        Task task = findTaskById(id);
        if (task.getStatus() != TaskStatus.PENDING) {
            throw new Exception("Task not updated. The task status needs to be PENDING to be updated.");
        } else {
            Task searchedTask = taskService.findTaskById(id);

            searchedTask.setTitle(taskDTO.title());
            searchedTask.setDescription(taskDTO.description());
            searchedTask.setStatus(taskDTO.status());

            Task updatedTask = taskService.saveTask(task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) throws Exception {
        Task task = findTaskById(id);
        if (task.getStatus() != TaskStatus.PENDING) {
            throw new Exception("Task not deleted. The task status needs to be PENDING to be deleted.");
        } else {
            if (task.getDtcreation().isBefore(task.getDtcreation().plusDays(5))) {
                throw new Exception("Task not deleted. The task should be at least five days old.");
            }
            taskService.deleteTask(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }
}

package org.tinker.project.task_manager.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.tinker.project.task_manager.model.Task;
import org.tinker.project.task_manager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task, User currentUser) {
        task.setUser(currentUser);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();
            updatedTask.setTitle(task.getTitle()); // Set the title
            updatedTask.setDescription(task.getDescription()); // Set the description
            updatedTask.setCompleted(task.isCompleted()); // Set completed status

            // Save the updated task back to the repository
            return taskRepository.save(updatedTask);
        } else {
            // Handle the case where the task does not exist
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
}

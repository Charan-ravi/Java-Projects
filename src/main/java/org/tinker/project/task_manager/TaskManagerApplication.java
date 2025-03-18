package org.tinker.project.task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tinker.project.task_manager.model.Task;
import org.tinker.project.task_manager.service.TaskService;

import java.util.Scanner;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Is the task completed? (true/false): ");
        boolean completed = scanner.nextBoolean();

        // Create a new Task object
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setCompleted(completed);

        // Save the task to the database
        taskService.saveTask(newTask);

        System.out.println("Task saved successfully!");
    }

}

package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<TaskDTO> index() {
        var taskList = taskRepository.findAll();
        var tasks = taskList.stream().map(task -> taskMapper.map(task)).toList();

        return tasks;

    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        var taskModel =  taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var task = taskMapper.map(taskModel);
        return task;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO task) {
        var mappedTask = taskMapper.map(task);
        taskRepository.save(mappedTask);
        var taskDto = taskMapper.map(mappedTask);
        return taskDto;
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody TaskUpdateDTO data) {
        var task =  taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        var assignee = userRepository.findById(data.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));;

        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setAssignee(assignee);

        taskRepository.save(task);

        var taskResult = taskMapper.map(task);

        return taskResult;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
    // END
}
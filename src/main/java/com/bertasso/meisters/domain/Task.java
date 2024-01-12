package com.bertasso.meisters.domain;


import com.bertasso.meisters.dto.TaskDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Task")
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dtcreation;

    public Task(TaskDTO task) {
        this.title = task.title();
        this.description = task.description();
        this.status = TaskStatus.PENDING;
        this.dtcreation = task.dtcreation();
    }
}

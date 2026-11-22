package ru.hogwarts.school.dto;

import java.util.List;

public class FacultyDto {
    private Long id;
    private String name;
    private String color;
    // список студентов — тоже DTO
    private List<StudentDto> students;

    public FacultyDto() {}

    public FacultyDto(Long id, String name, String color, List<StudentDto> students) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.students = students;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public List<StudentDto> getStudents() { return students; }
    public void setStudents(List<StudentDto> students) { this.students = students; }
}

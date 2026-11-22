package ru.hogwarts.school.dto;

public class StudentDto {
    private Long id;
    private String name;
    private int age;
    // факультет отдаём тоже DTO, чтобы не тянуть всю сущность Faculty
    private FacultyDto faculty;

    public StudentDto() {}

    // конструктор для маппинга
    public StudentDto(Long id, String name, int age, FacultyDto faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    // геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public FacultyDto getFaculty() { return faculty; }
    public void setFaculty(FacultyDto faculty) { this.faculty = faculty; }
}

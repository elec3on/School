package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // POST — создать студента
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student) {
        StudentDto created = studentService.createStudent(student);
        return ResponseEntity.ok(created);
    }

    // GET — получить студента по id
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable long id) {
        StudentDto studentDto = studentService.findStudentDto(id);
        if (studentDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<FacultyDto> getStudentFaculty(@PathVariable long id) {
        StudentDto studentDto = studentService.findStudentDto(id);
        if (studentDto == null || studentDto.getFaculty() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDto.getFaculty());
    }

    // GET — все студенты
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // GET — Получить возраст из диапазона минимального и максимального
    @GetMapping("/age-between")
    public ResponseEntity<List<StudentDto>> getStudentsByAgeBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    // GET — фильтрация по возрасту
    @GetMapping("/filter")
    public ResponseEntity<List<StudentDto>> getStudentsByAge(@RequestParam int age) {
        return ResponseEntity.ok(studentService.findByAge(age));
    }

    // PUT — изменить студента
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody Student student) {
        StudentDto updated = studentService.editStudent(student);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE — удалить студента
    @DeleteMapping("{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable long id) {
        StudentDto deleted = studentService.deleteStudent(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}
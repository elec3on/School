package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public FacultyDto createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable long id) {
        FacultyDto facultyDto = facultyService.findFaculty(id);
        if (facultyDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDto);
    }

    @GetMapping
    public List<FacultyDto> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @PutMapping
    public ResponseEntity<FacultyDto> editFaculty(@RequestBody Faculty faculty) {
        FacultyDto edited = facultyService.editFaculty(faculty);
        if (edited == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FacultyDto> deleteFaculty(@PathVariable long id) {
        FacultyDto deleted = facultyService.deleteFaculty(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/filter")
    public List<FacultyDto> getFacultiesByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("/search")
    public List<FacultyDto> searchFaculty(@RequestParam String search) {
        return facultyService.findByNameOrColor(search);
    }

    @GetMapping("{id}/students")
    public List<StudentDto> getFacultyStudents(@PathVariable long id) {
        return facultyService.getStudentsByFaculty(id);
    }
}
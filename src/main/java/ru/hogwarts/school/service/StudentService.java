package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto createStudent(Student student) {
        Student saved = studentRepository.save(student);
        return toDto(saved);
    }

    public StudentDto findStudentDto(long id) {
        return toDto(studentRepository.findById(id).orElse(null));
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public StudentDto editStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return toDto(studentRepository.save(student));
        }
        return null;
    }

    public StudentDto deleteStudent(long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.deleteById(id);
        }
        return toDto(student);
    }

    public List<StudentDto> findByAge(int age) {
        return studentRepository.findByAge(age).stream()
                .map(this::toDto)
                .toList();
    }

    public List<StudentDto> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max).stream()
                .map(this::toDto)
                .toList();
    }

    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        FacultyDto facultyDto = null;
        Faculty faculty = student.getFaculty();
        if (faculty != null) {
            facultyDto = new FacultyDto(
                    faculty.getId(),
                    faculty.getName(),
                    faculty.getColor(),
                    null
            );
        }

        return new StudentDto(student.getId(), student.getName(), student.getAge(), facultyDto);
    }
}
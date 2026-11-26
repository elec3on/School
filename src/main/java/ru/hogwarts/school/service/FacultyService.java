package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDto createFaculty(Faculty faculty) {
        return toDto(facultyRepository.save(faculty));
    }

    public FacultyDto findFaculty(long id) {
        return toDto(facultyRepository.findById(id).orElse(null));
    }

    public List<FacultyDto> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public FacultyDto editFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return toDto(facultyRepository.save(faculty));
        }
        return null;
    }

    public FacultyDto deleteFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty != null) {
            facultyRepository.deleteById(id);
        }
        return toDto(faculty);
    }

    public List<FacultyDto> findByColor(String color) {
        return facultyRepository.findByColor(color).stream()
                .map(this::toDto)
                .toList();
    }

    public List<FacultyDto> findByNameOrColor(String search) {
        return facultyRepository
                .findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(search, search)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<StudentDto> getStudentsByFaculty(long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        if (faculty == null) {
            return List.of();
        }
        return faculty.getStudents().stream()
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getAge(), null))
                .toList();
    }

    private FacultyDto toDto(Faculty faculty) {
        if (faculty == null) {
            return null;
        }

        List<StudentDto> studentDtos = faculty.getStudents().stream()
                .map(s -> new StudentDto(s.getId(), s.getName(), s.getAge(), null))
                .toList();

        return new FacultyDto(faculty.getId(), faculty.getName(), faculty.getColor(), studentDtos);
    }
}
package com.api.crudangular.service;

import com.api.crudangular.exception.RecordNotFoundException;
import com.api.crudangular.model.Course;
import com.api.crudangular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Validated
@Service //serve so para saber que é service pack
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public @ResponseBody List<Course> list(){
        return courseRepository.findAll();
    }
    public Course findById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id).orElseThrow(()->new RecordNotFoundException(id));
    }
    public Course create(@Valid Course record){
        return courseRepository.save(record);
    }
    public Course update(@NotNull @Positive Long id, @Valid Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(course.getNome());
                    recordFound.setCategoria(course.getCategoria());
                    return courseRepository.save(recordFound);
                }).orElseThrow(()->new RecordNotFoundException(id));
    }
    public void delete(@PathVariable @NotNull @Positive long id){
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(id)));

        /*courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(()->new RecordNotFoundException(id));*/
    }
}

package com.api.crudangular.service;

import com.api.crudangular.dto.CourseDTO;
import com.api.crudangular.dto.mapper.CourseMapper;
import com.api.crudangular.exception.RecordNotFoundException;
import com.api.crudangular.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


@Validated
@Service //serve so para saber que é service pack
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    public @ResponseBody List<CourseDTO> list(){
        return courseRepository.findAll()
                .stream()
                //TODO .map(course -> courseMapper.toDTO(course)) seria a mesma coisa do que abaixo
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(()->new RecordNotFoundException(id));
    }
    public CourseDTO create(@Valid @NotNull CourseDTO record){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(record)));
    }
    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(course.nome());
                    recordFound.setCategoria(course.categoria());
                    return courseRepository.save(recordFound);
                }).map(courseMapper::toDTO)
                .orElseThrow(()->new RecordNotFoundException(id));
    }
    public void delete(@PathVariable @NotNull @Positive long id){
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(id)));

        //TODO courseRepository.findById(id).map(recordFound -> {courseRepository.deleteById(id);return true;}).orElseThrow(()->new RecordNotFoundException(id));*/
    }
}

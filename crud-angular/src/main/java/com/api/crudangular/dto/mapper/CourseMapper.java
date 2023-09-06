package com.api.crudangular.dto.mapper;

import com.api.crudangular.dto.CourseDTO;
import com.api.crudangular.model.Course;
import com.api.crudangular.service.CourseService;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course){
        if(course == null){
            return null;
        }
        return new CourseDTO(course.getId(), course.getNome(), course.getCategoria());
    }
    public Course toEntity(CourseDTO courseDTO){
        if(courseDTO == null){
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null){
            course.setId(courseDTO.id());
        }
        course.setNome(courseDTO.nome());
        course.setCategoria(courseDTO.categoria());
        course.setStatus("Ativo");
        return course;
    }
}

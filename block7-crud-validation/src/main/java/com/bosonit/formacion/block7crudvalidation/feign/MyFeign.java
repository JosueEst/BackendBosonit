package com.bosonit.formacion.block7crudvalidation.feign;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.professor.ProfessorOutputDto;
import feign.RequestLine;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "myFeign", url = "http://localhost:8080/")
public interface MyFeign {
    // @RequestMapping (method = RequestMethod.GET, value = "/profesor/{id}")
    @RequestLine("GET ")
    String getProfessor() throws EntityNotFoundException;
}

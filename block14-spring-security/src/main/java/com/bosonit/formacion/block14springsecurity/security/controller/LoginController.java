package com.bosonit.formacion.block14springsecurity.security.controller;

import com.bosonit.formacion.block14springsecurity.controller.Dtos.CustomErrorOutputDto;
import com.bosonit.formacion.block14springsecurity.domain.Person;
import com.bosonit.formacion.block14springsecurity.repository.PersonRepository;
import com.bosonit.formacion.block14springsecurity.security.config.WebSecurityConfig;
import com.bosonit.formacion.block14springsecurity.security.utils.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    PersonRepository personaRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    WebSecurityConfig webSecurityConfig=new WebSecurityConfig();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String usuario = requestMap.get("usuario");
        String password = requestMap.get("password");

        Person person = personaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new EntityNotFoundException ("Usuario no encontrado."));

        if (!person.getPassword().equals(password)) {
            throw new EntityNotFoundException("Password no válida.");
        }

        String role = Boolean.TRUE.equals(person.getAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(jwtTokenUtil.generateToken(usuario, role), HttpStatus.OK);
    }
    //Method for catching 'EntityNotFoundException' exceptions
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorOutputDto> handleEntityNotFoundException (EntityNotFoundException e){
        CustomErrorOutputDto customErrorOutputDto = new CustomErrorOutputDto();
        customErrorOutputDto.setTimestamp(new Date());
        customErrorOutputDto.setHttpCode(HttpStatus.NOT_FOUND.value());
        customErrorOutputDto.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorOutputDto);
    }
}

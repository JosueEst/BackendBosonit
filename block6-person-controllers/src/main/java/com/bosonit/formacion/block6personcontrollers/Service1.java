package com.bosonit.formacion.block6personcontrollers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class Service1 {

    Person person  = new Person();
    City city = new City();
    List<City> cityList =  new ArrayList<>();

    public Person returnPerson(){

        return person;
    }

    public City returnCity(){

        return  city;
    }

    public List<City> returnCityList(){
        return  cityList;
    }
}

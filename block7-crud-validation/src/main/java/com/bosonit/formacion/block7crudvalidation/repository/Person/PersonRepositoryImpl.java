package com.bosonit.formacion.block7crudvalidation.repository.Person;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.PersonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<PersonOutputDto> getData(HashMap<String, Object> conditions) {
        //Configuracion de la query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((clave, valor) -> {
            switch (clave) {
                case "usuario":
                    predicates.add(criteriaBuilder.equal(root.get(clave), (String) valor));
                    break;
                case "name":
                    predicates.add(criteriaBuilder.equal(root.get(clave), (String) valor));
                    break;
                case "surname":
                    predicates.add(criteriaBuilder.equal(root.get(clave), (String) valor));
                    break;
                case "createdDate":
                    String dateCondition = (String) conditions.get("dateCondition");
                    if(dateCondition ==null) dateCondition = "equal";
                    switch (dateCondition) {
                        case "greater":
                            predicates.add(criteriaBuilder.greaterThan(root.get(clave), (Date) valor));
                            break;
                        case "less":
                            predicates.add(criteriaBuilder.lessThan(root.get(clave), (Date) valor));
                            break;
                        case "equal":
                            predicates.add(criteriaBuilder.equal(root.get(clave), (Date) valor));
                            break;
                    }
                    break;
                case "order":
                    String orderCondition = (String) conditions.get("orderCondition");
                    switch (orderCondition){
                        case "usuarioOrder":
                            Expression <Object> sort = root.get("usuario");
                            // to order de result list by "usuario"
                            criteriaQuery.orderBy(criteriaBuilder.asc(sort));

                        case "nameOrder":
                            Expression <Object> sort2 = root.get("name");
                            // to order de result list by "name"
                            criteriaQuery.orderBy(criteriaBuilder.asc(sort2));
                    }
            }
        });
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(criteriaQuery)
                .getResultList()
                .stream()
                .map(PersonMapper.INSTANCE::personToPersonOutputDto)
                .toList();
    }
}

package com.bosonit.formacion.block7crudvalidation.repository.Person;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.PersonMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    //Method to do a query in runtime
    public List<PersonOutputDto> getData(HashMap<String, Object> conditions) {
        //Configuracion de la query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        int pageNumber = Integer.valueOf(conditions.get("pageNumber").toString());
        int pageSize = Integer.valueOf(conditions.get("pageSize").toString());
        int setPage = (pageNumber) * pageSize; // To set the page to start (the first result)

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((clave, valor) -> {
            switch (clave) {
                case "usuario":
                    predicates.add(criteriaBuilder.like(root.get(clave),"%"+ (String) valor+ "%"));
                    break;
                case "name":
                    predicates.add(criteriaBuilder.like(root.get(clave), "%"+ (String) valor +"%"));
                    break;
                case "surname":
                    predicates.add(criteriaBuilder.equal(root.get(clave), "%"+ (String) valor +"%"));
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
                            // to order de result list by "usuario" (ascending order)
                            criteriaQuery.orderBy(criteriaBuilder.asc(sort));
                            break;
                        case "nameOrder":
                            Expression <Object> sort2 = root.get("name");
                            // to order de result list by "name"
                            criteriaQuery.orderBy(criteriaBuilder.asc(sort2));
                            break;
                    }
               break;
            }
        });
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(criteriaQuery)
                .setMaxResults(pageSize)
                .setFirstResult(setPage)
                .getResultList()
                .stream()
                .map(PersonMapper.INSTANCE::personToPersonOutputDto)
                .toList();
    }
}

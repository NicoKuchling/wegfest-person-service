package com.nicokuchling.wegfest.person_service.services;

import com.nicokuchling.wegfest.api.core.person.Person;
import com.nicokuchling.wegfest.api.core.person.PersonService;
import com.nicokuchling.wegfest.api.exceptions.InvalidInputException;
import com.nicokuchling.wegfest.api.exceptions.NotFoundException;
import com.nicokuchling.wegfest.shared.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PersonServiceImpl implements PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final ServiceUtil serviceUtil;

    @Autowired
    public PersonServiceImpl(ServiceUtil serviceUtil) { this.serviceUtil = serviceUtil; }


    @Override
    public Person getPersonById(int personId) {
        LOG.debug("/person return the found person for personId={}", personId);

        if(personId < 1) {
            throw new InvalidInputException("Invalid personId: " + personId);
        }

        if(personId == 13) {
            throw new NotFoundException("No person found for personId: " + personId);
        }

        return new Person(
                personId,
                "firstName-" + personId,
                "lastName-" + personId,
                LocalDate.now(),
                Person.Gender.MALE,
                serviceUtil.getServiceAddress());
    }
}

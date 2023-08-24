package com.reviews.Directory.service;


import com.reviews.Directory.entity.Business;
import com.reviews.Directory.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;  // The service communicates with Repo. so Repo needs to be initialized.

// CREATE - POST
    public Business savePerson(Business business) {
        return repository.save(business);
    }

    public List<Business>  savePeople(List<Business> businesses) {
        return repository.saveAll(businesses);
    }

// READ - GET
    public List<Business> getPeople() {
        return repository.findAll();
    }

    public Business getPersonById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Business getPersonByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
        // This is a custom method. It has to be made in repository.
    }



// DELETE
    public String deletePerson(long id){
        repository.deleteAllById(Collections.singleton(id));
        // deleteAllById(id)
        return "Person removed "+ id;
    }

// UPDATE - PUT
    public Business updatePerson(Business business){
        Business existingBusiness = repository.findById(business.getId()).orElse(null);
        // There is no UPDATE method in Spring Data JPA
        existingBusiness.setFirstName(business.getFirstName());
        existingBusiness.setLastName(business.getLastName());
        existingBusiness.setEmail(business.getEmail());
        existingBusiness.setMobile(business.getMobile());
        existingBusiness.setPhysicalAddress(business.getPhysicalAddress());
        existingBusiness.setLocation(business.getLocation());
        existingBusiness.setCategory(business.getCategory());
        existingBusiness.setTinNumber(business.getTinNumber());
        existingBusiness.setLink(business.getLink());
//        existingPerson.setCreatedAt(person.getCreatedAt());
        existingBusiness.setUpdatedAt( new Date() );

        return repository.save(existingBusiness);

}


}

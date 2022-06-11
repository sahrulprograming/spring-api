package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplexService {

    @Autowired
    private ComplexRepository complexRepository;

    public void createComplex (Complex complex){

        complexRepository.save(complex);
    }

    public List<Complex> complexList (){
        return complexRepository.findAll();
    }


}

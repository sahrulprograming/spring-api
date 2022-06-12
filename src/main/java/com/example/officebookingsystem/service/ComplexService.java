<<<<<<< HEAD
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
=======
//package com.example.officebookingsystem.service;
//
//import com.example.officebookingsystem.domain.entity.Complex;
//import com.example.officebookingsystem.domain.repository.ComplexRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ComplexService {
//
//    @Autowired
//    private ComplexRepository complexRepository;
//
//    public void creatComplex (Complex complex){
//
//        complexRepository.save(complex);
//    }
//
//    public List<Complex> complexList (){
//        return complexRepository.findAll();
//    }
//
//    public List<Complex>  complexName(){
//        return complexRepository;
//    }
//}
>>>>>>> 3d11ab2fbbd9c757f0aa11ec800da6146a32a92d

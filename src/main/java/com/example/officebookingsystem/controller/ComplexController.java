package com.example.officebookingsystem.controller;
import com.example.officebookingsystem.domain.dto.request.ComplexCreateRequest;
import com.example.officebookingsystem.domain.dto.response.ComplexResponse;
import com.example.officebookingsystem.domain.dto.response.ResponseData;
import com.example.officebookingsystem.service.ComplexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/page/admin")
public class ComplexController {

    @Autowired
    private ComplexService complexService;

    @PostMapping("/complex/create")
    @ApiOperation(value = "Create Complex", notes = "Endpoint for Creating Complex by City and District")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData<ComplexCreateRequest>> createComplex(@RequestBody ComplexCreateRequest complexCreateRequest){
        return complexService.createComplex(complexCreateRequest);
    }
    @GetMapping("complex/findAll")
    @ApiOperation(value = "List Complex", notes = "Endpoint for Listng all Complex by City and District")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData<List<ComplexResponse>>> listComplex(){
        return complexService.listComplex();
    }
}

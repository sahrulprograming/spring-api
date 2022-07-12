package com.example.officebookingsystem.controller;
import com.example.officebookingsystem.domain.dto.request.ComplexCreateRequest;
import com.example.officebookingsystem.domain.dto.response.ComplexResponse;
import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.service.ComplexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/page/admin")
@CrossOrigin(origins = "*", maxAge = 3600L)
@Api(tags = "Admin Managing Complex API", description = "Complexes")
public class ComplexController {

    @Autowired
    private ComplexService complexService;

    @PostMapping("/complex/create")
    @ApiOperation(value = "Create Complex", notes = "Endpoint for Creating Complex by City and District")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createComplex(@RequestBody ComplexCreateRequest complexCreateRequest){
        return complexService.createComplex(complexCreateRequest);
    }
    @GetMapping("complex/findAll")
    @ApiOperation(value = "List Complex", notes = "Endpoint for Listng all Complex by City and District")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ComplexResponse>> listComplex(){
        return complexService.listComplex();
    }

    @DeleteMapping("complex/delete/{id}")
    @ApiOperation(value = "Delete Complex", notes = "Endpoint for delete complex by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteComplex(@PathVariable("id") Long id){
        return complexService.deleteById(id);
    }

    @PutMapping("complex/update/{id}")
    @ApiOperation(value = "Update Complex ", notes = "Endpoint for update complex by id ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Complex> updateComplex(@PathVariable("id") Long id, @Valid @RequestBody ComplexCreateRequest complexCreateRequest){
        return complexService.update(id,complexCreateRequest);
    }
}

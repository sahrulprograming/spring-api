package com.example.officebookingsystem.domain.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.officebookingsystem.domain.dto.response.ResponseData;

@Component
public class ResponseHelper {
    public <T> ResponseData<T> response(Integer status, String message, T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(status);
        List<String> messages = new ArrayList<>();
        messages.add(message);
        responseData.setMessages(messages);
        responseData.setData(data);
        return responseData;
    }

    public <T> ResponseData<T> validationError(Errors errors){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(400);
        List<String> messages = new ArrayList<>();
        errors.getAllErrors().forEach(error -> messages.add(error.getDefaultMessage()));
        responseData.setMessages(messages);
        return responseData;
    }
}

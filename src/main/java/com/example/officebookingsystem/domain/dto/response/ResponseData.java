package com.example.officebookingsystem.domain.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseData<T> {
    private Integer status;
    private List<String> messages = new ArrayList<>();
    private T data;
}

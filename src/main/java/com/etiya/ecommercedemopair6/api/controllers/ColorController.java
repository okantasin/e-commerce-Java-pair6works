package com.etiya.ecommercedemopair6.api.controllers;


import com.etiya.ecommercedemopair6.business.abstracts.ColorService;
import com.etiya.ecommercedemopair6.business.constants.Paths;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.Color.CreateColorRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.color.CreateColorResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.color.GetAllColorResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.color.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Paths.apiPrefix+"colors")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/getById")

    public GetColorResponse getById(@RequestParam int id){
        return colorService.getById(id);
    }

    @GetMapping("/getAll")

    public List<GetAllColorResponse> getAllColors(){
        return colorService.getAllColor();
    }

    @PostMapping("/add")

    public ResponseEntity<CreateColorResponse> createColor(@RequestBody @Valid CreateColorRequest createColorRequest){
        return new ResponseEntity<>(colorService.createColor(createColorRequest), HttpStatus.CREATED);
    }
}
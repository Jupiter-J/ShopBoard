package com.example.shopboard.controller;

import com.example.shopboard.dto.AreaDto;
import com.example.shopboard.service.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("area")
public class AreaController {
    private final AreaService areaService;


    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<AreaDto> createArea(@RequestBody AreaDto areaDto){
        return ResponseEntity.ok(this.areaService.createArea(areaDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<AreaDto> readArea(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.areaService.readArea(id));
    }


    @GetMapping
    public ResponseEntity<Collection<AreaDto>> readAreaAll(){
        return ResponseEntity.ok(this.areaService.readAllArea());
    }



}

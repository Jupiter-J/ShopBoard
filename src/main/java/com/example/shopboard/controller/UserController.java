package com.example.shopboard.controller;

import com.example.shopboard.dto.UserDto;
import com.example.shopboard.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userService.createUser(userDto));
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        return ResponseEntity.ok(this.userService.readUserAll());
    }


    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id,
                                        @RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userService.readUser(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @RequestBody UserDto userDto){
        this.userService.updateUser(id, userDto);
                            //왜 ok사인을 보내지 않고 noContent인가
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }




}

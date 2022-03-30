package com.example.shopboard.service;

import com.example.shopboard.dto.UserDto;
import com.example.shopboard.entity.AreaEntity;
import com.example.shopboard.entity.UserEntity;
import com.example.shopboard.repository.AreaRepository;
import com.example.shopboard.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AreaRepository areaRepository;

    public UserService(UserRepository userRepository, AreaRepository areaRepository) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
    }

    public UserDto createUser(UserDto userDto){
        //
        Optional<AreaEntity> areaEntityOptional = this.areaRepository.findById(userDto.getAreaId());
        if (areaEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //AreaEntity에 만들어둔 fk에 값을 저장
        AreaEntity residence = areaEntityOptional.get();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setIsShopOwner(userDto.getIsShopOwner());
        userEntity.setResidence(residence);
        userEntity = this.userRepository.save(userEntity);
        return new UserDto(userEntity);

    }

    public UserDto readUser(Long id){
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new UserDto(userEntityOptional.get());
    }

    public List<UserDto> readUserAll(){
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(
                userEntity -> userDtoList.isEmpty());
        return userDtoList;
    }

    public void updateUser(Long id, UserDto dto){
        // 1. 매개변수로 들어온 id와 repository에서 id를 비교해 가져온다
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // 2. 가져온 값을 entity에 저장
        UserEntity userEntity = userEntityOptional.get();

        // 3. userEntityPw변경 (dto의 pw가 null이면 entity로 가져오고 아니면 dto의 값으로 저장)
        userEntity.setPassword(
                dto.getPassword() == null? userEntity.getPassword() :  dto.getPassword()
        );

        // 4. userEntity ShopOwner변경 (dto에 들어온값이 null이면 Enttiy반환, 아니면 들어온값 저장)
        userEntity.setIsShopOwner(
                dto.getIsShopOwner() == null?
                        userEntity.getIsShopOwner(): dto.getIsShopOwner());

        // 5.
        Optional<AreaEntity> newArea = this.areaRepository.findById(
                //getId는 area의 pk id?
                dto.getId() == null ? userEntity.getResidence().getId() : dto.getAreaId());

                //??
        newArea.ifPresent(userEntity::setResidence);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
                                //boolean타입으로 확인
        if (!this.userRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.userRepository.deleteById(id);

    }




}

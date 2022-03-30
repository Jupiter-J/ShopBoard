package com.example.shopboard.service;

import com.example.shopboard.dto.AreaDto;
import com.example.shopboard.entity.AreaEntity;
import com.example.shopboard.repository.AreaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    private final AreaRepository areaRepository;


    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }


    public AreaDto createArea(AreaDto areaDto){
        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setRegionMajor(areaDto.getRegionMajor());
        areaEntity.setRegionMinor(areaDto.getRegionMinor());
        areaEntity.setRegionPatch(areaDto.getRegionPatch());
        areaEntity.setLatitude(areaDto.getLatitude());
        areaEntity.setLongitude(areaDto.getLongitude());
        areaEntity = areaRepository.save(areaEntity);

        return new AreaDto(areaEntity);
    }

    public AreaDto readArea(Long id){
        //NPE를 방지
        Optional<AreaEntity> areaEntityOptional = areaRepository.findById(id);
        if (areaEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new AreaDto(areaEntityOptional.get());
    }

    public List<AreaDto> readAllArea(){
        List<AreaDto> areaDtoList = new ArrayList<>();
        areaRepository.findAll().forEach(
                areaEntity -> areaDtoList.add(
                        new AreaDto(areaEntity)));
        return areaDtoList;
    }



}

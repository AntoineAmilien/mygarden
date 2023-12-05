package com.mygarden.mygarden.service;

import com.mygarden.mygarden.dto.CreateFruitDto;
import com.mygarden.mygarden.dto.GetFruitDto;
import com.mygarden.mygarden.dto.UpdateFruitDto;
import com.mygarden.mygarden.entity.Fruit;
import com.mygarden.mygarden.exception.NotFoundException;
import com.mygarden.mygarden.repository.FruitRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FruitService {

    private final FruitRepository fruitRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FruitService(FruitRepository fruitRepository, ModelMapper modelMapper) {
        this.fruitRepository = fruitRepository;
        this.modelMapper = modelMapper;
    }

    public List<GetFruitDto> getAllFruits() {
        List<Fruit> fruits = fruitRepository.findAll();
        return fruits.stream()
                .map(fruit -> modelMapper.map(fruit, GetFruitDto.class))
                .collect(Collectors.toList());
    }

    public GetFruitDto getFruitById(Long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit not found with id: " + id));
        return modelMapper.map(fruit, GetFruitDto.class);
    }

    public GetFruitDto createFruit(CreateFruitDto createFruitDto) {
        Fruit fruit = modelMapper.map(createFruitDto, Fruit.class);
        Fruit createdFruit = fruitRepository.save(fruit);
        return modelMapper.map(createdFruit, GetFruitDto.class);
    }

    public GetFruitDto updateFruit(Long id, UpdateFruitDto updatedFruitDto) {
        Fruit existingFruit = fruitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit not found with id: " + id));
        modelMapper.map(updatedFruitDto, existingFruit);
        Fruit savedFruit = fruitRepository.save(existingFruit);
        return modelMapper.map(savedFruit, GetFruitDto.class);
    }

    public void deleteFruit(Long id) {
        if(!fruitRepository.existsById(id)){
            throw new NotFoundException("Fruit not found with id: " + id);
        }
        fruitRepository.deleteById(id);
    }
}

package com.mygarden.mygarden.controller;

import com.mygarden.mygarden.dto.CreateFruitDto;
import com.mygarden.mygarden.dto.GetFruitDto;
import com.mygarden.mygarden.dto.UpdateFruitDto;
import com.mygarden.mygarden.service.FruitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/fruit")
@AllArgsConstructor
public class FruitController {

    private final FruitService fruitService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/v1", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFruitDto> createFruit(@Valid @RequestBody CreateFruitDto createFruitDto) {
        GetFruitDto responseDto = fruitService.createFruit(createFruitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        // throw new NotFoundException("La ressource spécifiée n'a pas été trouvée.");
        //throw new MygardenException("Une erreur spécifique à mon application s'est produite.");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<GetFruitDto> getFruit(@PathVariable Long id) {
        GetFruitDto fruitDto = fruitService.getFruitById(id);
        return ResponseEntity.ok(fruitDto);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/v1")
    public ResponseEntity<List<GetFruitDto>> getAllFruits() {
        List<GetFruitDto> fruitDtos = fruitService.getAllFruits();
        return ResponseEntity.ok(fruitDtos);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value = "/v1/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<GetFruitDto> updateFruit(@PathVariable Long id, @Valid @RequestBody UpdateFruitDto updatedFruitDto) {
        GetFruitDto updatedFruit = fruitService.updateFruit(id, updatedFruitDto);
        return ResponseEntity.ok(updatedFruit);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }
}

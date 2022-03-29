package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.FraisWithIdDto;
import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.service.FraisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;

@RestController
@CrossOrigin
@RequestMapping("/frais")
public record FraisController(FraisService fraisService) {
    @GetMapping()
    public ResponseEntity<Iterable<FraisDto>> getAllFrais(){
        return ResponseEntity.ok().body(fraisService.findAll());
    }

    @GetMapping("/all-with-id")
    public ResponseEntity<Iterable<FraisWithIdDto>> getAllWithId(){
        return ResponseEntity.ok().body(fraisService.findAllWithId());
    }

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FraisDto> createFrais(@RequestBody FraisDto fraisDto){
        return ResponseEntity.ok(fraisService.save(fraisDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FraisDto> getFraisById(@PathVariable int id){
        return ResponseEntity.ok(fraisService.getById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteTagById(@PathVariable int id) {
        fraisService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<FraisDto> updateFraisyId(@PathVariable int id, @RequestBody FraisDto fraisDto){
        return ResponseEntity.ok(fraisService.update(id, fraisDto));
    }
}
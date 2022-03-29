package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.TagWithIdDto;
import fr.insys.commerce.dto.TagDto;
import fr.insys.commerce.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tag")
public record TagController(TagService tagService) {
    //TODO : retourner des ResponseEntity
    @GetMapping()
    public ResponseEntity<Iterable<TagDto>> getAllTags(){
        return ResponseEntity.ok().body(tagService.findAll());
    }
    
    @GetMapping("/all-with-id")
    public ResponseEntity<Iterable<TagWithIdDto>> getAllWithId(){
        return ResponseEntity.ok().body(tagService.findAllWithId());

    }
    
    @GetMapping("/type/{label}")
    public ResponseEntity<Iterable<TagDto>> getAllByTypeProduit(@PathVariable String label) {
    	return ResponseEntity.ok().body(tagService.findAllByTypeProduit(label));

    }

    @PostMapping("/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TagDto> createTag(@RequestBody  TagDto tagDto){
        return ResponseEntity.ok().body(tagService.save(tagDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable int id){
        return ResponseEntity.ok().body(tagService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deleteTagById(@PathVariable int id) {
        tagService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TagDto> updateTagById(@PathVariable int id, @RequestBody TagDto tagDto){
        return ResponseEntity.ok().body(tagService.update(id, tagDto));
    }
}
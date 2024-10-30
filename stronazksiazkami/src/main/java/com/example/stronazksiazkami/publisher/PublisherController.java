package com.example.stronazksiazkami.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/publishers")
public class PublisherController {
    private final PublisherServiceImpl publishersService;

    @Autowired
    public PublisherController(PublisherServiceImpl publishersService) {
        this.publishersService = publishersService;
    }

    @GetMapping
    public List<Publisher> getPublishers()
    {
        return publishersService.getPublishers();
    }

    @PostMapping
    public ResponseEntity<Publisher> registerNewPublishers(@RequestBody Publisher publishers)
    {
        Publisher savedPublishers = publishersService.addNewPublishers(publishers);
        return ResponseEntity.ok(savedPublishers);
    }

    @DeleteMapping(path = "{publishersId}")
    public void deletePublishers(@PathVariable("publishersId") Integer publishersId)
    {
        publishersService.deletePublishers(publishersId);
    }

    @PutMapping(path = "/{publishersId}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable("publishersId") Integer publishersId,
                                                     @RequestBody Publisher updatedPublisher)
    {
        publishersService.updatePublisher(publishersId, updatedPublisher);
        return ResponseEntity.ok(updatedPublisher);
    }
}

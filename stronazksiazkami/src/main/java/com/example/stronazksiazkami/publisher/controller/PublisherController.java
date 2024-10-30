package com.example.stronazksiazkami.publisher.controller;

import com.example.stronazksiazkami.publisher.model.Publisher;
import com.example.stronazksiazkami.publisher.service.PublisherServiceImpl;
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
    public void updatePublisher(@PathVariable("spublihersId") Integer publishersId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String phone) {
        publishersService.updatePublisher(publishersId, name, phone);
    }
}

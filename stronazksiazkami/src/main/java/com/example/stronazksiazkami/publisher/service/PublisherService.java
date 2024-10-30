package com.example.stronazksiazkami.publisher.service;

import com.example.stronazksiazkami.publisher.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getPublishers();

    Publisher addNewPublishers(Publisher publishers);
    void deletePublishers(Integer publishersId);
    void updatePublisher(Integer publishersId, String name, String phone);

}

package com.example.stronazksiazkami.publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getPublishers();

    Publisher addNewPublisher(Publisher publishers);

    void deletePublisher(Integer publishersId);

    void updatePublisher(Integer publishersId, Publisher updatedPublisher);

}

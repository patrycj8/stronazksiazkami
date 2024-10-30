package com.example.stronazksiazkami.publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> getPublishers();

    Publisher addNewPublishers(Publisher publishers);
    void deletePublishers(Integer publishersId);
    void updatePublisher(Integer publishersId, Publisher updatedPublisher);

}

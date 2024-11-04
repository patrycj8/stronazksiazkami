package com.example.stronazksiazkami.publisher;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl {

    private final PublisherRepository publishersRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publishersRepository) {
        this.publishersRepository = publishersRepository;
    }
    public List<Publisher> getPublishers()
    {
        return publishersRepository.findAll();
    }

    public Publisher addNewPublishers(Publisher publishers) {
        Optional<Publisher> publishersOptional = publishersRepository.findPublishersByName(publishers.getName());
        if (publishersOptional.isPresent()) {
            throw new IllegalArgumentException("name exists");
        }
        Publisher savedPublishers = publishersRepository.save(publishers);
        return savedPublishers;
    }

    public void deletePublishers(Integer publishersId) {
        boolean exists = publishersRepository.existsById(publishersId);
        if (!exists)
        {
            throw new IllegalArgumentException("publisher with id " + publishersId + " does not exist");

        }
        publishersRepository.deleteById(publishersId);
    }

    @Transactional
    public void updatePublisher(Integer publishersId, Publisher updatedPublisher) {
        Publisher publisher = publishersRepository.findById(publishersId)
                .orElseThrow(() -> new IllegalArgumentException("Publisher with id " + publishersId + " does not exist"));

        if (updatedPublisher.getName() != null) {
            publisher.setName(updatedPublisher.getName());
        }
        if (updatedPublisher.getPhone() != null && !updatedPublisher.getPhone().isBlank()) {
            Optional<Publisher> publisherOptional = publishersRepository.findPublishersByPhone(updatedPublisher.getPhone());
            if (publisherOptional.isPresent() && publisherOptional.get().getId() != (publisher.getId())) {
                throw new IllegalArgumentException("W");
            }
            publisher.setPhone(updatedPublisher.getPhone());
        }
        if (updatedPublisher.getEmail() != null) {publisher.setEmail(updatedPublisher.getEmail());}
        if (updatedPublisher.getAddress() != null) {publisher.setAddress(updatedPublisher.getAddress());}
        if (updatedPublisher.getWebsite() != null) {publisher.setWebsite(updatedPublisher.getWebsite());}

        publishersRepository.save(publisher);
    }
}

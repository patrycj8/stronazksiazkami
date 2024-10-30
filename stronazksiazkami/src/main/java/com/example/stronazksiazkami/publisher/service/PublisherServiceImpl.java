package com.example.stronazksiazkami.publisher.service;

import com.example.stronazksiazkami.publisher.model.Publisher;
import com.example.stronazksiazkami.publisher.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public void updatePublisher(Integer publishersId, String name, String phone) {
        Publisher publishers = publishersRepository.findById(publishersId).orElseThrow(() -> new IllegalArgumentException("users with id " + publishersId + " does not exist"));
        if (name != null && name.length() >0 && !Objects.equals(publishers.getName(), name)) {
            publishers.setName(name);
        }
        if (phone != null && !phone.isBlank() && !Objects.equals(publishers.getPhone(), phone)) {
            Optional<Publisher> publishersOptional = publishersRepository.findPublishersByPhone(phone);
            if (publishersOptional.isPresent()) {
                throw new IllegalArgumentException("Publisher with this phone number already exists");
            }
            publishers.setPhone(phone);

        }
    }

}

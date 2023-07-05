package com.example.finalproject.repo;

import com.example.finalproject.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Long> {
}

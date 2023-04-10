package ru.nelf.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nelf.backend.entity.File;

import java.util.List;
import java.util.Optional;

public interface MongoDBRepository extends MongoRepository<File, Integer> {
    Optional<File> findByFilename(String filename);
    Optional<List<File>> findAllByCategory(String category);
}

package com.anghel.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * JPA repository and REST endpoint for {@link Parent}.
 */
@RepositoryRestResource(path = "parents", collectionResourceRel = "parents")
public interface ParentRepository extends JpaRepository<Parent, Long> {

    // so far no special finders

}
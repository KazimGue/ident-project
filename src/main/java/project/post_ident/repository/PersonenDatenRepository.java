package project.post_ident.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import project.post_ident.entities.Personendaten;

import java.util.*;

/**
 * 
 */
public interface PersonenDatenRepository extends JpaRepository<Personendaten, Long> {

}
package project.post_ident.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import project.post_ident.entities.Bild;

import java.io.File;
import java.sql.Blob;
import java.util.*;

/**
 * 
 */
public interface BildRepository extends JpaRepository <Bild, Long> {

}
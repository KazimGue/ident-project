package project.post_ident.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import project.post_ident.entities.Bild;

import java.io.File;


/**
 * 
 */
public interface BildRepository extends JpaRepository <Bild, Long> {

}
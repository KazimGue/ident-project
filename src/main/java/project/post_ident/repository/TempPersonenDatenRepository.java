package project.post_ident.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import project.post_ident.entities.Personendaten;
import project.post_ident.entities.TempPersonendaten;

/**
 * 
 */
public interface TempPersonenDatenRepository extends JpaRepository<TempPersonendaten, Long> {

}
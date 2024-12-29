package exp.company.repositories;
import exp.company.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @Query("SELECT m FROM Manager m  WHERE m.name = :name")
    Manager findManagersByName(@Param("name") String name);
}

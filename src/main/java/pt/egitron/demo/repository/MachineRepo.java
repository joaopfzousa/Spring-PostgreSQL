package pt.egitron.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.egitron.demo.model.Machine;

@Repository
public interface MachineRepo extends JpaRepository<Machine, Integer> {
}

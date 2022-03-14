package HRdepartment.demo.repository;

import HRdepartment.demo.model.Shedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheduleRepository extends CrudRepository<Shedule, Integer> {
}

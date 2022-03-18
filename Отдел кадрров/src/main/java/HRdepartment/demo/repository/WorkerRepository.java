package HRdepartment.demo.repository;

import HRdepartment.demo.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    @Modifying
    @Query("SELECT w FROM Worker w WHERE w.positionId = :positionId AND w.isWorking = true")
    Iterable<Worker> findWorkingWorkers(Integer positionId);
}
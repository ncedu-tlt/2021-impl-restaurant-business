package HRdepartment.demo.service;

import HRdepartment.demo.model.Worker;
import HRdepartment.demo.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Iterable<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Optional<Worker> getWorkerById(int id) {
        return workerRepository.findById(id);
    }

    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorkerById(int id, Worker worker){
        worker.setId(id);
        return workerRepository.save(worker);
    }

    public void deleteWorkerById(int id){
        workerRepository.deleteById(id);
    }
    public Iterable<Worker> getWorkingWorkers (int positionId){
        return workerRepository.findWorkingWorkers(positionId);
    }
}

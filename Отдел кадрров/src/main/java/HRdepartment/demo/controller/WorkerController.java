package HRdepartment.demo.controller;

import HRdepartment.demo.model.Worker;
import HRdepartment.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hr/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<Iterable<Worker>> getWorkers(){
        return ResponseEntity.ok(workerService.getWorkers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Worker>> getWorkerById (@PathVariable("id") int id){
        return ResponseEntity.ok(workerService.getWorkerById(id));
    }
    @PostMapping
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker){
        return ResponseEntity.ok(workerService.createWorker(worker));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorkerById(@PathVariable("id") int id,@RequestBody Worker worker){
        return ResponseEntity.ok(workerService.updateWorkerById(id,worker));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkerById(@PathVariable("id") int id){
        workerService.deleteWorkerById(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/working/{positionId}")
    public ResponseEntity<Iterable<Worker>> getWorkingWorkers (@PathVariable("positionId") int positionId){
     return ResponseEntity.ok(workerService.getWorkingWorkers(positionId));
    }
}

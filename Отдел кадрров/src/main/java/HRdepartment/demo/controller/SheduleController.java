package HRdepartment.demo.controller;

import HRdepartment.demo.model.Shedule;
import HRdepartment.demo.service.SheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hr/shedules")
public class SheduleController {

    @Autowired
    private SheduleService sheduleService;

    @GetMapping
    public ResponseEntity<Iterable<Shedule>> getShedules(){
        return ResponseEntity.ok(sheduleService.getShedules());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Shedule>> getSheduleById (@PathVariable("id") int id){
        return ResponseEntity.ok(sheduleService.getShedulerById(id));
    }
    @PostMapping
    public ResponseEntity<Shedule> createShedule(@RequestBody Shedule shedule){
        return ResponseEntity.ok(sheduleService.createShedule(shedule));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Shedule> updateSheduleById(@PathVariable("id") int id,@RequestBody Shedule shedule){
        return ResponseEntity.ok(sheduleService.updateSheduleById(id,shedule));
    }
    @DeleteMapping
    public ResponseEntity deleteSheduleById(@PathVariable("id") int id){
        sheduleService.deleteSheduleById(id);
        return ResponseEntity.accepted().build();
    }


}

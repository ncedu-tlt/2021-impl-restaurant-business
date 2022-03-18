package HRdepartment.demo.controller;

import HRdepartment.demo.model.Position;
import HRdepartment.demo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hr/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public ResponseEntity<Iterable<Position>> getPositions(){
        return ResponseEntity.ok(positionService.getPositions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Position>> getPositionById (@PathVariable("id") int id){
        return ResponseEntity.ok(positionService.getPositionById(id));
    }
    @PostMapping
    public ResponseEntity<Position> createPosition(@RequestBody Position position){
        return ResponseEntity.ok(positionService.createPosition(position));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePositionById(@PathVariable("id") int id,@RequestBody Position position){
        return ResponseEntity.ok(positionService.updatePositionById(id,position));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePositionById(@PathVariable("id") int id){
        positionService.deletePositionById(id);
        return ResponseEntity.accepted().build();
    }


}

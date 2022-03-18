package HRdepartment.demo.service;

import HRdepartment.demo.model.Position;
import HRdepartment.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Iterable<Position> getPositions() {
        return positionRepository.findAll();
    }

    public Optional<Position> getPositionById(int id) {
        return positionRepository.findById(id);
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePositionById(int id, Position position){
        position.setId(id);
        return positionRepository.save(position);
    }

    public void deletePositionById(int id){
        positionRepository.deleteById(id);
    }
}

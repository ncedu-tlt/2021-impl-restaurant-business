package HRdepartment.demo.service;

import HRdepartment.demo.model.Shedule;
import HRdepartment.demo.repository.SheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SheduleService {

    @Autowired
    private SheduleRepository sheduleRepository;

    public Iterable<Shedule> getShedules() {
        return sheduleRepository.findAll();
    }

    public Optional<Shedule> getShedulerById(int id) {
        return sheduleRepository.findById(id);
    }

    public Shedule createShedule(Shedule shedule) {
        return sheduleRepository.save(shedule);
    }

    public Shedule updateSheduleById(int id, Shedule shedule){
        shedule.setId(id);
        return sheduleRepository.save(shedule);
    }

    public void deleteSheduleById(int id){
        sheduleRepository.deleteById(id);
    }
}

package HRdepartment.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "shedule")
public class Shedule {
    @Id
    @Column(name = "worker_id", nullable = false)
    private Integer id;

    @Column(name = "hours_per_week")
    private Double hoursPerWeek;

    @Column(name = "vacation_days")
    private Integer vacationDays;

    @Column(name = "vacation_schedule")
    private Integer vacationSchedule;

    public Integer getVacationSchedule() {
        return vacationSchedule;
    }

    public void setVacationSchedule(Integer vacationSchedule) {
        this.vacationSchedule = vacationSchedule;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
    }

    public Double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
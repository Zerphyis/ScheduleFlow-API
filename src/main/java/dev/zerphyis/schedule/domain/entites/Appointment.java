package dev.zerphyis.schedule.domain.entites;

import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private LocalDateTime dateTime;
    private Professional professional;
    private Client client;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime dateTime, Professional professional, Client client) {
        this.id = id;
        this.dateTime = dateTime;
        this.professional = professional;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Professional getProfessional() {
        return professional;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

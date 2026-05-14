package com.complaint_escalation.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.complaint_escalation.entity.Complaint;
import com.complaint_escalation.enums.Priority;
import com.complaint_escalation.enums.Status;
import com.complaint_escalation.repository.ComplaintRepository;

import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final ComplaintRepository repo;

    public ComplaintService(
            ComplaintRepository repo){

        this.repo = repo;
    }

    public Complaint createComplaint(
            Complaint complaint){

        complaint.setCreatedAt(
                LocalDateTime.now());

        complaint.setStatus(
                Status.OPEN);

        return repo.save(complaint);
    }

    public List<Complaint>
    getAllComplaint(){

        return repo.findAll();
    }

    public Complaint
    getComplaintById(Long id){

        return repo.findById(id)
                .orElse(null);
    }

    public Complaint updateStatus(
            Long id,
            Status status){

        Complaint complaint =
                repo.findById(id)
                        .orElse(null);

        complaint.setStatus(status);

        complaint.setUpdatedAt(
                LocalDateTime.now());

        return repo.save(complaint);
    }

    public void checkEscalation(
            Complaint complaint){

        LocalDateTime now =
                LocalDateTime.now();

        Duration duration =
                Duration.between(
                        complaint.getCreatedAt(),
                        now);

        long hours =
                duration.toHours();

        if(complaint.getStatus()
                != Status.RESOLVED){

            if(complaint.getPriority()
                    == Priority.HIGH
                    && hours >=2){

                complaint.setStatus(
                        Status.ESCALATED);
            }

            else if(
                    complaint.getPriority()
                            == Priority.MEDIUM
                            && hours>=6){

                complaint.setStatus(
                        Status.ESCALATED);
            }

            else if(
                    complaint.getPriority()
                            == Priority.LOW
                            && hours>=24){

                complaint.setStatus(
                        Status.ESCALATED);
            }

        }

        repo.save(complaint);

    }

}
package com.complaint_escalation.scheduler;


import com.complaint_escalation.entity.Complaint;
import com.complaint_escalation.repository.ComplaintRepository;
import com.complaint_escalation.service.ComplaintService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComplaintScheduler {

    private final ComplaintRepository repo;
    private final ComplaintService service;

    public ComplaintScheduler(
            ComplaintRepository repo,
            ComplaintService service){

        this.repo = repo;
        this.service = service;
    }

    @Scheduled(fixedRate = 30000)
    public void checkComplaint(){

        List<Complaint> complaints =
                repo.findAll();

        for(Complaint c : complaints){

            service.checkEscalation(c);

        }

        System.out.println("Checking complaints...");
    }
}
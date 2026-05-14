
package com.complaint_escalation.controller;

import com.complaint_escalation.entity.Complaint;
import com.complaint_escalation.enums.Status;
import com.complaint_escalation.service.ComplaintService;

import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping
    public Complaint createComplaint(
            @RequestBody Complaint complaint){

        return service.createComplaint(complaint);
    }

    @GetMapping
    public List<Complaint> getAllComplaints(){

        return service.getAllComplaint();
    }

    @GetMapping("/{id}")
    public Complaint getComplaintById(
            @PathVariable Long id){

        return service.getComplaintById(id);
    }

    @PutMapping("/{id}")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestParam Status status){

        return service.updateStatus(id, status);
    }
}
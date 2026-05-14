package com.complaint_escalation.repository;

import com.complaint_escalation.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository
        extends JpaRepository<Complaint, Long> {

}
package com.ljmu.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "request_audit", schema = "thesis")
public class RequestAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_audit_seq")
    @SequenceGenerator(name = "request_audit_seq", sequenceName = "request_audit_seq", allocationSize = 1)
    @Column(name = "audit_id")
    private Long auditId;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "request_data")
    private String requestData;
    @Column(name = "response_status")
    private String responseStatus;
    @Column(name = "response_body")
    private String responseBody;
    @Column(name = "status")
    private String status;
    @Column(name = "creation_timestamp")
    private String creationTimestamp;
    @Column(name = "exception")
    private String exception;
    @Column(name = "exception_message")
    private String exceptionMessage;
}

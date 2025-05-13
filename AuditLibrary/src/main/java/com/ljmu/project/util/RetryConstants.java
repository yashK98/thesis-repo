package com.ljmu.project.util;

public interface RetryConstants {
    String SUCCESS = "SUCCESS";
    String FAILED = "FAILED";
    String GET_FAILED_AUDIT_QUERY = "SELECT * FROM request_audit WHERE status='FAILED'";
}

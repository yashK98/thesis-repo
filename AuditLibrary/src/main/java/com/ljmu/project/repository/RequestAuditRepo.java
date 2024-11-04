package com.ljmu.project.repository;

import com.ljmu.project.entity.RequestAudit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ljmu.project.util.RetryConstants.GET_FAILED_AUDIT_QUERY;

@Repository
public interface RequestAuditRepo extends CrudRepository<RequestAudit, Long> {
    @Query(nativeQuery = true, value=GET_FAILED_AUDIT_QUERY)
    public List<RequestAudit> getFailedRequests();
}

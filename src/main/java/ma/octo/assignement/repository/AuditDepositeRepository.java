package ma.octo.assignement.repository;

import ma.octo.assignement.domain.AuditDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditDepositeRepository extends JpaRepository<AuditDeposit,Long> {
}
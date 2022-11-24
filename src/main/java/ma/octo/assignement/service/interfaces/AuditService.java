package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.Audit;
import ma.octo.assignement.exceptions.AuditNonValideException;

public interface AuditService {
     Audit createAudit(Audit audit) throws AuditNonValideException;
}

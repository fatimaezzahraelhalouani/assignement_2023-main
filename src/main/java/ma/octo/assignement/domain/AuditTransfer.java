package ma.octo.assignement.domain;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_TRANSFER")
public class AuditTransfer extends Audit{


  private final String type = "transfer";


  public String getType() {
    return type;
  }
}

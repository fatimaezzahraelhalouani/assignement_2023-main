package ma.octo.assignement.domain;

import ma.octo.assignement.domain.util.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_DEPOSIT")
public class AuditDeposit extends Audit{

  private final String type = "Deposit d'argent";


  public String getType() {
    return type;
  }
}

package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import java.util.List;

public interface MoneyDepositService
{

     void createDeposit(MoneyDepositDto moneyDepositDto) throws CompteNonExistantException, TransactionException, AuditNonValideException;

     List<MoneyDeposit> loadAll();

     void save(MoneyDeposit moneyDeposit);
}

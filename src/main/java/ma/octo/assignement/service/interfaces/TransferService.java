package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNonExistantException;
import java.util.List;

public interface TransferService {

     void createTransaction(TransferDto transferDto) throws CompteNonExistantException, TransactionException, AuditNonValideException;

     List<Transfer> loadAll();

     void save(Transfer transfer);

    TransferDto getTransfer(Long id) throws TransferNonExistantException;

}

package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController(value = "/transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferController {

    Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private TransferService transferService;

    @GetMapping("listDesTransferts")
    List<Transfer> loadAll() {
     return transferService.loadAll();
    }





    @PostMapping("/executerTransfers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransferDto transferDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
         transferService.createTransaction(transferDto);
            }

    private void save(Transfer transfer) {
        transferService.save(transfer);
    }
}

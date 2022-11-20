package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.MoneyDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/deposits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDepositController {

    Logger LOGGER = LoggerFactory.getLogger(MoneyDepositController.class);

    @Autowired
    private MoneyDepositService moneyDepositService;


    @GetMapping("listDesDeposits")
    List<MoneyDeposit> loadAll() {
        return moneyDepositService.loadAll();
    }

    @PostMapping("/executerDeposits")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody MoneyDepositDto moneyDepositDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
        moneyDepositService.createDeposit(moneyDepositDto);
    }

    private void save(MoneyDeposit moneyDeposit) {
        moneyDepositService.save(moneyDeposit);
    }


}

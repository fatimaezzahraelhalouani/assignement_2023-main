package ma.octo.assignement.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.interfaces.MoneyDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposits")

public class MoneyDepositController {

    Logger LOGGER = LoggerFactory.getLogger(MoneyDepositController.class);


    private final MoneyDepositService moneyDepositService;

    public MoneyDepositController(MoneyDepositService moneyDepositService) {
        this.moneyDepositService = moneyDepositService;
    }

    @GetMapping
    List<MoneyDeposit> loadAll() {
        return moneyDepositService.loadAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody MoneyDepositDto moneyDepositDto) throws AuditNonValideException, TransactionException, CompteNonExistantException {
        moneyDepositService.createDeposit(moneyDepositDto);
    }




}

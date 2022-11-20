package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class MoneyDepositService {
    Logger LOGGER = LoggerFactory.getLogger(MoneyDepositService.class);
    public static final int MONTANT_MAXIMAL = 10000;

    @Autowired
    private MoneyDepositRepository moneyDepositRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private AuditService auditService;


    public void createDeposit(MoneyDepositDto moneyDepositDto)
            throws CompteNonExistantException, TransactionException {

        Compte compteBeneficiaire = compteRepository
                .findByNrCompte(moneyDepositDto.getNrCompteBeneficiaire());


        if (compteBeneficiaire == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (moneyDepositDto.getMontant().equals(null)) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (moneyDepositDto.getMontant().intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (moneyDepositDto.getMontant().intValue() < 10) {
            System.out.println("Montant minimal de deposit non atteint");
            throw new TransactionException("Montant minimal de deposit non atteint");
        } else if (moneyDepositDto.getMontant().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de deposit dépassé");
            throw new TransactionException("Montant maximal de deposit dépassé");
        }


        compteBeneficiaire
                .setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + moneyDepositDto.getMontant().intValue()));
        compteRepository.save(compteBeneficiaire);

        MoneyDeposit moneyDeposit = new MoneyDeposit();
        moneyDeposit.setDateExecution(moneyDepositDto.getDate());
        moneyDeposit.setCompteBeneficiaire(compteBeneficiaire);
        moneyDeposit.setMontant(moneyDepositDto.getMontant());

        moneyDepositRepository.save(moneyDeposit);

        auditService.auditTransfer("Deposit "+ moneyDepositDto
                .getNrCompteBeneficiaire() + " d'un montant de " + moneyDepositDto.getMontant()
                .toString());
    }

    public List<MoneyDeposit> loadAll() {
        LOGGER.info("Lister des utilisateurs");
        var all = moneyDepositRepository.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return CollectionUtils.isEmpty(all) ? all : null;
        }


    }
    public void save(MoneyDeposit moneyDeposit) {
        moneyDepositRepository.save(moneyDeposit);
    }
}

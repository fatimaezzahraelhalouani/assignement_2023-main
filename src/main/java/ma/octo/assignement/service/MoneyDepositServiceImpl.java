package ma.octo.assignement.service;

import ma.octo.assignement.domain.Audit;
import ma.octo.assignement.domain.AuditDeposit;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.MoneyDeposit;
import ma.octo.assignement.dto.MoneyDepositDto;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.AuditDepositeRepository;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.MoneyDepositRepository;
import ma.octo.assignement.service.interfaces.AuditService;
import ma.octo.assignement.service.interfaces.MoneyDepositService;
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
public class MoneyDepositServiceImpl implements MoneyDepositService {
    Logger LOGGER = LoggerFactory.getLogger(MoneyDepositServiceImpl.class);
    public static final int MONTANT_MAXIMAL = 10000;


    private final MoneyDepositRepository moneyDepositRepository;

    private final CompteRepository compteRepository;

    private final AuditService auditService;
@Autowired
    public MoneyDepositServiceImpl(MoneyDepositRepository moneyDepositRepository,
                              CompteRepository compteRepository, AuditService auditService) {
        this.moneyDepositRepository = moneyDepositRepository;
        this.compteRepository = compteRepository;
        this.auditService = auditService;
    }

    public void createDeposit(MoneyDepositDto moneyDepositDto)
            throws CompteNonExistantException, TransactionException, AuditNonValideException {

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

        //mise à jour du solde solde
        compteBeneficiaire
                .setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + moneyDepositDto.getMontant().intValue()));
        compteRepository.save(compteBeneficiaire);

        MoneyDeposit moneyDeposit = new MoneyDeposit();
        moneyDeposit.setDateExecution(moneyDepositDto.getDate());
        moneyDeposit.setCompteBeneficiaire(compteBeneficiaire);
        moneyDeposit.setMontantDeposit(moneyDepositDto.getMontant());

        //creer nouveau deposit
        // create an audit
        Audit audit = new AuditDeposit();
        String message = "Deposit fait par " + moneyDepositDto.getNomPrenomEmetteur() + " vers "
                + moneyDepositDto.getNrCompteBeneficiaire() + " d'un montant de "
                + moneyDepositDto.getMontant().toString();

        audit.setMessage(message);
        auditService.createAudit(audit);
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

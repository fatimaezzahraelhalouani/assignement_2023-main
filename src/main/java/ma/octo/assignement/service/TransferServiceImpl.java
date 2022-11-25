package ma.octo.assignement.service;

import ma.octo.assignement.domain.*;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNonExistantException;
import ma.octo.assignement.mapper.TransferMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.interfaces.AuditService;
import ma.octo.assignement.service.interfaces.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    Logger LOGGER = LoggerFactory.getLogger(TransferServiceImpl.class);

    public static final int MONTANT_MAXIMAL = 10000;
    private final TransferRepository transferRepository;
    private final CompteRepository compteRepository;
    private final AuditService auditService;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, CompteRepository compteRepository, AuditService auditService)
    {
        this.transferRepository = transferRepository;
        this.compteRepository = compteRepository;
        this.auditService = auditService;
    }

    @Override
    public void createTransaction(TransferDto transferDto) throws CompteNonExistantException, TransactionException, AuditNonValideException
    {
        Compte compteEmetteur = compteRepository.findByNrCompte(transferDto.getNrCompteEmetteur());
        Compte compteBeneficiaire = compteRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire());

        if (compteEmetteur == null)
        {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (compteBeneficiaire == null)
        {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (transferDto.getMontant().equals(null))
        {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        }
        else if (transferDto.getMontant().intValue() == 0)
        {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        }
        else if (transferDto.getMontant().intValue() < 10)
        {
            System.out.println("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        }
        else if (transferDto.getMontant().intValue() > MONTANT_MAXIMAL)
        {
            System.out.println("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");
        }

        if (transferDto.getMotif().length() < 0)
        {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        if (compteEmetteur.getSolde().intValue() - transferDto.getMontant().intValue() < 0)
        {
           LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        //Mise à jour solde emetteur
        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(transferDto.getMontant()));
        compteRepository.save(compteEmetteur);

        //Mise à jour solde beneficiaire
        compteBeneficiaire.setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + transferDto.getMontant().intValue()));
        compteRepository.save(compteBeneficiaire);

        Transfer transfer = new Transfer();
        transfer.setDateExecution(transferDto.getDate());
        transfer.setCompteBeneficiaire(compteBeneficiaire);
        transfer.setCompteEmetteur(compteEmetteur);
        transfer.setMontantTransfer(transferDto.getMontant());

        transferRepository.save(transfer);

        //creer transfer
        Audit audit = new AuditTransfer();
        String message = "Transfer depuis " + transferDto.getNrCompteEmetteur() + " vers " + transferDto
                .getNrCompteBeneficiaire() + " d'un montant de " + transferDto.getMontant()
                .toString();

        audit.setMessage(message);
        auditService.createAudit(audit);
    }

    @Override
    public List<Transfer> loadAll()
    {
        LOGGER.info("Lister des utilisateurs");

        var all = transferRepository.findAll();

        if (CollectionUtils.isEmpty(all))
        {
            return null;
        }
        else
        {
            return CollectionUtils.isEmpty(all) ? all : null;
        }

    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public TransferDto getTransfer(Long id) throws TransferNonExistantException
    {
        Optional<Transfer> optionalTransferDto = transferRepository.findById(id);

        if (!optionalTransferDto.isPresent())
            throw new TransferNonExistantException("ce transfert non existant");

        return TransferMapper.map(optionalTransferDto.get());
    }
}

package ma.octo.assignement.service;

import ma.octo.assignement.domain.*;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AuditService {


    Logger LOGGER = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditTransferRepository auditTransferRepository;
    @Autowired
    private AuditDepositeRepository auditDepositeRepository;


    public void auditTransfer(String message) {

        LOGGER.info("Audit de l'événement Transfert");

        AuditTransfer audit = new AuditTransfer();
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }


    public void auditDeposit(String message) {
        //?
        LOGGER.info("Audit de l'événement Deposit de l'argent");

        AuditDeposit audit = new AuditDeposit();
        audit.setMessage(message);
        auditDepositeRepository.save(audit);
    }


    }






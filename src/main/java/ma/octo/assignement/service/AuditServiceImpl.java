package ma.octo.assignement.service;

import ma.octo.assignement.domain.*;
import ma.octo.assignement.exceptions.AuditNonValideException;
import ma.octo.assignement.repository.*;
import ma.octo.assignement.service.interfaces.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {


    Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);



    private final AuditRepository auditRepository;
@Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }


    @Override
    public Audit createAudit(Audit audit) throws AuditNonValideException {
        String message = audit.getMessage();
        if (message == null || message.equals(""))
            throw new AuditNonValideException("Ajouter une description a l'audit");
        return auditRepository.save(audit);
    }

/*
    private   AuditTransferRepository auditTransferRepository;

    private   AuditDepositeRepository auditDepositeRepository;
    public void auditTransfer(String message) throws AuditNonValideException {

        LOGGER.info("Audit de l'événement Transfert");

        AuditTransfer audit = new AuditTransfer();
        if (message == null || message.equals(""))
            throw new AuditNonValideException("L'audit n'a pas une description");
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }


    public void auditDeposit(String message) throws AuditNonValideException {
        //?
        LOGGER.info("Audit de l'événement Deposit de l'argent");

        AuditDeposit audit = new AuditDeposit();
        if (message == null || message.equals(""))
            throw new AuditNonValideException("L'audit n'a pas une description");
        audit.setMessage(message);
        auditDepositeRepository.save(audit);
    }
*/


    }






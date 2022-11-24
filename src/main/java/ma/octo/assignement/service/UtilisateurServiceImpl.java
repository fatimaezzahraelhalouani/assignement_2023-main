package ma.octo.assignement.service;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurResponseDto;
import ma.octo.assignement.dto.UtilisateurResquestDto;
import ma.octo.assignement.exceptions.UtilisateurExisteDejaException;
import ma.octo.assignement.mapper.UtilisateurMapper;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.interfaces.UtlisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class UtilisateurServiceImpl implements UtlisateurService {

    Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;

@Autowired
    public UtilisateurServiceImpl(PasswordEncoder passwordEncoder,UtilisateurRepository utilisateurRepository) {

        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public List<UtilisateurResponseDto> loadAllUtilisateur() {
        List<UtilisateurResponseDto> all = utilisateurRepository.findAll().stream().map(UtilisateurMapper::map).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }
    @Override
    public UtilisateurResponseDto getUtilisateurByUsername(String username){

        return UtilisateurMapper.map(utilisateurRepository.findByUsername(username));
    }

    @Override
    public UtilisateurResponseDto save(UtilisateurResquestDto utilisateurResquestDto) throws UtilisateurExisteDejaException {

        Utilisateur user = utilisateurRepository.findByUsername(utilisateurResquestDto.getUsername());

        if (user != null) {
            throw new UtilisateurExisteDejaException("Ce nom d'utilisateur existe deja");
        }

        Utilisateur utilisateur = UtilisateurMapper.utilisateur(utilisateurResquestDto);

        // chiffrer le password
        String encodedPassword = passwordEncoder.encode(utilisateurResquestDto.getPassword());

       utilisateur.setPassword(encodedPassword);

        Utilisateur saveUtilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.map(saveUtilisateur);

    }

}

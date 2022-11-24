package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.CompteRequestDto;
import ma.octo.assignement.dto.CompteResponseDto;
import ma.octo.assignement.exceptions.CompteExisteDejaException;
import ma.octo.assignement.exceptions.CompteNonValidException;
import ma.octo.assignement.exceptions.UtilisateurNonExistantException;
import ma.octo.assignement.mapper.CompteMapper;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.interfaces.CompteService;
import ma.octo.assignement.service.validators.CompteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ma.octo.assignement.service.validators.CompteValidator.*;
import static ma.octo.assignement.service.validators.CompteValidator.IsValid.VALIDE;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    Logger LOGGER = LoggerFactory.getLogger(CompteServiceImpl.class);


    private final CompteRepository compteRepository;

     private final UtilisateurRepository utilisateurRepository;

    public CompteServiceImpl(CompteRepository compteRepository,
                             UtilisateurRepository utilisateurRepository) {
        this.compteRepository = compteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public CompteResponseDto getCompte(String nrCompte) {
        return CompteMapper.map(compteRepository.findByNrCompte(nrCompte));
    }

    @Override
    public List<CompteResponseDto> loadAllCompte() {
        List<CompteResponseDto> all = compteRepository.findAll().stream().map(CompteMapper::map)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @Override
    public CompteResponseDto save(CompteRequestDto compteRequestDto) throws UtilisateurNonExistantException, CompteNonValidException, CompteExisteDejaException {

        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(compteRequestDto.getUsername());

        if (utilisateur == null) {
            throw new UtilisateurNonExistantException(
                    "Cet utilisateur "
                            + compteRequestDto.getUsername()+" n'existe pas");
        }

        CompteValidator.IsValid compteValidation = isNbCompteValid()
                .and(isRibValid())
                .and(isSoldeSuffisant())
                .apply(compteRequestDto);

        if (!compteValidation.equals(VALIDE))
            throw new CompteNonValidException(compteValidation.getMessage());

        Compte compteExistant = compteRepository.findByNrCompte(compteRequestDto.getNrCompte());

        if (compteExistant != null)
            throw new CompteExisteDejaException("Ce numero de compte existe deja.");

        Compte compte = compteRepository.save(
                CompteMapper.RequestCompte(compteRequestDto, utilisateur));

        return CompteMapper.map(compte);
    }
    @Override
   public void deleteCompte(String nrCompte)
    {
        compteRepository.deleteByNrCompte(nrCompte);
    }


}

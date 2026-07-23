package com.betacom.ec.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.betacom.ec.dto.input.ChangePasswordRequest;
import com.betacom.ec.dto.input.UtenteRequest;
import com.betacom.ec.dto.output.MeDTO;
import com.betacom.ec.dto.output.UtenteDTO;
import com.betacom.ec.exception.EcommerceVinoException;
import com.betacom.ec.mapping.UtenteMap;
import com.betacom.ec.models.Utente;
import com.betacom.ec.repository.IRuoloRepository;
import com.betacom.ec.repository.IUtenteRepository;
import com.betacom.ec.services.interfaces.IUtenteService;
import com.betacom.ec.utils.Utilities;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UtenteImpl implements IUtenteService {
	private final IUtenteRepository utenteRepository;
	private final IRuoloRepository ruoloRepository;
	
	private final UtenteMap mapper;
	
	private final PasswordEncoder encoder;
	
	@Transactional
	@Override
	public Utente create(UtenteRequest utenteRequest) throws Exception {
		log.debug("create: {}", utenteRequest);
		
		Utente utente = new Utente();
		utente.setNome(utenteRequest.getNome());
		utente.setCognome(utenteRequest.getCognome());
		utente.setDataNascita(Utilities.stringToDate(utenteRequest.getDataNascita()));
		utente.setEmail(utenteRequest.getEmail());
		utente.setPassword(utenteRequest.getPassword());
		utente.setRuolo(ruoloRepository.findById(utenteRequest.getIdRuolo()).orElseThrow(() -> new EcommerceVinoException("ruolo.id_not_found")));
		//CHECK non salviamo qui le info su cliente e venditore giusto?
		
		return utenteRepository.save(utente);
	}
	
	@Transactional
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("Delete user with id {}", id);
		
		Utente utente = utenteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));
		
		utenteRepository.delete(utente);
	}
	
	@Transactional
	@Override
	public Utente update(UtenteRequest utenteRequest) throws Exception {
		log.debug("Update user {}", utenteRequest);
		
		Utente utente = utenteRepository.findById(utenteRequest.getId())
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));
		
		Optional.ofNullable(utenteRequest.getCognome()).ifPresent(utente::setCognome);
		Optional.ofNullable(utenteRequest.getNome()).ifPresent(utente::setNome);
		Optional.ofNullable(utenteRequest.getEmail()).ifPresent(utente::setEmail);
		Optional.ofNullable(utenteRequest.getPassword()).ifPresent(utente::setPassword); //CHECK security issues? dovrei fare un metodo a parte?
		Optional.ofNullable(utenteRequest.getDataNascita()).ifPresent(data -> utente.setDataNascita(Utilities.stringToDate(data)));
		if (utenteRequest.getIdRuolo() != null)
			utente.setRuolo(ruoloRepository.findById(utenteRequest.getIdRuolo()).orElseThrow(() -> new EcommerceVinoException("ruolo.id_not_found")));
		
		return utente;
	}
	
	@Transactional
	@Override
	public List<UtenteDTO> listBySearchString(String nomeSearch, 
								String cognomeSearch, 
								String emailSearch, 
								String dataNascitaSearch, 
								String ruoloSearch) throws Exception {
		
		List<Utente> listUtente = utenteRepository.searchByFilter(nomeSearch, cognomeSearch, emailSearch, dataNascitaSearch, ruoloSearch); 
		return mapper.buildUtenteDTOList(listUtente); 
	}
	
	@Transactional
	@Override
	public UtenteDTO getById(Integer id) throws Exception {
		log.debug("getById {}", id);
		
		Utente utente = utenteRepository.findById(id)
							.orElseThrow(() -> new EcommerceVinoException("utente.id_not_found"));		
		
		return mapper.buildUtenteDTO(utente);
	}
	
	@Transactional
	@Override
	public void changePassword(ChangePasswordRequest req) throws Exception {
		log.debug("changePwd {}", req);
		
		Utente ut = utenteRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new EcommerceVinoException("user_ntfnd"));

		if (!encoder.matches(req.getOldPassword(), ut.getPassword()))
			throw new Exception("login_invalid");
		
		Optional.ofNullable(req.getNewPassword())
			.ifPresentOrElse(pwd -> {
				ut.setPassword((encoder.encode(pwd))) ;
			}, () -> { 
				throw new RuntimeException("user_no_newpwd");
			});
		
		utenteRepository.save(ut);
		
	}

	@Transactional
	@Override
	public MeDTO me(UtenteRequest req) throws Exception {
		log.debug("login {}", req);
		Utente ut = utenteRepository.findByEmail(req.getEmail())
				.orElseThrow(() -> new EcommerceVinoException("user_invalid_pwd"));
		
		return MeDTO.builder()
				.id(ut.getEmail())
				.role(ut.getRuolo().getNome())
//				.mailValidate(ut.getValidate())
				.build();
}
	
}

package com.betacom.ec.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betacom.ec.models.MessageID;
import com.betacom.ec.models.Messaggi;
import com.betacom.ec.repository.IMessaggioRepository;
import com.betacom.ec.services.interfaces.IMessaggioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessaggioImpl implements IMessaggioService{
	
	private final IMessaggioRepository msgR;
	
	@Value("${lang}")
	private String lang;

	@Override
	public String get(String code) {
		log.debug("get {}", code);
		String r = null;
		Optional<Messaggi> m = msgR.findById(new MessageID(lang, code));
		if(m.isEmpty())
			r = code;
		else
			r = m.get().getMessaggio();
		
		
		return r;
	}

}

package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.alcolico.AlcolicoTest;
import com.betacom.ec.box.BoxControllerTest;
import com.betacom.ec.cantina.CantinaControllerTest;
import com.betacom.ec.carrello.CarrelloTest;
import com.betacom.ec.controllers.BoxController;
import com.betacom.ec.degustazione.DegustazioneControllerTest;
import com.betacom.ec.prodottoalcolico.ProdottoAlcolicoTest;
import com.betacom.ec.prodottobox.ProdottoBoxTest;
import com.betacom.ec.prodottodegustazione.ProdottoDegustazioneTest;
import com.betacom.ec.ratingalcolico.RatingAlcolicoTest;
import com.betacom.ec.ratingcantina.RatingCantinaTest;
import com.betacom.ec.utente.RuoloControllerTest;
import com.betacom.ec.utente.VenditoreControllerTest;


@Suite
@SelectClasses({
	RuoloControllerTest.class,
	VenditoreControllerTest.class,
	AlcolicoTest.class,
	CantinaControllerTest.class,
	BoxControllerTest.class,
	DegustazioneControllerTest.class,
	ProdottoAlcolicoTest.class,
	ProdottoBoxTest.class,
	ProdottoDegustazioneTest.class,
	CarrelloTest.class,
	RatingAlcolicoTest.class,
	RatingCantinaTest.class
})
public class SuitClassPulzato {

}

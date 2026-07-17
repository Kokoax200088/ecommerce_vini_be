package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.carrello.CarrelloTest;
import com.betacom.ec.prodottoalcolico.ProdottoAlcolicoTest;
import com.betacom.ec.prodottobox.ProdottoBoxTest;
import com.betacom.ec.prodottodegustazione.ProdottoDegustazioneTest;
import com.betacom.ec.ratingalcolico.RatingAlcolicoTest;
import com.betacom.ec.ratingcantina.RatingCantinaTest;


@Suite
@SelectClasses({
	CarrelloTest.class,
	ProdottoAlcolicoTest.class,
	ProdottoBoxTest.class,
	ProdottoDegustazioneTest.class,
	RatingAlcolicoTest.class,
	RatingCantinaTest.class
})
public class SuitClassPulzato {

}

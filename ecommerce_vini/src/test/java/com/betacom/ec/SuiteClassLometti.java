package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.alcolico.AlcolicoTest;
import com.betacom.ec.caratteristica.CaratteristicaTest;
import com.betacom.ec.colore.ColoreTest;
import com.betacom.ec.ordinebox.OrdineBoxTest;
import com.betacom.ec.ordinedegustazione.OrdineDegustazioneTest;
import com.betacom.ec.tipologiaalcolico.TipologiaAlcolicoTest;

@Suite
@SelectClasses({
	AlcolicoTest.class,
	ColoreTest.class,
	TipologiaAlcolicoTest.class,
	CaratteristicaTest.class,
	OrdineBoxTest.class,
	OrdineDegustazioneTest.class
})
public class SuiteClassLometti {

}

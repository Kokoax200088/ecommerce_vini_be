package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.box.BoxControllerTest;
import com.betacom.ec.boxalcolico.BoxAlcolicoControllerTest;
import com.betacom.ec.cantina.CantinaControllerTest;
import com.betacom.ec.cantinaalcolico.CantinaAlcolicoControllerTest;
import com.betacom.ec.degustazione.DegustazioneControllerTest;
import com.betacom.ec.immaginealcolico.ImmagineAlcolicoControllerTest;
import com.betacom.ec.immaginebox.ImmagineBoxControllerTest;
import com.betacom.ec.immaginecantina.ImmagineCantinaControllerTest;
import com.betacom.ec.immaginedegustazione.ImmagineDegustazioneControllerTest;
import com.betacom.ec.posizione.PosizioneControllerTest;
import com.betacom.ec.prenotazionedegustazione.PrenotazioneDegustazioneControllerTest;

@Suite
@SelectClasses({
	BoxControllerTest.class,
	PosizioneControllerTest.class,
	BoxAlcolicoControllerTest.class,
	CantinaControllerTest.class,
	CantinaAlcolicoControllerTest.class,
	DegustazioneControllerTest.class,
	ImmagineBoxControllerTest.class,
	ImmagineCantinaControllerTest.class,
	ImmagineDegustazioneControllerTest.class,
	ImmagineAlcolicoControllerTest.class,
	PrenotazioneDegustazioneControllerTest.class
})
public class SuiteClassPurcea {

}

package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.box.BoxControllerTest;
import com.betacom.ec.box.BoxServiceTest;
import com.betacom.ec.boxalcolico.BoxAlcolicoControllerTest;
import com.betacom.ec.boxalcolico.BoxAlcolicoServiceTest;
import com.betacom.ec.cantina.CantinaControllerTest;
import com.betacom.ec.cantina.CantinaServiceTest;
import com.betacom.ec.cantinaalcolico.CantinaAlcolicoControllerTest;
import com.betacom.ec.cantinaalcolico.CantinaAlcolicoServiceTest;
import com.betacom.ec.degustazione.DegustazioneControllerTest;
import com.betacom.ec.degustazione.DegustazioneServiceTest;
import com.betacom.ec.immaginealcolico.ImmagineAlcolicoControllerTest;
import com.betacom.ec.immaginealcolico.ImmagineAlcolicoServiceTest;
import com.betacom.ec.immaginebox.ImmagineBoxControllerTest;
import com.betacom.ec.immaginebox.ImmagineBoxServiceTest;
import com.betacom.ec.immaginecantina.ImmagineCantinaControllerTest;
import com.betacom.ec.immaginecantina.ImmagineCantinaServiceTest;
import com.betacom.ec.immaginedegustazione.ImmagineDegustazioneControllerTest;
import com.betacom.ec.immaginedegustazione.ImmagineDegustazioneServiceTest;
import com.betacom.ec.posizione.PosizioneControllerTest;
import com.betacom.ec.posizione.PosizioneServiceTest;
import com.betacom.ec.prenotazionedegustazione.PrenotazioneDegustazioneControllerTest;
import com.betacom.ec.prenotazionedegustazione.PrenotazioneDegustazioneServiceTest;

@Suite
@SelectClasses({
	BoxControllerTest.class,
	PosizioneControllerTest.class,
	PosizioneServiceTest.class,
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

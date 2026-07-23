package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.ordine.OrdineTest;
import com.betacom.ec.ordinealcolico.OrdineAlcolicoTest;
import com.betacom.ec.status.StatusTest;
import com.betacom.ec.spedizionealcolico.SpedizioneAlcolicoTest;
import com.betacom.ec.spedizionebox.SpedizioneBoxTest;

@Suite
@SelectClasses({
	StatusTest.class,
	OrdineTest.class,
	OrdineAlcolicoTest.class,
	SpedizioneAlcolicoTest.class,
	SpedizioneBoxTest.class
})

public class SuiteClassPadilla {
	
}

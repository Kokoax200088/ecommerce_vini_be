package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.utente.ClienteControllerTest;
import com.betacom.ec.utente.RuoloControllerTest;
import com.betacom.ec.utente.UtenteControllerTest;

//import com.betacom.ec.utente.RuoloControllerTest;

@Suite
@SelectClasses({
	RuoloControllerTest.class,
	UtenteControllerTest.class,
	ClienteControllerTest.class
})
public class SuiteClassSalvatore {

}

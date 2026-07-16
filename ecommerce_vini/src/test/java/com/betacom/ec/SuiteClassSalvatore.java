package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.utente.RuoloControllerTest;

//import com.betacom.ec.utente.RuoloControllerTest;

@Suite
@SelectClasses({
	RuoloControllerTest.class
})
public class SuiteClassSalvatore {

}

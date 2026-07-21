package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.ordine.OrdineTest;
import com.betacom.ec.ordinealcolico.OrdineAlcolicoTest;
import com.betacom.ec.status.StatusTest;

@Suite
@SelectClasses({
	StatusTest.class,
	OrdineTest.class,
	OrdineAlcolicoTest.class
})
public class SuiteClassPadilla {

	
}

package com.betacom.ec;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.ec.status.StatusTest;

@Suite
@SelectClasses({
	StatusTest.class
})
public class SuiteClassPadilla {

	
}

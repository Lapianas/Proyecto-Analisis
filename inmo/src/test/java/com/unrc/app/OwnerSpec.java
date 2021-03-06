package com.unrc.app;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unrc.app.models.Owner;
import static org.javalite.test.jspec.JSpec.the;

public class OwnerSpec {	
	
		@Before
		public void before() {
			Base.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/inmoapp_development","root","root");
			Base.openTransaction();
		}
		
		@After
		public void after() {
			Base.rollbackTransaction();
			Base.close();
		}
		
		@Test
		public void shouldValidateMandatoryFields() {
			
			Owner owner = new Owner();
			
			//Check Errors
			
			the(owner).shouldNotBe("valid");
			the(owner.errors().get("owner_name")).shouldBeEqual("value is missing");
			the(owner.errors().get("owner_dni")).shouldBeEqual("value is missing");
			
			
			//Set Missing Values
			owner.set("owner_name","John Doe");
			owner.set("owner_dni","1234567");
			
			
			//All Is Good
			the(owner).shouldBe("valid");
		}
		
		
		
		
	}


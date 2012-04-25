package com.nicholasren;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTemplate {

	private Template template;

	@Before
	public void setUp() {
		template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");

	}

	@Test
	public void one_variable() {
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void unknow_variable_should_be_ignored() {
		template.set("not_exist", "some_value");
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void missing_value_raise_exception() {
		try {
			new Template("${foo}").evluate();
			fail("evaluate() should throw an exception if a variable was left without a value");
		} catch (MissingValueException expected) {
			assertEquals("No value for ${foo}", expected.getMessage());
		}
	}
	
	@Test
	public void variables_should_get_processed_onece()
	{
	    template.set("one", "${one}");
	    template.set("two", "${three}");
	    template.set("three", "${two}");
	    
	    assertTemplateEvaluatesTo("${one}, ${three}, ${two}");
	    
	}

	private void assertTemplateEvaluatesTo(String expected) {
		assertEquals(expected, template.evluate());
	}
}

package com.nicholasren;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestTemplateParse {

	@Test
	public void emplate_template_renders_as_empty_string() {
		List<String> segments = parse("");
		assertSegments(segments, "");
	}

	@Test
	public void template_with_only_plain_text() {
		List<String> segments = parse("plain text only");
		assertSegments(segments, "plain text only");
	}

	@Test
	public void parsing_multiple_variable() {
		List<String> segments = parse("${a}:${b}:${c}");
		assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
	}
	
	
	@Test
	public void parsing_template_into_segment_objects()
	{
		TemplateParser parser = new TemplateParser();
		List<Segment> segments = parser.parseSegments("a ${b} c ${d}");
		
		assertSegments(segments, new PlainText("a "), new Variable("b"), new PlainText("c "), new Variable("d"));
	}

	private List<String> parse(String template) {
		return new TemplateParser().parse(template);
	}

	private void assertSegments(List<? extends Object> actual,
			Object... expected) {
		assertEquals("Number of segments", expected.length, actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}
}

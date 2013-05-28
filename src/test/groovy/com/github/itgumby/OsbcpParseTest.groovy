package com.github.itgumby

import com.osbcp.cssparser.CSSParser
import com.osbcp.cssparser.PropertyValue
import com.osbcp.cssparser.Rule
import com.osbcp.cssparser.Selector
import org.junit.Ignore
import org.junit.Test

import static org.junit.Assert.assertEquals

class OsbcpParseTest {

    @Test
    public void testSimpleString() {
        String input = 'div { width: 100px; -mozilla-opacity: 345; }'

        List<Rule> rules = CSSParser.parse(input)
        assertEquals("rule count", 1, rules.size())

        Rule rule = rules.head();
        List<Selector> selectors = rule.getSelectors()
        List<PropertyValue> propertyValues = rule.getPropertyValues()
        assertEquals("selector count", 1, selectors.size())
        assertEquals("prop count", 2, propertyValues.size())

        List<String> props = propertyValues*.getProperty()
        assertEquals("Css properties contains width", true, props.contains("width"))
    }

    @Test
    @Ignore
    public void testMediaQueries() {
        String input = '@media screen and (max-width:1024px) { p { color: black; } }'

        List<Rule> rules = CSSParser.parse(input)
        assertEquals("rule count", 1, rules.size())

        Rule rule = rules.head();
        List<Selector> selectors = rule.getSelectors()
        List<PropertyValue> propertyValues = rule.getPropertyValues()
        assertEquals("selector count", 1, selectors.size())
//        assertEquals("media query rule", "p { color: black; }", rule.toString())

        List<String> props = propertyValues*.getProperty()
//        props.each { println "${it}" }    // p { color
//        assertEquals("Css properties contains color", true, props.contains("color"))
        assertEquals("Css properties doesnt contain width", false, props.contains("width"))
    }
}

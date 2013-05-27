package com.github.itgumby

import com.steadystate.css.parser.CSSOMParser
import org.junit.Test
import static org.junit.Assert.*
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.CSSRule
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.css.CSSValue

//courtesy of http://stackoverflow.com/questions/1513587/looking-for-a-css-parser-in-java
public class ParseCssTest {

    @Test
    public void testParseStyleDeclaration() throws IOException {
        String cssSample = "margin-top: 0cm; margin-bottom: 0cm; background: #e6e6e6";
        CSSOMParser parser = new CSSOMParser();
        CSSStyleDeclaration o = parser.parseStyleDeclaration(new InputSource(new StringReader(cssSample)));
        assertEquals("margin-top: 0cm; margin-bottom: 0cm; background: rgb(230, 230, 230)", o.toString());
        assertEquals("0cm", o.getPropertyCSSValue("margin-bottom").toString());
        assertEquals("0cm", o.getPropertyCSSValue("margin-bottom").getCssText());
        assertEquals(null, o.getPropertyCSSValue("foo"));
    }

    @Test
    public void testParseARule() throws IOException {
        String cssSample = "r1 { margin-top: 0cm; margin-bottom: 0cm; background: #e6e6e6 }";
        CSSOMParser parser = new CSSOMParser();
        CSSRule o = parser.parseRule(new InputSource(new StringReader(cssSample)));
        assertEquals("r1 { margin-top: 0cm; margin-bottom: 0cm; background: rgb(230, 230, 230) }", o.toString());
    }

    @Test
    public void parseStyleDeclarationWithAdvancedTests() throws IOException {
        String cssSample = "margin-top: 0 cm; margin-bottom: 0cm; background: #e6e6e6";
        CSSOMParser parser = new CSSOMParser();
        CSSStyleDeclaration o = parser.parseStyleDeclaration(new InputSource(new StringReader(cssSample)));
        assertEquals("margin-top: 0 cm; margin-bottom: 0cm; background: rgb(230, 230, 230)", o.toString());

        assertEquals("0cm", o.getPropertyCSSValue("margin-bottom").toString());
        assertEquals("0cm", o.getPropertyCSSValue("margin-bottom").getCssText());
        assertEquals(CSSValue.CSS_VALUE_LIST, o.getPropertyCSSValue("margin-top").getCssValueType());

        assertEquals("0 cm", o.getPropertyCSSValue("margin-top").toString());
        assertEquals("0 cm", o.getPropertyCSSValue("margin-top").getCssText());
        assertEquals(CSSValue.CSS_VALUE_LIST, o.getPropertyCSSValue("margin-top").getCssValueType());
    }
}

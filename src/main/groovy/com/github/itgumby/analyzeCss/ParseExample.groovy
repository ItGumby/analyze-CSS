package com.github.itgumby.analyzeCss

import com.steadystate.css.parser.CSSOMParser
import org.w3c.css.sac.InputSource
import org.w3c.dom.css.*

// example converted from http://stackoverflow.com/questions/1513587/looking-for-a-css-parser-in-java
class ParseExample {

    static ParseExample parser;

    public static void main(String[] args) {
        parser = new ParseExample();

        if (parser.isValid("example.css")) {
            println("Parsing completed OK");
        } else {
            println("Unable to parse CSS");
        }
    }


    public boolean isValid(String cssfile) {
        FileOutputStream out = null;
        PrintStream ps = null;
        boolean retVal = false;

        try {
            // cssfile accessed as a resource, so must be in the pkg (in src dir).
            InputStream stream = parser.getClass().getResourceAsStream(cssfile)

            // overwrites and existing file contents
            out = new FileOutputStream("log.txt")
            if (out != null) {
                ps = new PrintStream(out)
                System.setErr(ps) //redirects stderr to the log file as well
            } else {
                return retVal
            }

            InputSource source = new InputSource(new InputStreamReader(stream))
            CSSOMParser parser = new CSSOMParser()
            // parse and create a stylesheet composition
            CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null)

            //ANY ERRORS IN THE DOM WILL BE SENT TO STDERR HERE!!
            // now iterate through the dom and inspect.
            CSSRuleList ruleList = stylesheet.getCssRules()
            ps.println("Number of rules: ${ruleList.getLength()}")

            for (int i = 0; i < ruleList.getLength(); i++) {
                CSSRule rule = ruleList.item(i);
                if (rule instanceof CSSStyleRule) {
                    CSSStyleRule styleRule = (CSSStyleRule) rule;
                    ps.println("selector# ${i}: ${styleRule.getSelectorText()}")
                    CSSStyleDeclaration styleDeclaration = styleRule.getStyle()

                    for (int j = 0; j < styleDeclaration.getLength(); j++) {
                        String property = styleDeclaration.item(j);
                        ps.println("\tproperty: ${property}")
                        ps.println("\tvalue: ${styleDeclaration.getPropertyCSSValue(property).getCssText()}")
                        if (!styleDeclaration.getPropertyPriority(property).isEmpty())
                            ps.println("\tpriority: ${styleDeclaration.getPropertyPriority(property)}")
                    }

                }// end of StyleRule instance test
            } // end of ruleList loop

            if (out != null) out.close();
            if (stream != null) stream.close();
            retVal = true;
        }
        catch (IOException ioe)
            { System.err.println("IO Error: " + ioe); }
        catch (Exception e)
            { System.err.println("Error: " + e); }
        finally { if (ps != null) ps.close(); }

        return retVal;
    }

}

package com.github.itgumby.analyzeCss
/*
 * Copyright (c) 2000 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 */

import org.w3c.css.sac.*
import org.w3c.css.sac.helpers.ParserFactory
import java.net.*

/**
 * This example count the number of property in the style rules (outside a
 * media rule).
 * @author Philippe Le Hegaret
 */
public class AnalyzeCss implements DocumentHandler {
    boolean inMedia = false
    boolean inStyleRule = false
    int propertyCounter = 0

    public void startMedia(SACMediaList media) throws CSSException {
        inMedia = true
    }

    public void endMedia(SACMediaList media) throws CSSException {
        inMedia = false
    }

    public void startSelector(SelectorList patterns) throws CSSException {
        if (!inMedia) {
            inStyleRule = true
            propertyCounter = 0
        }
    }

    public void endSelector(SelectorList patterns) throws CSSException {
        if (!inMedia) {
            System.out.println("Found " + propertyCounter + " properties.");
        }
        inStyleRule = false

    }

    public void property(String name, LexicalUnit value, boolean important)
    throws CSSException {
        if (inStyleRule) {
            propertyCounter++
        }
    }

    public static void main(String[] args) throws Exception {
        InputSource source = new InputSource()
        URL uri = new URL("file", null, -1, args[0])
        InputStream stream = uri.openStream()

        source.setByteStream(stream)
        source.setURI(uri.toString())
        Parser parser = ParserFactory.makeParser()

        parser.setDocumentHandler(new AnalyzeCss())
        parser.parseStyleSheet(source)
        stream.close()
    }

    @Override
    void startDocument(InputSource source) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void endDocument(InputSource source) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void comment(String text) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void ignorableAtRule(String atRule) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void namespaceDeclaration(String prefix, String uri) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void startPage(String name, String pseudo_page) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void endPage(String name, String pseudo_page) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void startFontFace() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void endFontFace() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

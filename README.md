# Description #

Basis for CSS optimization.  Start by trying to analyzing a CSS file.


# ToDo #

* count CSS selectors
* count CSS properties, especially most frequent properties
* filter CSS properties
* order CSS properties ala [CSS Comb](http://csscomb.com/)
* explore phloc-css; more complicated than desired

# Done #

* explore [OBSP CSS Parser](https://github.com/corgrath/osbcp-css-parser)
    * learned how to import dependencies from GitHub projects
    * jUnit tests in src/test/groovy/com/github/itgumby/OsbcpParseTest
    * simple, clean interface
    * *unsuitable* for media queries
* explore CssParser (implementation of SAC)
    * jUnit tests in src/test/groovy/com/github/itgumby/ParseCssTest.groovy
    * example in src/main/groovy/com/github/itgumby/analyzeCss/ParseExample.groovy
    * both based on code gleaned from [StackOverflow](http://stackoverflow.com/questions/1513587/looking-for-a-css-parser-in-java)
    * *unsuitable* for `@media` media selectors
* explore [W3C SAC](http://www.w3.org/TR/SAC/DemoSAC.java)
    * *unsuitable* SAC has no implementation, just an interface


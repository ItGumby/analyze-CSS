# Description #

Basis for CSS optimization.  Start by trying to analyzing a CSS file.


# ToDo #

* count CSS selectors
* count CSS properties, especially most frequent properties

# Done #

* explore CssParser (implementation of SAC)
    * jUnit tests in src/test/groovy/com/github/itgumby/ParseCssTest.groovy
    * example in src/main/groovy/com/github/itgumby/analyzeCss/ParseExample.groovy
    * both based on code gleaned from [StackOverflow](http://stackoverflow.com/questions/1513587/looking-for-a-css-parser-in-java)
    * may not be suitable for `@media` media selectors
* explore W3C SAC
    * http://www.w3.org/TR/SAC/DemoSAC.java
    * unsuitable; SAC is interface only


# Theo's Ark 
## APCS Final Project

[Javadocs](https://theoyochum.github.io/APCS-Final-Project/index-all.html)

[Prototyping Document](https://theoyochum.github.io/APCS-Final-Project/package-summary.html)

[UML Diagram](https://github.com/TheoYochum/APCS-Final-Project/blob/main/UMLDiagrams/APCS%20Final%20UML.pdf)

Group Members: Theodore Yochum, Akram Khalifa

Target: The main goal of this project is to replicate as many functions as possible that are found on a standard graphing/scientific calculator. The majority of it will be utilizing the terminal and be written in plain java, using processing to graph the equations. It will have the ability to store and manipulate variables, including standard number formats, int, float, fraction, and simple data structures such as matrices, equations, lists, and points. The tentative plan for graphing is for the equations to be declared in the command line, and then call a processing executable to graph them, with manipulation available. The rest of the functions will all be available through parsing of the terminal inputs.

End Product: Most of the target was achieved, many standard functions are not only accessible but manually implemented. All interactions are through the command line and it is possible to graph all equations that can also be evaluated. Storage and printing of variables works as intended, terminal interactions are clean but could be spruced up given more time and effort. It was not extensively tested but as long as you are acting as intended every function operates predictibly. Notable features inclue: the ability to graph any expression given its terms are implemented; matrix operations including inverse, rref was not done; a backend implementation of continued fraction approximations of decimals to fractions with variable percision, could be brought to frontend with more infrastructure; a fully developed JavaDoc hosted on github pages.

Compile/Run Instructions: Use the following command line codes in the `/project` subdirectory:
First Time Windows: `javac -classpath "../Libraries/core.jar;../Project" *.java && java -classpath "../Libraries/core.jar;../Project" Calculator`

First Time Mac/Linux: `javac -classpath "../Libraries/core.jar:../Project" *.java && java -classpath "../Libraries/core.jar:../Project" Calculator`

Later Runs Windows: `java -classpath "../Libraries/core.jar;../Project" Calculator`

Later Runs Mac/Linux: `java -classpath "../Libraries/core.jar:../Project" Calculator`

Typing help gives you a list of the functions and variable types as well as a description of syntax. Reccomendations would be to declare a few variables, fractions, ints, floats, and evaluate some expressions with them as values. Declare a square matrix of any size, find and store the inverse and then multiply the two, you should be given an identity matrix. Write out one or more equations and then graph them, use left and right click to zoom in and out.

## Development Logs

**5/23/22** 

Akram: I created basic skeletons for our 4 main classes.

**5/24/22** 

Theo: Built basic strucutre and documentation of variables and worked on a conceptual but unrefined basis for the calculator.

**5/25/22** 

Akram: Worked on the Statistics class, created toStrings for Int and Float classes.

**5/26/22** 

Akram: Reworked Statistics class to make it return new objects and not edit the old ones. Refined methods in other classes to better work with each other 

Theo: Added all trig functions and series implementations of them

**5/27/22**

Akram: Added documentation for Number class, made a bunch of changes to what certain functions return, made two seperate copy methods for Number and Variable.

**5/30/22** 

Akram: Made a working exponent function that worked with only int powers. New functions for Fraction Researched lots of math theorems to make sure my code was accurate.

Theo: Refined the trig functions and created a rudimentary expression parser for demonstration, likely adapted for regex in the future

**5/31/22** 

Akram: Created a scientific notation converter, created accurate ln function, and e implemntation of exp. 

Theo: Simplified and improved the parser, uses a regex expression for parentheses and recursively evaluates them

**6/1/22** 

Akram: Honed in accuracy of e and ln functions with for loops.

Theo: Created Matrix functions, generally functional, not all functions made, need to be translated to using Fractions for easy rational decimals

**6/2/22**

Akram: Created LCM function and GCD function.

**6/3/22** 

Akram: Started stats switch statement.

Theo: Experimented with continued fractions interoperability with fractions, did not finish still need to figure out decimal to Fraction

**6/5/22** 

Theo: Created semi-functional approximation of floats into Fractions

**6/6/22** 

Akram: Finished switch statement for stats function in parser. Sqrt now returns float.

Theo: Formatted and structured the decimal to fraction approximation with general documentation

**6/7/22**

Akram: Documentation for statistics, geometric-arithmetic mean calculator.

**6/10/22**

Akram: New doc for lcm, played around with processing in a java file.

**6/11/22**

Theo: Created basics of graph class and figured out compile instructions

Akram: Working positive x axis on the Graph class using Theo's new equation class.

**6/12/22**

Theo: Built up calculator to route to all desired and built functions

Akram: Added all constructors and functions for Graph, made sure zoom and scaling works, fixed edge cases for certain graphs. Made sure pow could handle any kind of value. Wrote doc for the rest of the files.

**6/13/22**

Thakram: Compiled a working graphing calculator. Created Javadoc, updated UML and Prototype.

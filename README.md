# <p align="center"> Theo's Ark </p>
## <p align="center"> APCS Final Project </p>

Group Members: Theodore Yochum, Akram Khalifa

The main goal of this project is to replicate as many functions as possible that are found on a standard graphing/scientific calculator. The majority of it will be utilizing the terminal and be written in plain java, using processing to graph the equations. It will have the ability to store and manipulate variables, including standard number formats, int, float, fraction, and simple data structures such as matrices, equations, lists, and points. The tentative plan for graphing is for the equations to be declared in the command line, and then call a processing executable to graph them, with manipulation available. The rest of the functions will all be available through parsing of the terminal inputs.

[Prototyping Document](https://docs.google.com/document/d/180BusO_vznPpkCsnz28LtAkmPrC_HMbmgoyS5ju0bSs/edit?usp=sharing)

[UML Diagram](https://github.com/TheoYochum/APCS-Final-Project/blob/main/UMLDiagrams/Initial%20Prototype.pdf)


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


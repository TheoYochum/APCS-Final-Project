# <p align="center"> Theo's Ark </p>
## <p align="center"> APCS Final Project </p>

Group Members: Theodore Yochum, Akram Khalifa

The main goal of this project is to replicate as many functions as possible that are found on a standard graphing/scientific calculator. The majority of it will be utilizing the terminal and be written in plain java, using processing to graph the equations. It will have the ability to store and manipulate variables, including standard number formats, int, float, fraction, and simple data structures such as matrices, equations, lists, and points. The tentative plan for graphing is for the equations to be declared in the command line, and then call a processing executable to graph them, with manipulation available. The rest of the functions will all be available through parsing of the terminal inputs.

[Prototyping Document](https://docs.google.com/document/d/180BusO_vznPpkCsnz28LtAkmPrC_HMbmgoyS5ju0bSs/edit?usp=sharing)

[UML Diagram](https://github.com/TheoYochum/APCS-Final-Project/blob/main/UMLDiagrams/Initial%20Prototype.pdf)


**5/23/22** <p> Akram: I created basic skeletons for our 4 main classes. </p>
**5/24/22** <p> Theo: Built basic strucutre and documentation of variables and worked on a conceptual but unrefined basis for the calculator. </p>
**5/25/22** <p> Akram: Worked on the Statistics class, created toStrings for Int and Float classes. </p>
**5/26/22** <p> Akram: Reworked Statistics class to make it return new objects and not edit the old ones. Refined methods in other classes to better work with each other </p>
<p> Theo: Added all trig functions and series implementations of them </p>
**5/30/22** <p> Theo: Refined the trig functions and created a rudimentary expression parser for demonstration, likely adapted for regex in the future </p>
**5/31/22** <p> Theo: Simplified and improved the parser, uses a regex expression for parentheses and recursively evaluates them </p>

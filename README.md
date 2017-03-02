
# ShortestPath
This program is an implementation of a the BellMan-Ford Algorithm:

The program reads from a text file with the following format for the vertices and edges.

14 // This number represents the total vertices to be added.

WA // The following single strings are the vertices that belong to the graph.


CA1
CA2
UT
CO
TX
NE
IL
PA
GA
TR
NY
NJ
DC
WA CA1 1050 // The following are the edges with their respective source and target vertex and the number is 
WA CA2 1500 //the weight associated with that vertex.
WA IL 2400
CA1 WA 1050
CA1 UT 750
CA1 CA2 600
CA2 WA 1500
CA2 CA1 600
CA2 TX 1800
UT CA1 750
UT TR 1950
UT CO 600
CO UT 600
CO TX 1200
CO NE 600
TX CA2 1800
TX CO 1200
TX GA 1050
TX DC 1800
NE CO 600
NE IL 750
NE GA 1350
IL WA 2400
IL NE 750
IL PA 750
PA IL 750
PA GA 750
PA NY 300
PA NJ 300
GA TX 1050
GA NE 1350
GA PA 750
TR UT 1950
TR NY 600
TR NJ 750
NY PA 300
NY TR 600
NY DC 300
NJ PA 300
NJ TR 750
NJ DC 150
DC TX 1800
DC NY 300
DC NJ 150

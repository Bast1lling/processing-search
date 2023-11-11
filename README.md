# processing-search
using java processing [https://processing.org/](URL) to visualize common search algorithms

This is a demo of search algorithms proposed by "Fundamentals of AI" lecture.
It uses processing-library to visualize the different algorithms.

# setup:
1) download the stable 3.3.7 processing .jar:
[https://mavenlibs.com/jar/file/org.processing/core](URL)
2) If you use IntellJ, go to File->ProjectStructure->Modules->Dependencies
3) click on *+* and select the jar, add it to the project (Apply!)
4) If you use Visual Studio then good luck. But it should work in a similar way

# important classes:

Under *Visualization/SearchSketch*, a processing sketch is included whith a main() to execute.
It creates a colored board with one black tile.
If the user clicks on any other tile and activates visualization by pressing SPACE, the current search algorithm is shown.
It always tries to find the black tile, but several implementations can be chosen in SearchSketch.

Under *Basics/Board*, several methods are implemented, which initialize a board's tiles with colors

Under *Search/Board*, all search-algorithm implementations can be found

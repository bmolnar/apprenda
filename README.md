# apprenda
Development exercise for Apprenda candidacy.

## Description

This exercise implements a Rectangle class with three methods,

1. intersection()

1. contains()

1. isAdjacentTo()

which satisfy the corresponding requirements of the problem description.

In addition to representing 2-D rectangular regions, this object can also
be used to represent the following degenerate cases:

1. If either the height or width are negative, the object represents an empty set.
This condition can be tested by calling the isEmpty() method.

1. If both height and width are zero, the region represented is a point.
This condition can be tested by calling the isPoint() method.

1. If the width is zero and the height is positive, the region represented is a vertical line segment.
This condition can be tested by calling the isVerticalLine() method.

1. If the height is zero and the width is positive, the region represented is a horizontal line segment.
This condition can be tested by calling the isHorizontalLine() method.


## Building

To build (on Windows), run:

$ gradlew.bat build

## Unit tests

To run unit tests (on Windows), run:

$ gradlew.bat test


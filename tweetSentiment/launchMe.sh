#!/bin/bash

classifierPath=
mapper=
reducer=
hadp=
streamingApi=
inputPath=
outputPath=
##########################################


$hadp jar $streamingApi -mapper $mapper -reducer $reducer -input $inputPath -output $outputPath -file $mapper -file $reducer -file $classifierPath
#!/bin/bash

cPATH=

mapper=
filter=
reducer=
hadp=
streamingApi=
inputPath=
outputPath=
topics=london madrid moscow paris rome

###################################################################

$hadp jar $streamingApi -file $mapper -file $reducer -file $cPATH -mapper $filter  -reducer $reducer -input $inputPath -output $outputPath
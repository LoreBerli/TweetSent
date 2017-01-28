# TweetSent
Parallel Computing assigment - 2017 - Università degli studi di Firenze.

Twitter sentiment analysis with Hadoop.

## Java Version
To use the java version you need to edit the config.properties file inside  *src/main/resources*

---
<code>

pathToClassifier= #path to a serialized LMClassifier


inputPath= #input files path


outputPath= #output files path
</code>
---
Then:
<code>
java SentimentTweet.java
</code>


Optionally the in/out paths can be passed as arguments:

<code>
java SentimentTweet.java /../inputPath  /../outPath
</code>

These arguments ovveride the config file.



## Python Version
Python version requires Hadoop.
To launch it edit *launch.sh* first:

---
<code>
classifierPath=#path to pickled nltk classifier

mapper=#mapper py path

reducer=#reducer.py path

hadp=#path to bin/hadoop

streamingApi=# path to hadoop-streaming-2.7.3.jar

inputPath=# input files path

outputPath=# outputfiles path


$hadp jar $streamingApi -mapper $mapper -reducer $reducer -input $inputPath -output $outputPath -file $mapper -file $reducer -file $classifierPath
</code>

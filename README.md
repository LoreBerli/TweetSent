# TweetSent
Parallel Computing assigment - 2017 - **Università degli studi di Firenze**.

###Twitter sentiment analysis with Hadoop.
The project is implemented both in a Java and a Python version, both versions rely on a preclassified sentimentClassifier (a serialized LMClassifier from lingpipe for Java and a pickled nltk.SentimentAnalyzer for the Python version).
The input data must be in the form of one or more text files where each line is a single tweet.


## Java Version
To use the java version you need to edit the config.properties file inside  *src/main/resources*

---
<code>
pathToClassifier= #path to classifier

inputPath= #input path

outputPath= #output path

topics= #space separated topics

trainPositiveFile=

trainNegativeFile=

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
To launch it edit *launchMe.sh* first:

---
<code>
classifierPath=#path to pickled nltk SentimentAnalyzer

mapper=#mapper py path

reducer=#reducer.py path

hadp=#path to bin/hadoop

streamingApi=# path to hadoop-streaming-2.7.3.jar

inputPath=# input files path

outputPath=# outputfiles path


$hadp jar $streamingApi -mapper $mapper -reducer $reducer -input $inputPath -output $outputPath -file $mapper -file $reducer -file $classifierPath
</code>

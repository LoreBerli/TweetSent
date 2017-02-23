#!/usr/bin/python3
import pickle
import sys
from nltk import *
import parsePath
import utils


stemmer=PorterStemmer()
pi=open(parsePath.parsePath(), 'rb')
snt=pickle.load(pi)
topics=parsePath.parseTopics()
utils.outTimes()
for l in sys.stdin:
    for t in topics:
        fix=l.lower().strip("\n").split(" ")
        if(t in fix):
            wrds=fix
            wrds=[stemmer.stem(i) for i in wrds]
            topic = t
            print(snt.classify(wrds[0:-1])+" "+topic)

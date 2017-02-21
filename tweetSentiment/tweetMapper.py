#!/usr/bin/python3
import pickle
import sys
from nltk import *
import parsePath

stemmer=PorterStemmer()
pi=open(parsePath.parsePath(), 'rb')
snt=pickle.load(pi)
for l in sys.stdin:
    wrds=l.strip("\n").lower().split(" ")
    wrds=[stemmer.stem(i) for i in wrds]
    topic = wrds[-1].strip(" ")
    print(snt.classify(wrds[0:-1])+" "+topic)

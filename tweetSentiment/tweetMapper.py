#!/usr/bin/python3
import pickle
import sys

import parsePath

pi=open(parsePath.parsePath(), 'rb')
snt=pickle.load(pi)
for l in sys.stdin:
    wrds=l.strip("\n").lower().split(" ")
    topic = wrds[-1]
    print(snt.classify(wrds[0:-1])+" "+topic)

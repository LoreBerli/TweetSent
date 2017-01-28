#!/usr/bin/python3
import pickle
import sys

import parsePath

pi=open(parsePath.parsePath(), 'rb')
#pi=open(args[1],'rb')
snt=pickle.load(pi)
for l in sys.stdin:
    wrds=l.lower().split(" ")
    print(snt.classify(wrds))

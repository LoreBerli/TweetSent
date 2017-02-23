#!/usr/bin/python3
import sys
import utils

###################################
NEG=0
POS=4
###################################

def addTopic(top):
    tot[top]=[0,0]

def addPolarity(top,pol):
    if(pol=='0'):
        tot[top][0]+=1
    else:
        tot[top][1]+=1

def prettyPrint():
    for t in tot:
        ratio=1 if (tot[t][0]==0) else tot[t][1]/float(tot[t][0])
        print(t+":"+str(tot[t])+" = "+str(ratio))
###################################

tot={}
for l in sys.stdin:
    total=l.strip("\n").split(" ")
    topic = total[-1]
    pol = total[0]
    if(tot.get(topic)==None):
        addTopic(topic)
    addPolarity(topic,pol)

utils.outTimes()
prettyPrint()


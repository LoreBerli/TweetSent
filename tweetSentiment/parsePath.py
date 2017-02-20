import re

def parsePath():
    #imbarazzante
    lnc = open("launch.sh",'r').read()
    pat = re.compile("classifierPath=.*\n")
    res = re.search(pat,lnc)
    return res.group(0).strip("classifierPath=\"").strip("\n")

def parseTopics():
    lnc = open("launch.sh",'r').read()
    pat = re.compile("topics=.*\n")
    res = re.search(pat,lnc)
    stuff=res.group(0).strip("topics=").strip("\"").strip("\n").split(" ")
    return stuff

def testMe():
    print(parsePath())
    print(parseTopics())
import re

def parsePath():

    lnc = open("launch.sh",'r').read()
    pat = re.compile("cPATH.*")
    res = re.search(pat,lnc)
    return res.group(0)[6:].strip("\n")

def parseTopics():
    lnc = open("launch.sh",'r').read()
    pat = re.compile("topics=.*\n")
    res = re.search(pat,lnc)
    stuff=res.group(0).strip("topics=").strip("\"").strip("\n").split(" ")
    return stuff

def testMe():
    print(parsePath())
    print(parseTopics())
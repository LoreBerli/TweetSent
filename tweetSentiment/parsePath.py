import re

def parsePath():
    #imbarazzante
    lnc = open("launch.sh",'r').read()
    pat = re.compile("classifierPath=.*\n")
    res = re.search(pat,lnc)
    return res.group(0).strip("classifierPath=\"")[:-2]
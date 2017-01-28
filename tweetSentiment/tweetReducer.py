#!/usr/bin/python3
import sys

tot={}
for l in sys.stdin:
    l=l.strip("\n").strip("")
    if(tot.get(l)==None):
        tot[l]=1
    else:
        tot[l]=tot[l]+1#tot[l[1]]

print(tot)

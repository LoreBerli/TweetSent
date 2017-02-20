import csv
fieldNames=['pol','id','date','query','user','text']

def createFiles(filePath):
    dta = open(filePath,"r",encoding='latin1')
    posOut=open("positive.csv","w+")
    negOut=open("negative.csv","w+")
    reader=csv.DictReader(dta,fieldnames=fieldNames)
    posWrt=csv.DictWriter(posOut,fieldNames)
    negOut=csv.DictWriter(negOut,fieldNames)
    for row in reader:
        if(row['pol']=='0'):
            negOut.writerow(row)
        elif(row['pol']=='4'):
            posWrt.writerow(row)

def preprocess(data):
    wrds=data['text'].lower().split(" ")
    pol=data['pol']
    return (wrds,pol)

def getData(filePathP,filePathN,sz):
    posData = open(filePathP,"r")
    negData = open(filePathN, "r")
    posReader=csv.DictReader(posData,fieldnames=fieldNames)
    negReader = csv.DictReader(negData, fieldnames=fieldNames)
    tweets=[]
    for p in range(0,sz):
        tweets.append(preprocess(posReader.__next__()))
        tweets.append(preprocess(negReader.__next__()))
    return tweets

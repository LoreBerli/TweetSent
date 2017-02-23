from nltk.sentiment import SentimentAnalyzer
from nltk.classify import NaiveBayesClassifier
import nltk.sentiment.util
import csv
import pickle
from nltk.stem import *
from nltk.stem.porter import *

fieldNames=['pol','id','date','query','user','text']
LOAD=False

#TODO REFACTOR EVERYTHING


def createFiles(filePath):
    dta = open(filePath,"r",encoding='latin1')
    posOut=open("positive.csv","w+")
    negOut=open("negative.csv","w+")
    reader=csv.DictReader(dta,fieldnames=fieldNames)
    posWrt=csv.DictWriter(posOut,fieldNames)
    negOut=csv.DictWriter(negOut,fieldNames)
    for row in reader:
        if  (row['pol']=='0'):
            negOut.writerow(row)
        elif(row['pol']=='4'):
            posWrt.writerow(row)

def singleFile():
    print("single File")
    dta = open("data/trainT.csv", "r",encoding='latin1')
    reader = csv.DictReader(dta, fieldnames=fieldNames)
    out=open("outF.txt",'w+')
    for row in reader:
            out.write(row['text'])
    out.close()

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

def getTest(filePath):
    dta=open(filePath,"r")
    reader=csv.DictReader(dta,fieldnames=fieldNames)
    tweets=[]
    for r in reader:
        if(r['pol'] !='2'):
            tweets.append(preprocess(r))
    return tweets

def stem(stemmer,wrds):
    stemd=[stemmer.stem(w) for w in wrds]
    return stemd


def preprocess(data):
    stemmer = PorterStemmer()
    wrds=stem(stemmer,data['text'].lower().split(" "))
    pol=data['pol']
    return (wrds,pol)

def getTrainer():

    if(LOAD==False):
        dic = getData("/home/cioni/git/sentimentw/inputFolder/positive.csv","/home/cioni/git/sentimentw/inputFolder/negative.csv",5000)
        train = dic
        snt = SentimentAnalyzer()
        wrds= snt.all_words(dic,True)
        feat=snt.unigram_word_feats(wrds,min_freq=3)
        snt.add_feat_extractor(nltk.sentiment.util.extract_unigram_feats,unigrams=feat)
        train = snt.apply_features(train)
        trainer=NaiveBayesClassifier.train
        classifier=snt.apply_features(train,True)
        snt.train(trainer,train)
        clFile=open("classifierSmall2.pickle","wb+")
        pickle.dump(snt,clFile)
        return snt
    else:
        load_cls=open("classifier.pickle","rb")
        snt =pickle.load(load_cls)
        return snt
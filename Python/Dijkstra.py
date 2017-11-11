from functools import reduce

import os
import sys
import psutil
import time

def createGraph():
    edges = {0:[(1,3),(2,4)],
             1:[(0,3),(3,5),(4,6)],
             2:[(0,4),(3,2),(6,7)],
             3:[(1,5),(2,2),(4,1),(7,22)],
             4:[(1,6),(3,1),(5,6)],
             5:[(4,6),(7,8)],
             6:[(2,7),(7,10)],
             7:[(3,22),(5,8),(6,10)]}
    graph = {'edges':edges}
    return graph

def findShortestDistances(graph, index):
    edges = graph['edges']

    shortestPaths = {index:0}
    calculated = set()
    currentIndex = index

    while True:
        calculated.add(currentIndex)
        needToVisit = set()
        for edgePair in edges[currentIndex]:
            if edgePair[0] not in calculated:
                needToVisit.add(edgePair)

        for edgePair in needToVisit:
            if edgePair[0] not in shortestPaths or shortestPaths[currentIndex] + edgePair[1] < shortestPaths[edgePair[0]]:
                shortestPaths[edgePair[0]] = shortestPaths[currentIndex] + edgePair[1]

        possibleNextNodes = set(shortestPaths.keys()) - calculated

        if not possibleNextNodes:
            break
        else:
            currentIndex = reduce(lambda x, y : x if x[1] < y[1] else y,
                map(lambda x : (x, shortestPaths[x]), possibleNextNodes))[0]

    return shortestPaths

def calc():
    graph = createGraph()
    a = time.clock()
    print(findShortestDistance(graph, 0))
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

calc()

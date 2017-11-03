import os
import sys
import psutil
import time

def createGraph():
    nodes = [0,1,2,3]
    edges = {0:[(1,2),(2,3),(3,8)],1:[(0,3),(2,4),(3,9)],2:[(0,5),(1,3),(3,2)],3:[(0,2),(1,1),(2,3)]}
    graph = {'nodes':nodes,'edges':edges}
    return graph

def findShortestDistance(graph, index):
    nodes = graph['nodes']
    edges = graph['edges']

    shortestPaths = {index:0}
    shouldCalculate = set()
    calculated = set()
    currentIndex = index

    while True:
        calculated.add(currentIndex)
        needToVisit = set()
        for edgePair in edges[currentIndex]:
            if edgePair[0] not in calculated:
                needToVisit.add(edgePair)
                shouldCalculate.add(edgePair[0])

        for edgePair in needToVisit:
            if edgePair[0] not in shortestPaths or shortestPaths[currentIndex] + edgePair[1] < shortestPaths[edgePair[0]]:
                shortestPaths[edgePair[0]] = shortestPaths[currentIndex] + edgePair[1]

        if shouldCalculate:
            currentIndex = shouldCalculate.pop()
        else:
            break

    return shortestPaths

def calc():
    graph = createGraph()
    a = time.clock()
    print(findShortestDistance(graph, 0))
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

calc()

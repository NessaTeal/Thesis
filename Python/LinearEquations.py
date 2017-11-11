from copy import deepcopy

import os
import sys
import psutil
import time

def calc():
    matrix = [[1,1,1],[0,2,5],[2,5,-1]]
    vector = [6,-4,27]

    a = time.clock()
    print(getAnswer(matrix, vector))
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

def getAnswer(matrix, vector):
    determinant = getDeterminant(matrix)
    adjugate = getAdjugate(matrix)
    multiplied = multiplyMatrixAndVector(adjugate, vector)
    answer = list(map(lambda x : x / determinant, multiplied))

    return answer

def getMinor(i, j, matrix):
    matrix = deepcopy(matrix)

    del matrix[i]

    for x in range(len(matrix)):
        del matrix[x][j]

    return (-1) ** (i + j) * getDeterminant(matrix)

def getDeterminant(matrix):
    if len(matrix) == 2:
        a = matrix[0][0]
        b = matrix[0][1]
        c = matrix[1][0]
        d = matrix[1][1]
        return a * d - b * c
    else:
        return sum(map(lambda y : matrix[0][y] * getMinor(0, y, matrix), range(len(matrix))))

def getAdjugate(matrix):
    adjugate = []

    for y in range(len(matrix)):
        adjugateRow = []
        for x in range(len(matrix)):
            adjugateRow.append(getMinor(x,y,matrix))
        adjugate.append(adjugateRow)

    return adjugate

def multiplyMatrixAndVector(matrix, vector):
    return list(map(lambda x : sum([(a * b) for (a,b) in zip(x, vector)]), matrix))

calc()

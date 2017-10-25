import os
import sys
import psutil
import time

def summ(n):
    sum(range(n))

def calc():
    a = time.clock()
    print(summ(1000000000))
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

calc()

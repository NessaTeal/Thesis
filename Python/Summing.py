import os
import sys
import psutil
import time

def summ(n):
    return sum(range(n + 1))

def calc():
    try:
        n = int(sys.argv[1])
    except IndexError:
        print("There is no argument supplied")
        return
    except ValueError:
        print("Argument is not an integer")
        return

    a = time.clock()
    print(summ(n))
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

calc()

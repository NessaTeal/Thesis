import os
import sys
import psutil
import time

def fib(n):
    if n == 1 or n == 2:
        return 1
    else:
        return fib(n - 1) + fib(n - 2)

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
    fib(n)
    print(time.clock() - a)
    process = psutil.Process(os.getpid())
    print(process.memory_info().peak_wset / 1024 / 1024)

calc()

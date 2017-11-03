from Expressions import *

import os
import sys
import psutil
import time

def calc():
	a = time.clock()

	first = Product(X(), Power(E(), Sum(X(), Const(1))))
	print(first.derive().simplify())

	print(time.clock() - a)
	process = psutil.Process(os.getpid())
	print(process.memory_info().peak_wset / 1024 / 1024)

calc()

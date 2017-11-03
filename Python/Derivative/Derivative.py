from Expressions import *

def calc():
	first = Product(X(), Power(E(), Sum(X(), Const(1))))
	print(first.derive())
	print(first.derive().simplify())

calc()

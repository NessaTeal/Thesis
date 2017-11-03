class Const(object):
	def __init__(self, a):
		self._a = a

	def derive(self):
		return Const(0)

	def simplify(self):
		return self

	def __str__(self):
		return str(self._a)

	def __eq__(self, other):
		return isinstance(other, self.__class__) and self._a == other._a

	def __ne__(self, other):
		return not self == other

class Cos(object):
	def __init__(self, a):
		self._a = a

	def derive(self):
		return Product(Neg(Sin(self._a)), self._a.derive())

	def simplify(self):
		self._a = self._a.simplify()
		return self

	def __str__(self):
		return "Cos(" + str(self._a) + ")"

class Division(object):
	def __init__(self, a, b):
		self._a = a
		self._b = b

	def derive(self):
		newA = Sub.Sub(Product(self._a.derive(), self._b), Product.Product(self._a, self._b.derive()))
		newB = Power.Power(self._b, Const.Const(2))
		return Division(newA, newB)

	def simplify(self):
		aSimple = self._a.simplify()
		if aSimple == Const(0):
			return Const(0)
		else:
			self._a = aSimple
			self._b = self._b.simplify()
			return self

	def __str__(self):
		return "(" + str(self._a) + " / " + str(self._b) + ")"

class E(object):
	def derive(self):
		return Const(0)

	def simplify(self):
		return self

	def __str__(self):
		return "e"

class Log(object):
	def __init__(self, a):
		self._a = a

	def derive(self):
		return Product(Division(Const(1), self._a), self._a.derive())

	def simplify(self):
		if type(self._a) == type(E()):
			return Const(1)
		else:
			newA = self._a.simplify()
			return Log(newA)

	def __str__(self):
		return "Log(" + str(self._a) + ")"

class Neg(object):
	def __init__(self, a):
		self._a = a

	def derive(self):
		self._a = self._a.derive()
		return self

	def simplify(self):
		self._a = self._a.simplify()
		return self

	def __str__(self):
		return "-" + str(self._a)

class Power(object):
	def __init__(self, a, b):
		self._a = a
		self._b = b

	def derive(self):
		if type(self._a) == type(E()):
			return Product(Power(self._a, self._b), self._b.derive())
		else:
			return Product(Product(self._b, Log(self._a)).derive(), Power(self._a, self._b))

	def simplify(self):
		aSimple = self._a.simplify()
		bSimple = self._b.simplify()
		if bSimple == Const(0):
			return Const(1)
		elif bSimple == Const(1):
			return aSimple
		else:
			self._a = aSimple
			self._b = bSimple
			return self

	def __str__(self):
		return str(self._a) + " ^ " + str(self._b)

class Product(object):
	def __init__(self, a, b):
		self._a = a
		self._b = b

	def derive(self):
		return Sum(Product(self._a.derive(), self._b), Product(self._a, self._b.derive()))

	def simplify(self):
		aSimple = self._a.simplify()
		bSimple = self._b.simplify()

		if aSimple == Const(0) or bSimple == Const(0):
			return Const(0)
		elif aSimple == Const(1):
			return bSimple
		elif bSimple == Const(1):
			return aSimple
		else:
			self._a = aSimple
			self._b = bSimple
			return self

	def __str__(self):
		return "(" + str(self._a) + " * " + str(self._b) + ")"

class Sin(object):
	def __init__(self, a):
		self._a = a

	def derive(self):
		return Product(Cos(self._a), self._a.derive())

	def simplify(self):
		self._a = self._a.simplify()
		return self

	def __str__(self):
		return "Sin(" + str(self._a) + ")"

class Sub(object):
	def __init__(self, a, b):
		self._a = a
		self._b = b

	def derive(self):
		return Sub(self._a.derive(), self._b.derive())

	def simplify(self):
		aSimple = self._a.simplify()
		bSimple = self._b.simplify()
		if aSimple.equals(Const(0)):
			return Neg(bSimple)
		elif bSimple.equals(Const(0)):
			return aSimple
		else:
			self._a = aSimple
			self._b = bSimple
			return self

	def __str__(self):
		return str(self._a) + " - " + str(self._b)

class Sum(object):
	def __init__(self, a, b):
		self._a = a
		self._b = b

	def derive(self):
		return Sum(self._a.derive(), self._b.derive())

	def simplify(self):
		aSimple = self._a.simplify()
		bSimple = self._b.simplify()
		if aSimple == Const(0):
			return bSimple
		elif bSimple == Const(0):
			return aSimple
		else:
			self._a = aSimple
			self._b = bSimple
			return self

	def __str__(self):
		return "(" + str(self._a) + " + " + str(self._b) + ")"

class X(object):
	def derive(self):
		return Const(1)

	def simplify(self):
		return self

	def __str__(self):
		return "x"

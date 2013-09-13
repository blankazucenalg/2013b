'''
Created on Aug 30, 2013
Calculo de la sucesión de fibonacci
@author: Lopez Garduno Blanca Azucena
@author: Resendiz Arteaga Juan Alberto

'''

class Practica4(object):
    '''
    classdocs
    '''
    def __init__(self):  # 1 => Algoritmo Ingenuo
        self.lista = {0:0,1:1} 
    def fibonacciIterative(self,n):
        a, b = 0, 1
        for i in range(n):
            a, b = b, a + b
        return a
    def fibonacciRecursive(self,n):
        if n is 0:
            return 0
        if n is 1:
            return 1
        return self.fibonacciRecursive(n-1)+self.fibonacciRecursive(n-2)
    def fibonacciDynamic(self,n):
        if(not(n in self.lista.values())):
            self.lista[n]=self.fibonacciDynamic(n-1)+self.fibonacciDynamic(n-2);
        return self.lista[n]
    def fibonacciMatrix(self,n):
        if n < 2:
            return n
        return self.pow((1,1,0),n-1)[0]
    def mul(self,A, B):
        a, b, c = A
        d, e, f = B
        return a*d + b*e, a*e + b*f, b*e + c*f

    def pow(self,A, n):
        if n == 1:
            return A
        if n & 1 == 0:
            return self.pow(self.mul(A, A), n//2)
        else:
            return self.mul(A, self.pow(self.mul(A, A), (n-1)//2))
prac =  Practica4()
try:
    print ("Selecciona una opcion para ejecutar ")
    print ("\t 1) Algoritmo Iterativo")
    print ("\t 2) Algoritmo Recursivo")
    print ("\t 3) Programacion Dinamica")
    print ("\t 4) Multiplicacion de Matrices")
    opcion = int(input('Opcion:'))
    while(not(isinstance(opcion, int))):
        print ("Selecciona una opcion valida :")
        opcion = int(input('Opcion:'))

    a = int(input('Introduce el numero de la serie a calcular: '))
    while(not(isinstance(a, int))):
        print ("El formato no es un numero")
        a = int(input('Introduce el numero de la serie a calcular: '))
    if(opcion is 1):
        print(">>> ",prac.fibonacciIterative(a))
    if(opcion is 2):
        print(">>> ",prac.fibonacciRecursive(a))
    if(opcion is 3):
        print(">>> ",prac.fibonacciRecursive(a))
    if(opcion is 4):
        print(">>> ",prac.fibonacciMatrix(a))
except Exception as exception:
    print ("Exception ", exception)
from numpy import *
'''
Created on Sep 26, 2013


Multiplicacion de matrices(metodo iterativo, recursivo y Strassen)
@author: Lopez Garduno Blanca Azucena
@author: Resendiz Arteaga Juan Alberto

'''


class Practice5(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        pass
    def initArrays(self, size):
        self.tam = size
        self.MatrixA = arange((size * size)).reshape((size, size))
        self.MatrixB = arange((size * size)).reshape((size, size))
        self.MatrixC = zeros((size * size)).reshape((size, size))
    def iterativeSolution(self):
        for i in range(self.tam):
            for j in range(self.tam):
                for k in range(self.tam):
                    self.MatrixC[i, j] += self.MatrixA[i, k] * self.MatrixB[k, j]
        # return self.MatrixC
    def recursiveSolution(self, matrizA, matrizB):
        half = matrizA.shape[0]/2 #las dimensiones son iguales
        totalSize = matrizA.shape[0]
        if(half is 0):
            return matrizA[0,0]*matrizB[0,0]
        else:
            print("--------------------------------------")
            a = matrizA[0:half,0:half]
            b = matrizA[0:half,half:totalSize]            
            c = matrizA[half:totalSize,0:half]
            d = matrizA[half:totalSize,half:totalSize]

            #
            e = matrizB[0:half,0:half]            
            f = matrizB[0:half,half:totalSize]
            g = matrizB[half:totalSize,0:half]
            h = matrizB[half:totalSize,half:totalSize]
            #
            self.MatrixC[0:half,0:half]=(dot(a,e)+dot(b,g))
            self.MatrixC[half:totalSize,0:half] = (dot(c,e)+dot(d,g))
            self.MatrixC[0:half,half:totalSize] = (dot(a,f)+dot(b,h))
            self.MatrixC[half:totalSize,half:totalSize] = (dot(c,f)+dot(d,h))
    def strassenSolution(self,matrizA,matrizB):
        half = matrizA.shape[0]/2 #las dimensiones son iguales
        totalSize = matrizA.shape[0]
        if(half is 0):
            return matrizA[0,0]*matrizB[0,0]
        else:
            a = matrizA[0:half,0:half]
            b = matrizA[0:half,half:totalSize]            
            c = matrizA[half:totalSize,0:half]
            d = matrizA[half:totalSize,half:totalSize]

            #
            e = matrizB[0:half,0:half]            
            f = matrizB[0:half,half:totalSize]
            g = matrizB[half:totalSize,0:half]
            h = matrizB[half:totalSize,half:totalSize]

            p1 = dot(a ,(f-h))
            p2 = dot((a+b),h)
            p3 = dot((c+d),e)
            p4 = dot(d,(g-e))
            p5 = dot((a+d),(e+h))
            p6 = dot((b-d),(g+h))
            p7 = dot((a-c),(e+f))
            self.MatrixC[0:half,0:half]=(p5+p4-p2+p6)
            self.MatrixC[half:totalSize,0:half] = (p3+p4)
            self.MatrixC[0:half,half:totalSize] = (p1+p2)
            self.MatrixC[half:totalSize,half:totalSize] = (p1+p5-p3-p7)
prac = Practice5()
try:
    print ("Selecciona una opcion para ejecutar ")
    print ("\t 1) Algoritmo Iterativo")
    print ("\t 2) Algoritmo Recursivo")
    print ("\t 3) Algoritmo Strassen")
    opcion = int(input('Opcion:'))
    while(not(isinstance(opcion, int))):
        print ("Selecciona una opcion valida :")
        opcion = int(input('Opcion:'))
    a = int(input('Introduce el tamano de la matriz'))
    while(not(isinstance(a, int))):
        print ("El formato no es un numero")
        a = int(input('Introduce el tamano de la matriz : '))
    if(opcion is 1):
        prac.initArrays(a)
        prac.iterativeSolution()
        print(prac.MatrixA)
        print(prac.MatrixB)
        print("----------------")
        print(prac.MatrixC)
    if(opcion is 2):
        prac.initArrays(a)
        matrizA = prac.MatrixA
        matrizB = prac.MatrixB
        prac.recursiveSolution(matrizA, matrizB)
        print(prac.MatrixA)
        print(prac.MatrixB)
        print("----------------")
        print(prac.MatrixC)
    if(opcion is 3):
        prac.initArrays(a)
        matrizA = prac.MatrixA
        matrizB = prac.MatrixB
        prac.strassenSolution(matrizA, matrizB)
        print(prac.MatrixA)
        print(prac.MatrixB)
        print("----------------")
        print(prac.MatrixC)
except Exception as exception:
    print ("Exception ", exception)

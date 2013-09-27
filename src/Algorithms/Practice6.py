'''
Created on Sep 27, 2013

Algoritmo de Ordenamiento: QuickSort
@author: Lopez Garduno Blanca Azucena
@author: Resendiz Arteaga Juan Alberto

'''


class Practice6(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        self.lista = []
    def init(self):
        self.archivo = open("arreglo.csv", 'r').read()
        for elemento in self.archivo.split(","):
            self.lista.append(int(elemento)) 
    def quickSort(self,lista):
        if(len(lista)<=1):
            return lista
        pivot = lista[-1] 
        less = []
        greater = []
        for elemento in lista:
            if elemento < pivot:
                less.append(elemento)
            if elemento > pivot:
                greater.append(elemento)
        return self.quickSort(less)+[pivot]+self.quickSort(greater)                    
                     
    def __readFile(self, fileName):
        try:
            
            return True
        except Exception as exception:
            print exception
            return False
        
start = Practice6()
archivo = "" 
try:
    print("El archivo \"arreglo.csv\" contiene la lista")            
    start.init()
    lista = start.lista
    print("Antes de ordenar : ",lista)
    listafinal = start.quickSort(lista)
    print("Despues de ordenar : ",listafinal)
except Exception as exception:
    print "Exception ", exception
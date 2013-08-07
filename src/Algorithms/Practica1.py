'''
Created on Aug 6, 2013

@author: alberto
'''

class Practica1(object):
    def __init__(self):#1 => Algoritmo Ingenuo
        self.cadena = [] 
    def getArray(self,archivo='arreglo.csv'):
        if(self.__readFile(archivo) is True):
            self.cadena = self.archivo.split(",")
            self.findPeak(len(self.cadena)/2,self.cadena)
        else:
            print "ERROR AL LEER EL ARCHIVO"
        
    def findPeak(self,middle,cadena):
        print "Cadena ",cadena," \n Mitad ",cadena[middle]
        try:
            if(cadena[middle>=cadena[middle+1]] and cadena[middle]>=cadena[middle-1]):
                print "\n Pico encontrado en ",cadena[middle]
                return
            else:
                if(cadena[middle+1]>=cadena[middle]):#Se compara el lado derecho
                    print cadena[middle+1],">=",cadena[middle]
                    cadenaAux = cadena[middle+1:]
                    self.findPeak(len(cadenaAux)/2, cadenaAux)
                    return
                else:
                    print cadena[middle+1],"<=",cadena[middle]
                    cadenaAux = cadena[:middle-1]
                    self.findPeak(len(cadenaAux)/2, cadenaAux)
                    return
        except Exception as exception:
            print "\n Array Index Out of Bounds Exception ",exception
                    
    def __readFile(self,fileName):
        try:
            self.archivo = open(fileName,'r').read()
            return True
        except Exception as exception:
            print exception
            return False
        
start = Practica1()
start.getArray()
#start.findPeak(2)
        
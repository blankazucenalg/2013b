'''
Created on Aug 6, 2013

@author: alberto
'''

class Practica1(object):
    def __init__(self):  # 1 => Algoritmo Ingenuo
        self.cadena = [] 
    def recursiveSolution(self, archivo='arreglo.csv'):
        if(self.__readFile(archivo) is True):
            self.cadena = self.archivo.split(",")
            self.findPeak(len(self.cadena) / 2, self.cadena)
        else:
            print "ERROR AL LEER EL ARCHIVO"
        
    def findPeak(self, middle, cadena):
        print "Cadena", cadena, "\nMitad", cadena[middle], "\nIndice", middle
        try:
            if(cadena[middle >= cadena[middle + 1]] and cadena[middle] >= cadena[middle - 1]):
                print "\nPico encontrado en", cadena[middle]
                return
            else:
                if(cadena[middle + 1] >= cadena[middle]):  # Se compara el lado derecho
                    print cadena[middle + 1], ">=", cadena[middle]
                    cadenaAux = cadena[middle + 1:]
                    self.findPeak(len(cadenaAux) / 2, cadenaAux)
                    return
                else:
                    print cadena[middle + 1], "<=", cadena[middle]
                    cadenaAux = cadena[:middle - 1]
                    self.findPeak(len(cadenaAux) / 2, cadenaAux)
                    return
        except Exception as exception:
            print "\nArray Index Out of Bounds Exception", exception
    def iterativeSolution(self, archivo='arreglo.csv'):
        pass                    
    def __readFile(self, fileName):
        try:
            self.archivo = open(fileName, 'r').read()
            return True
        except Exception as exception:
            print exception
            return False
        
start = Practica1()
archivo = "" 
try:
    print "Selecciona una opcion para ejecutar "
    print "\t 1) Algoritmo Ingenuo"
    print "\t 2) Algoritmo Recursivo"
    mode = int(raw_input('Opcion:'))
    while(not(isinstance(mode, int))):
        print "Selecciona una opcion valida :"
        mode = int(raw_input('Opcion:'))
    archivo = str(raw_input("Nombre del archivo (vacio para archivo por default): "))            
    if(mode is 1):
        # Algoritmo ingenuo
        if(len(archivo) > 0):
            start.iterativeSolution(archivo)
        else:
            start.iterativeSolution()
        pass
    if(mode is 2):
        # Algoritmo Recursivo
        if(len(archivo) > 0):
            start.recursiveSolution(archivo)
        else:
            start.recursiveSolution()
        pass
except Exception as exception:
    print "Exception ", exception
        

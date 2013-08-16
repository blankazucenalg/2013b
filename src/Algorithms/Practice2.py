'''
Created on Aug 14, 2013

@author: alberto
'''
class Practica2(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        self.archivo = ""
        
    def recursive(self):
        print "OK"
        self.cadena = ((1,2,3,8,7),
                       (4,2,6,9,8),
                       (7,2,8,3,5),
                       (1,2,3,-1,7))
        print "\n>>> Columnas ",len(self.cadena[0])
        print "\n>>> Filas ",len(self.cadena)
        noColumnas = len(self.cadena[0])
        noFilas = len(self.cadena)
        self.findPeak(noColumnas/2, self.cadena)
    def findPeak(self, middle,cadena):
        if(middle is 1):
            print "ES 1"
            return
        else:            
            maximoCadena = max(cadena[middle])
            indiceMaximo = cadena[middle].index(maximoCadena)
            print "Maximo %s en middle %s" % (maximoCadena,middle)
            #Comparaciones para la recursividad
            if(cadena[middle-1][indiceMaximo]>maximoCadena):
                print "Lado Izquierdo"
                self.findPeak((middle/2)-1, cadena)
                return
            elif(cadena[middle+1][indiceMaximo]>maximoCadena):
                print "Lado Derecho"
                self.findPeak(middle/2+1, cadena)
                return
            else:
                print "Pico encontrado en [%s,%s] y es %s"%(indiceMaximo,middle,maximoCadena)                   
    def __readFile(self, fileName):
        try:
            self.archivo = open(fileName, 'r').read()
            return True
        except Exception as exception:
            print exception
            return False
start = Practica2() 
try:            
    start.recursive()
except Exception as exception:
    print "Exception >>> ", exception
        
 
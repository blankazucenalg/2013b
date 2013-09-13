'''
Exponenciacion de numeros
Created on Aug 29, 2013
Lopez Garduno Blanca Azucena
Resendiz Arteaga Juan Alberto
'''

class Practica3(object):
    def iterative(self,a,n):
        resultado = 1
        for i in range(n):
            resultado *= a
        print "El resultado es: ",resultado

    def recursive(self,a,n):
        auxiliar = 1;
        if(n is 0):
            return 1
        if(n is 1):
            return a
        else:
            if(n % 2 is 0):
                auxiliar = self.recursive(a,n/2)
                return auxiliar*auxiliar
            else: 
                auxiliar = self.recursive(a, (n-1)/2)
                return auxiliar*auxiliar*a
                pass
        return
    
start = Practica3() 
try:
    print "Selecciona una opcion para ejecutar "
    print "\t 1) Algoritmo Ingenuo"
    print "\t 2) Algoritmo Recursivo"
    opcion = int(raw_input('Opcion:'))
    while(not(isinstance(opcion, int))):
        print "Selecciona una opcion valida :"
        opcion = int(raw_input('Opcion:'))

    a = int(raw_input('Introduce el numero a elevar: '))
    while(not(isinstance(a, int))):
        print "El formato no es un numero"
        a = int(raw_input('Introduce un numero a elevar: '))
    n = int(raw_input('Introduce la potencia: '))
    while(not(isinstance(n, int))):
        print "El formato no es un numero"
        n = int(raw_input('Introduce la potencia: '))
    if(opcion is 1):
        start.iterative(a,n)
    if(opcion is 2):
        potencia = start.recursive(a,n)
        print "El resultado es : ",potencia
except Exception as exception:
    print "Exception ", exception
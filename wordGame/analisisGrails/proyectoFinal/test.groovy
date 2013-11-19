import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.*
//println(([] as Queue).metaClass.methods*.name.sort().unique())
def variable = ""
Grafo g = new Grafo("Test")
for(elemento in FourLetterWord.list()){
    variable = elemento.word
    g.agregarNodo(variable)
    Nodo nodo =  g.obtenerNodo(variable)
    def listaVecinos = FourLetterWord.findAllByWordLike("%${(variable).substring(1,4)}")
    println(listaVecinos.size())
    listaVecinos.each{
        nodo.agregarVecino(it.word,1)
    }
}
/*
g.agregarNodo("r")
g.agregarNodo("s")
g.agregarNodo("t")
g.agregarNodo("u")
g.agregarNodo("v")
g.agregarNodo("w")
g.agregarNodo("x")
g.agregarNodo("y")


Nodo r = g.obtenerNodo("r")
r.agregarVecino("s", 1)
r.agregarVecino("v", 1)

Nodo  s = g.obtenerNodo("s")
s.agregarVecino("r", 1)
s.agregarVecino("w", 1)

Nodo t = g.obtenerNodo("t")
t.agregarVecino("u", 1)
t.agregarVecino("w", 1)
t.agregarVecino("x", 1)

Nodo u = g.obtenerNodo("u")
u.agregarVecino("t", 1)
u.agregarVecino("x", 1)
u.agregarVecino("y", 1)

Nodo v = g.obtenerNodo("v")
v.agregarVecino("r", 1)

Nodo w = g.obtenerNodo("w")
w.agregarVecino("s", 1)
w.agregarVecino("t", 1)
w.agregarVecino("x", 1)

Nodo x = g.obtenerNodo("x")
x.agregarVecino("t", 1)
x.agregarVecino("u", 1)
x.agregarVecino("w", 1)
x.agregarVecino("y", 1)

Nodo y = g.obtenerNodo("y")
y.agregarVecino("u", 1)
y.agregarVecino("x", 1)*/

println(" Antes de la busqueda ")
g.busquedaAmplitud("sell")
println(" Después de la busqueda ")
//println(g as JSON)
println(g.obtenerNodo("sell").getProperties())
println(g.obtenerNodo("tell").getProperties())
println ( "Antes de la ruta " )
ruta = g.ruta("tall", null)
println(" Impresión de la ruta ")
println(ruta)
/*
println(g.obtenerNombresNodos() as JSON)
println(g.obtenerNodo("tell").vecinos as JSON)
println(g.obtenerNodo("tall").vecinos as JSON)
*/
import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.*
//println(([] as Queue).metaClass.methods*.name.sort().unique())
def variable = ""
/*
println("%${variable[1]+variable[2]+variable[3]}")
println("${variable[0]}"+"%"+"${variable[2]+variable[3]}")
println("${variable[0]+variable[1]}"+"%"+"${variable[3]}")
println("${variable[0]+variable[1]+variable[2]}"+"%")
def listaVecinos = FourLetterWord.findAllByWordLikeOrWordLikeOrWordLikeOrWordLike(
        "%${variable[1]+variable[2]+variable[3]}",
        "${variable[0]}"+"%"+"${variable[2]+variable[3]}",
        "${variable[0]+variable[1]}"+"%"+"${variable[3]}",
        "${variable[0]+variable[1]+variable[2]}"+"%"
        )
println("--------------------------")
for(elemento in listaVecinos){
    println(elemento.word)
}*/

Grafo g = new Grafo("Test")
for(elemento in FourLetterWord.list()){
    variable = elemento.word
    g.agregarNodo(variable)
    Nodo nodo =  g.obtenerNodo(variable)
    def listaVecinos = FourLetterWord.findAllByWordLikeOrWordLikeOrWordLikeOrWordLike(
        "%${variable[1]+variable[2]+variable[3]}",
        "${variable[0]}"+"%"+"${variable[2]+variable[3]}",
        "${variable[0]+variable[1]}"+"%"+"${variable[3]}",
        "${variable[0]+variable[1]+variable[2]}"+"%"
        )
//    println(listaVecinos.size())
    listaVecinos.each{
        nodo.agregarVecino(it.word,1)
    }
}

println(g.busquedaDijkstra("five","pain"));

g.busquedaAmplitud("five")
//g.busquedaProfunidad("home")
ruta = g.ruta("pain", null)
println(ruta)
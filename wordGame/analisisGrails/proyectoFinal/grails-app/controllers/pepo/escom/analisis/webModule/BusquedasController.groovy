package pepo.escom.analisis.webModule
import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.*

class BusquedasController {

    def index() {
        Grafo g = new Grafo("Test")
        for(elemento in FourLetterWord.list()){
            String variable = elemento.word
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
        session.grafo = g
        render("Grafo cargado en memoria!!!!!")
    }
    def busquedaPorAmplitud(){
        session.grafo.busquedaAmplitud(params.inicio)
        def ruta = session.grafo.ruta(params.destino,null)
        render([ruta:ruta] as JSON)
    }
    def cargaRegistros(){
        def dictionary1 = new File(servletContext.getRealPath('archivos/englishWords.txt'))
        def dictionary2 = new File(servletContext.getRealPath('archivos/englishWords2.txt'))
        FourLetterWord four = null
        def list = []
        dictionary1.eachLine{ line ->
            list.add(line.toLowerCase())
        }
        println("Four line : >>>"+list.unique().size())
        /*for(elemento in list.unique()){
        try{
        four = new FourLetterWord()
        four.word = elemento
        four.save()
        }catch(Exception e){
        println("Error >> X__X" + e)
        }
        }*/
        list = []
        println("lista Vacia " + list.size())
        FiveLetterWord five = null
        dictionary2.eachLine{ line ->
            list.add(line.toLowerCase())
        }
        println("Five line : >>>"+list.unique().size())
        /*for(elemento in list.unique()){
        try{
        five = new FiveLetterWord()
        five.word = elemento
        five.save()
        }catch(Exception e){
        println("Error >> X__X" + e)
        }
        }*/
        render("OK")
    }
    def busquedaPorProfundidad(){
        session.grafo.busquedaProfunidad(params.inicio)
        def ruta = session.grafo.ruta(params.destino,null)
        println(ruta.size())
        render([ruta:ruta] as JSON)
    }
    def busquedaAEstrella(){
        render([mensaje:"ok"] as JSON)
    }
    def otraBusqueda(){
        render([mensaje:"ok"] as JSON)
    }
}

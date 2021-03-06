package pepo.escom.analisis.webModule
import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.*

class BusquedasController {
    def obtenerPalabrasAutoComplete(){
        println("${params?.like}%")
        render([lista:FourLetterWord.findAllByWordLike("${params?.like}%")] as JSON)
    }
    def respuestaCargaGrafo(){
        if(!session?.grafo){
            println("No existe el grafo!!")
            cargaGrafo()
            render("Cargado!")
            return;
        } else {
            render("Ya existe");
        }
    }
    def cargaGrafo() {
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
        //render([g:g.obtenerNodo("case"),mensaje:"OK"] as JSON)
    }
    @net.bull.javamelody.MonitoredWithSpring
    def busquedaPorAmplitud(){
        println("Amplitud")
        if(!session?.grafo){
            println("No existe el grafo!!")
            cargaGrafo()
        }
        if(params?.inicio!="" && params?.destino!=""){
            session.grafo.busquedaAmplitud(params?.inicio)
            def ruta = session.grafo.ruta(params.destino,null)
            render([ruta:ruta,error:false] as JSON)
        } else {
            render([ruta:[],error:true] as JSON)
        }
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
    @net.bull.javamelody.MonitoredWithSpring
    def busquedaPorProfundidad(){
        println("Profundidad")
        if(!session?.grafo){
            println("No existe el grafo!!")
            cargaGrafo()
        }
        session.grafo.busquedaProfunidad(params.inicio)
        def ruta = session.grafo.ruta(params.destino,null)
        render([ruta:ruta] as JSON)
    }
    @net.bull.javamelody.MonitoredWithSpring
    def busquedaAEstrella(){
        println("Estrella")
        if(!session?.grafo){
            println("No existe el grafo!!")
            cargaGrafo()
        }
        if(params?.inicio!="" && params?.destino!=""){
            def ruta = session.grafo.AStarSearch(params?.inicio,params?.destino)
            render([ruta:ruta,error:false] as JSON)
        } else {
            render([ruta:[],error:true] as JSON)
        }
    }
    @net.bull.javamelody.MonitoredWithSpring
    def busquedaDijkstra(){
        println("Dijkstra")
        if(!session?.grafo){
            println("No existe el grafo!!")
            cargaGrafo()
        }
        if(params?.inicio!="" && params?.destino!=""){
            def ruta = session.grafo.busquedaDijkstra(params?.inicio,params?.destino)
            render([ruta:ruta,error:false] as JSON)
        } else {
            render([ruta:[],error:true] as JSON)
        }
    }
}

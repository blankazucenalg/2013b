package pepo.escom.analisis.webModule
import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.FiveLetterWord
import pepo.escom.analisis.domain.FourLetterWord

class BusquedasController {

    def index() {/*
        def dictionary1 = new File(servletContext.getRealPath('archivos/englishWords.txt'))
        def dictionary2 = new File(servletContext.getRealPath('archivos/englishWords2.txt'))
        FourLetterWord four = null
        dictionary1.eachLine{ line ->
        four = new FourLetterWord()
        four.word = line
        four.save()
        }
        FiveLetterWord five = null
        dictionary2.eachLine{ line ->
        five = new FiveLetterWord()
        five.word = line
        five.save()
        }
        render("OK")
        //render([lista: FourLetterWord.list()] as JSON)
        //render("OK")
        /*
        session.mapa = [hola:"hola"]
        redirect(action:'busquedaPorAmplitud')*/
        /*
        try{

        Grafo g = new Grafo("Lista4Letras")
        for(elemento in listaPalabras1){
        g.agregarNodo(elemento)
        }
        //g.agregarNodo("Abby")
        //for(nodo0 in g.obtenerNombresNodos()){
        //Nodo parcial = g.obtenerNodo(nodo0)
        Nodo parcial = g.obtenerNodo("tell")
        def nombreNodoCortado = parcial.nombre.substring(0,0)
        listaPalabras1.each{
        if("${nombreNodoCortado}" == "${it.substring(0,0)}"){
        //println(it.substring(0,0) + " -- " + nombreNodoCortado)
        parcial.agregarVecino(it,1)
        }
        }
        //println("Nivel 1")
        //println(parcial.obtenerVecinos())
        for(nodo1 in parcial.obtenerVecinos()){
        Nodo parcial1 = g.obtenerNodo(nodo1)
        //println(parcial1)
        def nombreNodoCortado1 = parcial1.nombre.substring(0,1)
        listaPalabras1.each{
        if("${nombreNodoCortado1}" == "${it.substring(0,1)}"){
        //println(it.substring(0,1) + " -- " + nombreNodoCortado1)
        parcial1.agregarVecino(it,1)
        }
        }
        //println("Nivel 2")
        for(nodo2 in parcial1.obtenerVecinos()){
        Nodo parcial2 = g.obtenerNodo(nodo2)
        def nombreNodoCortado2 = parcial2.nombre.substring(0,2)
        listaPalabras1.each{
        if("${nombreNodoCortado1}" == "${it.substring(0,2)}"){
        //println(it.substring(0,2) + " -- " + nombreNodoCortado2)
        parcial2.agregarVecino(it,1)
        }
        }
        }
        }
        //}
        render(g.obtenerNodo("tell") as JSON)
        }catch(Exception e){
        e.printStackTrace()
        render("Error >> "+e)
            
        }   */     
        render([lista:FourLetterWord.list()])
    }
    def busquedaPorAmplitud(){
        Nodo n = new Nodo("Hola Primer Nodo")
        println(session.mapa);
        render([properties :n.getProperties(),objetoCompleto:n] as JSON)
    }
    def cargaRegistros(){
        def dictionary1 = new File(servletContext.getRealPath('archivos/englishWords.txt'))
        def dictionary2 = new File(servletContext.getRealPath('archivos/englishWords2.txt'))
        FourLetterWord four = null
        dictionary1.eachLine{ line ->
            four = new FourLetterWord()
            four.word = line
            four.save()
        }
        FiveLetterWord five = null
        dictionary2.eachLine{ line ->
            five = new FiveLetterWord()
            five.word = line
            five.save()
        }
    }
    def busquedaPorProfunidad(){
        render([mensaje:"ok"] as JSON)
    }
    def busquedaAEstrella(){
        render([mensaje:"ok"] as JSON)
    }
    def otraBusqueda(){
        render([mensaje:"ok"] as JSON)
    }
}

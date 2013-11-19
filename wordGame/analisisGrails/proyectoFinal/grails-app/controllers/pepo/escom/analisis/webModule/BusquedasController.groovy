package pepo.escom.analisis.webModule
import grails.converters.JSON
import pepo.escom.analisis.Nodo
import pepo.escom.analisis.Grafo
import pepo.escom.analisis.Utils
import pepo.escom.analisis.domain.*

class BusquedasController {

    def index() {
        //render([lista:FourLetterWord.list()])
        /*try{
            Grafo g = new Grafo("Lista4Letras")
            for(elemento in FourLetterWord.list()){
                g.agregarNodo(elemento.word)
            }
            for(nodo_0 in g.obtenerNombresNodos()){
                Nodo parcial_0 = g.obtenerNodo(nodo_0)
                def listaPalabras_0 = FourLetterWord.findAllByWordLike(parcial_0.nombre.substring(0,0));
                listaPalabras_0.each{
                    parcial_0.agregarVecino(it.word,1)
                }
                for(nodo_1 in parcial_0.obtenerVecinos()){
                    Nodo parcial_1 = g.obtenerNodo(nodo_1)
                    def listaPalabras_1 = FourLetterWord.findAllByWordLike(parcial_1.nombre.substring(0,1));
                    listaPalabras_1.each{
                        parcial1.agregarVecino(it.word,1)
                    }
                    for(nodo_2 in parcial_1.obtenerVecinos()){
                        Nodo parcial_2 = g.obtenerNodo(nodo_2)
                        def nombreNodoCortado2 = parcial2.nombre.substring(0,2)
                        def listaPalabras_2 = FourLetterWord.findAllByWordLike(parcial_2.nombre.substring(0,2));
                        listaPalabras_2.each{
                            parcial2.agregarVecino(it,1)
                        }
                    }
                }
            }
            render(g.obtenerNodo("tell") as JSON)
        }catch(Exception e){
            e.printStackTrace()
            render("Error >> "+e)
            
        }*/
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

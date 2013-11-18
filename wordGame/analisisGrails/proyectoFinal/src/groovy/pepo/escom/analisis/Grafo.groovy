package pepo.escom.analisis
import pepo.escom.analisis.*
    
/**
 *
 * @author alberto
 */
class Grafo {
    String nombre
    Integer numeroNodos
    def nodos
    def tiempo
    Grafo(String nombreNodo){
        this.nombre = nombreNodo
        this.numeroNodos = 0
        this.nodos = [:]
    }
    def agregarNodo(String nombreNodo){
        Nodo n = new Nodo(nombreNodo)
        this.nodos[nombreNodo] = n;
        this.numeroNodos = this.numeroNodos+1;
    }
    String toString(){
        return this.nombre + " NUMERO NODOS: "+this.numeroNodos
    }
    def obtenerNodos(){
        return this.nodos
    }
    def obtenerNombresNodos(){
        return this.nodos.getProperties()
    }
    Nodo obtenerNodo(String nombreNodo){
        return this.nodos[nombreNodo]
    }
    def busquedaProfunidad(String nombreNodoOrigen){
        Nodo n
        for( nn in obtenerNombresNodos()){
            n = this.obtenerNodo(nn)
            n.color = Utils.BLANCO()
            n.pi = null
        }
        this.tiempo = 0
        Nodo nodoOrigen = this.obtenerNodo(nombreNodoOrigen)
        this.bp_visita(nodoOrigen)
    }
    def bp_visita(Nodo u){
        u.color = Utils.GRIS()
        this.tiempo += 1
        u.d = this.tiempo
        for(nv in u.obtenerVecinos()){
            Nodo v =  this.obtenerNodo(nv)
            if(v.color == Utils.BLANCO()){
                v.pi = u.nombre
                this.bp_visita(v)
            }
        }
        u.color = Utils.NEGRO()
        this.tiempo += 1
        u.f = this.tiempo
    }
    def busquedaAmplitud(String nombreNodoOrigen){
        for(nn in this.obtenerNombresNodos()){
            if(nn != nombreNodoOrigen){
                Nodo n = this.obtenerNodo(nn)
                n.color = Utils.BLANCO()
                n.d = Utils.INFINITO()
                n.pi = null
            }
        }
        s = this.obtenerNodo(nombreNodoOrigen)
        s.color = Utils.GRIS()
        s.d = 0
        s.pi = Utils.SIN_ANTECESOR()
        def q = [] as Queue
        q.add(s)
        while(q.size()>0){
            z = q.poll()
            for(nv in z.obtenerVecinos()){
                v = this.obtenerNodo(nv)
                if(v.color == Utils.BLANCO()){
                    v.color = Utils.GRIS()
                    v.d = z.d + 1
                    v.pi = z.nombre
                    Q.add(v)
                }
            }
            z.color = Utils.NEGRO()
        }
        
    }
    def imprimirNodos(){
        for(n in this.nodos.getProperties){
            println n
        }
    }
    def ruta(String nombreNodoDestino, def lista){
        if(lista == null){
            lista = [] as Queue
        }
        lista.add(nombreNodoDestino)
        nd = this.obtenerNodo(nombreNodoDestino)
        if(nd.pi != null || nd.pi != ""){
            return this.ruta(nd.pi,lista)
        }
        return lista
    }
}


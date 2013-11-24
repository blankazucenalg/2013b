package pepo.escom.analisis
import pepo.escom.analisis.*
import grails.converters.JSON    
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
        return this.nodos.keySet() as String[]
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
            if(!nn.equals(nombreNodoOrigen)){
                Nodo n = this.obtenerNodo(nn)
                n.color = Utils.BLANCO()
                n.d = Utils.INFINITO()
                n.pi = null
            }
        }
        def s = this.obtenerNodo(nombreNodoOrigen)
        s.color = Utils.GRIS()
        s.d = 0
        s.pi = Utils.SIN_ANTECESOR()
        def q = [] as Queue
        q.offer(s)
        while(q.size()>0){
            def z = q.poll()
            for(nv in z.obtenerVecinos()){
                def v = this.obtenerNodo(nv)
                if(v.color == Utils.BLANCO()){
                    v.color = Utils.GRIS()
                    v.d = z.d + 1
                    v.pi = z.nombre
                    q.offer(v)
                }
                //println("Q --> " + q)
            }
            z.color = Utils.NEGRO()
        }
        
    }
    def imprimirNodos(){
        for(n in (this.nodos.keySet() as String [])){
            if(this.nodos[n]?.color == Utils.NEGRO())
                println this.nodos[n]
        }
    }
    def ruta(String nombreNodoDestino, def lista){
        if(lista == null){
            lista = [] as Queue
        }
        lista.add(nombreNodoDestino)
        def nd = this.obtenerNodo(nombreNodoDestino)
        //println(nd.getProperties())
        if(nd?.pi){
            return this.ruta(nd.pi,lista)
        }
        return lista
    }
    def busquedaDijkstra(String inicial,String destino){
        def q = [] as Queue
        def alt;
        def dist = [:]
        def visited = [:]
        def previous = [:]
        for(nodo in this.obtenerNombresNodos()){
            dist[nodo] = Utils.INFINITO_DIJKSTRA()
            visited[nodo] = false
            previous[nodo] = null;
        }
        Nodo nodoInicial = this.nodos[inicial];        
        q.offer(nodoInicial);
        while(q.size()>0){
            Nodo u = q.poll()            
            if(u.nombre == destino){
                break;
            }
            visited[u.nombre] = true;
            for(vecino in u.obtenerVecinos()){
                def nodoVecino = this.nodos[vecino]
                alt = dist[u.nombre] + 1;
                if(alt < dist[vecino] && !visited[vecino] ){
                    dist[vecino] = alt;
                    previous[vecino] = u.nombre;
                    q.offer(nodoVecino)
                }
            }
        }        
        def listaFinal = []
        def u = destino
        while(previous[u]){            
            listaFinal.add(u)
            u = previous[u]
        }
        listaFinal.add(inicial)
        println(listaFinal)
        return listaFinal;
    }
    def AStarSearch(String inicio, String fin){
        def closedSet = [] as Queue
        def openSet = [] as Queue
        def came_from = [:]
        def g_score = [:]
        def f_score = [:]
        g_score[inicio]=0
        f_score[inicio]=g_score[inicio]+this.heuristic(0);
        //Se añade el nodo inicial
        Nodo nodoInicial = this.nodos[inicio];
        openSet.offer(nodoInicial)
        int contador=0;
        while(openSet.size()>0){
            Nodo current = openSet.poll()
            if(current.nombre==fin){
                break;                
            }
            closedSet.offer(current)
            for(vecino in current.obtenerVecinos()){
                Nodo vecinoNodo =  this.nodos[vecino];
                contador++;
                def tentative_g_score = g_score[current.nombre] + 1;
                def tentative_f_score = tentative_g_score + heuristic(contador)
                if(closedSet.contains(vecinoNodo) && tentative_f_score >= f_score[vecinoNodo.nombre]){
                    continue;
                }
                if(!openSet.contains(vecinoNodo)||tentative_f_score < f_score[vecinoNodo.nombre] ){
                    came_from[vecinoNodo.nombre] = current.nombre
                    g_score[vecinoNodo.nombre] = tentative_g_score
                    f_score[vecinoNodo.nombre] = tentative_f_score
                    if(!openSet.contains(vecinoNodo)){
                        openSet.offer(vecinoNodo)
                    }
                    
                }
            }
        }
        String listaFinal = ""
        def u = fin
        while(came_from[u]){            
            listaFinal+=u+","
            u = came_from[u]
        }
        listaFinal+=inicio;
        println(listaFinal)
        return listaFinal.split(",");
    }
    def heuristic(Integer visitados){
        Integer valor = (Integer)((this.nodos.size()*2500 - visitados)/(this.nodos.size()*2500))
        //print(visitados+":"+valor+",");
        return valor;
    }
}


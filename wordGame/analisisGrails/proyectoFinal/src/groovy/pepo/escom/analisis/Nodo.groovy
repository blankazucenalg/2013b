package pepo.escom.analisis
import pepo.escom.analisis.*
/**
 *
 * @author alberto
 */


class Nodo {
    String nombre
    Integer d
    Integer color
    String pi
    def vecinos
    Integer f
    Nodo(String identificador){
        this.nombre = identificador
        this.d = Utils.INFINITO()
        this.color = Utils.SIN_COLOR()
        this.pi = ""
        this.vecinos = [:]
        this.f = Utils.INFINITO()
    }
    def agregarVecino(String nombreVecino, int costoVecino){
        this.vecinos[nombreVecino] = costoVecino
    }
    def obtenerVecinos(){
        return this.vecinos.keySet() as String []
    }
    def obtenerCostoVecinos(){
        return this.vecinos.getProperties()
    }
    String obtenerColor(){
        if(this.color == Utils.BLANCO())
        return "BLANCO"
        else if(this.color == Utils.GRIS())
        return "GRIS"
        else if(this.color == Utils.NEGRO())
        return "NEGRO"
        else
        return "SIN COLOR"
        
    }
    String toString(){
        return this.nombre + " COLOR : "+this.obtenerColor() + " Costo: "+this.d+" Antecesor : "+this.pi
    }
}


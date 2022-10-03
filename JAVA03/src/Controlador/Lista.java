package Controlador;

/**
 *
 * 
 * @param <K>
 */
public class Lista<K> {
    
    //Lista de nodos que contendran la informacion de las cuentas. Es una clase anonima para poder hacer listas de nodos de la clase que sean
    
    private static Nodo inicio;
    private Nodo actual;
    
    public Lista(){
        //Cuando se crea la lista, esta vacia y el inicio es nulo
        inicio = null;
    }
    
    
    //Para insertar nuevos nodos, introduzco en nodo como parametro
    public void insertar(K nodo){
        Nodo n = new Nodo(nodo);
        
        //Si la lista esta vacia, pongo este nodo como inicio
        if(inicio == null){
            setInicio(n);
            
        }
        else{
            //Si no, cojo el primer nodo e itero hasta que llego al que tiene nulo como el siguiente
            Nodo aux = getInicio();
            
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            
            //Le asigno como siguiente el nodo que introduzco y al introducido le asigno que el anterior sea el anteriormente ultimo nodo
            n.setAnterior(aux);
            aux.setSiguiente(n);
        }
    }

    /**
     * @return the inicio
     */
    public static Nodo getInicio() {
        return inicio;
    }

    /**
     * @param aInicio the inicio to set
     */
    public static void setInicio(Nodo aInicio) {
        inicio = aInicio;
    }

    /**
     * @return the actual
     */
    public Nodo getActual() {
        return actual;
    }

    /**
     * @param aActual the actual to set
     */
    public void setActual(Nodo aActual) {
        actual = aActual;
    }
    
}

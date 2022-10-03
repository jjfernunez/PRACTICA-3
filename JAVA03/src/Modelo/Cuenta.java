

package Modelo;

import java.util.*;

public class Cuenta {
    
   
    private int numero;
    private GregorianCalendar fecha; 
    private float saldo;
    private String propietario;

    private static int numeroEstatico = 0;
    
    
    //Cuenta tiene 3 constructores
    
    //El primer constructor pone todo por defecto
    public Cuenta()
    {
     this.numero = 0;
     this.saldo = 0;
     this.propietario = "";
     this.fecha = new GregorianCalendar();
    }
    
    //EL segundo pone solo la fecha por defecto
    
     public Cuenta(float saldo, String propietario){
        
        this();
        this.numero = numeroEstatico;
        numeroEstatico++;
        this.saldo = saldo;
        this.propietario = propietario;
        
    }
     
     //El ultimo todos los datos son introducidos por el ususario
    public Cuenta(float saldo, String propietario, int dia, int mes, int anio){
        
        this(saldo, propietario);
        setFecha(anio,mes,dia);
        setNumero(numeroEstatico);
       
       
        
        
    }
   
 
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the fecha
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * @param anio
     * @param fecha the fecha to set
     */
    public void setFecha(int anio, int mes, int dia) {
        this.fecha = new GregorianCalendar(anio, mes, dia);
    }

    /**
     * @return the saldo
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the propietario
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

  
    /**
     *
     * @return 
     */
    @Override
    public String toString(){
        String imprime = ("Numero de cuenta: "+ this.getNumero());
        imprime += ("\nPropietario: "+ this.getPropietario());
        imprime +=("\nSaldo: "+ this.getSaldo());
        imprime +=("\nFecha de creacion: "+ this.getFecha().get(Calendar.DATE)+"/"+this.getFecha().get(Calendar.MONTH)+"/"+this.getFecha().get(Calendar.YEAR));

        return imprime;
    }
    
   
    
  
    
}

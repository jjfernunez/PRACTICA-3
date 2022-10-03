package Vista;

import Vista.*;
import Modelo.*;
import Controlador.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public class JAVA03_UI extends javax.swing.JFrame {

    public static boolean creandoCuenta;
    
    public JAVA03_UI() {
        
        
         //Creo una nueva lista
         Lista <Cuenta> lista = new <Cuenta> Lista();
        
         //Inserto algunos datos en la lista
        lista.insertar(new Cuenta(1800, "Juan Jose", 21, 9, 2022));
        lista.insertar(new Cuenta(29000, "Jose Manuel", 12, 3, 2020));
        lista.insertar(new Cuenta(15000, "Alberto", 4, 7, 2021));
        lista.insertar(new Cuenta(3000, "Paula", 15, 2, 2021));
        lista.insertar(new Cuenta(45678, "Alvaro", 12, 3, 2020));
        
        
        //Pongo el apuntador actual en el inicio
        lista.setActual(lista.getInicio());
        
        
        //Instancio ese apuntador para poner los datos en los campos de texto
       Cuenta cuentaActual = (Cuenta) lista.getActual().getDato();
       
        
        //Se inician los componentes de la interfaz
        initComponents();
        
        //Escribo los datos en los campos de texto
        numeroField.setText(""+cuentaActual.getNumero());
        propietarioField.setText(""+cuentaActual.getPropietario());
        diaField.setText(""+cuentaActual.getFecha().get(Calendar.DATE));
        mesField.setText(""+cuentaActual.getFecha().get(Calendar.MONTH));
        yearField.setText(""+cuentaActual.getFecha().get(Calendar.YEAR));        
        saldoField.setText(""+cuentaActual.getSaldo());
        
        
        
        //Boton Anterior o Aceptar
        botonAnterior.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                
                //Instancio una cuenta que me de los datos que se va a ir mostrando
                Cuenta cuentaAc = (Cuenta) lista.getActual().getAnterior().getDato();
                
                //Si no estas creando una cuenta nueva
                if(creandoCuenta == false){
                    //Y el anterior no es nulo
                    if(lista.getActual().getAnterior() != null){

                            //Pongo el apuntador actual en el nodo anterior
                           lista.setActual(lista.getActual().getAnterior());
                            //Escribo los datos en el campo de texto
                           numeroField.setText(""+cuentaAc.getNumero());
                           propietarioField.setText(""+cuentaAc.getPropietario());
                           diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                           mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                           yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 
                           saldoField.setText(""+cuentaAc.getSaldo());
                           
                           //Pongo el boton siguiente en activo en caso de que estuviera apagado porque el siguiente era nulo
                           botonSiguiente.setEnabled(true);
                           
                           //Si el anterior es nulo, deshabilito el boton
                           if(lista.getActual().getAnterior() == null){
                                botonAnterior.setEnabled(false);

                           }
                    }
                }
                else{
                    
                      //Si estoy creando cuenta, este boton actua como un cancelar, por lo que devuelve al estado original todos los campos y botones sin modificarlos datos
                        propietarioField.setEditable(false);
                        saldoField.setEditable(false);
                        
                        numeroField.setBackground(null);
                        diaField.setBackground(null);
                        
                        
                        botonSiguiente.setText("Siguiente");
                        botonAnterior.setText("Anterior");
                        
                        botonCrearCuenta.setVisible(true);
                        
                        
                        numeroField.setText(""+cuentaAc.getNumero());
                        propietarioField.setText(""+cuentaAc.getPropietario());
                        diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                        mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                        yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 


                        saldoField.setText(""+cuentaAc.getSaldo());
                        creandoCuenta = false;
                    
                }

            }
        });
          
        
        //El boton siguiente actua parecido al anterior, excepto que aceptar no solo devuelve los campos al estado mostrar, sino que guarda los datos introducidos en una nueva cuenta
        botonSiguiente.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                Cuenta cuentaAc;
                if(creandoCuenta == false){
                    if(lista.getActual().getSiguiente() != null){
                        //Cuando el boton es siguiente, actua igual que anterior pero al reves
                    cuentaAc = (Cuenta) lista.getActual().getSiguiente().getDato();


                     

                        lista.setActual(lista.getActual().getSiguiente());

                        numeroField.setText(""+cuentaAc.getNumero());
                        propietarioField.setText(""+cuentaAc.getPropietario());
                        diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                         mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                         yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 
                        saldoField.setText(""+cuentaAc.getSaldo());
                         botonAnterior.setEnabled(true);
                        if(lista.getActual().getSiguiente() == null){
                            botonSiguiente.setEnabled(false);
                    
                        }
                       
                     }
                }
                else{
                    //Cuando el boton es aceptar
                     cuentaAc = (Cuenta) lista.getActual().getDato();
                        GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
                        
                        //Recojo los datos introducidos en distintas variables
                        String propietario = propietarioField.getText();
                        float saldo = Float.parseFloat(saldoField.getText());
                        String dia = diaField.getText();
                        String mes = mesField.getText();
                        String year = yearField.getText();
                        
                        
                        //Si no ha puesto ninguna fecha, utilizo el constructor que la pone por defecto para insertar la nueva cuenta
                        if((dia.equalsIgnoreCase("dd") || dia.equalsIgnoreCase("") || mes.equalsIgnoreCase("mm") || mes.equalsIgnoreCase("") || year.equalsIgnoreCase("yyyy") || year.equalsIgnoreCase(""))){
                        
                            
                            
                            lista.insertar(new Cuenta(saldo, propietario));  
                        }
                        else{
                         
                            //Si ha introducido fecha, utilizo el contructor necesario
                            int dial = Integer.parseInt(dia);
                            int mesl = Integer.parseInt(mes);
                            int yearl = Integer.parseInt(year);
                            lista.insertar(new Cuenta(saldo, propietario, dial , mesl, yearl));  

                        }
                        
                        
                        //Con la cuenta introducida, devuelvo al estado de vista original
                        propietarioField.setEditable(false);
                        saldoField.setEditable(false);
                        
                        numeroField.setBackground(null);
                        diaField.setBackground(null);
                        
                        
                        botonSiguiente.setText("Siguiente");
                        botonAnterior.setText("Anterior");
                        
                        botonCrearCuenta.setVisible(true);
                        
                        
                        numeroField.setText(""+cuentaAc.getNumero());
                        propietarioField.setText(""+cuentaAc.getPropietario());
                        diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                        mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                        yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 

                        saldoField.setText(""+cuentaAc.getSaldo());
                        creandoCuenta = false;
                }
            }
        });
        
        //El boton crearCuenta pone creandocuenta en verdadero, lo que cambia el funcionamiento de botonSiguiente y botonAnterior
        botonCrearCuenta.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
               
                //Cambio los campos de texto que sean rellenables a campos vacios y me aseguro qde que los botones estan activados
               botonAnterior.setEnabled(true);
               botonSiguiente.setEnabled(true);
                numeroField.setText("Autonumerico");
                numeroField.setBackground(Color.gray);
               propietarioField.setText("");
              diaField.setText("dd");
              mesField.setText("mm");
              yearField.setText("yyyy");
               saldoField.setText("");
                
               //Permito que los campos sean editables
               propietarioField.setEditable(true);
               saldoField.setEditable(true);
              
              
               //Quito el boton de crear cuenta
                botonCrearCuenta.setVisible(false);
              
                //Cambio los nombres de los botones
                creandoCuenta = true;
                botonAnterior.setText("Cancelar");
                botonSiguiente.setText("Aceptar");

            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        numeroField = new javax.swing.JTextField();
        propietarioField = new javax.swing.JTextField();
        diaField = new javax.swing.JTextField();
        saldoField = new javax.swing.JTextField();
        yearField = new javax.swing.JTextField();
        mesField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonCrearCuenta = new javax.swing.JButton();
        botonSiguiente = new javax.swing.JButton();
        botonAnterior = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Numero de cuenta:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Propietario:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Fecha de creación: ");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Saldo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(30, 30, 30))
        );

        numeroField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroFieldActionPerformed(evt);
            }
        });

        saldoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("/");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel7.setText("/");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saldoField)
                    .addComponent(propietarioField)
                    .addComponent(numeroField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(diaField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesField, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(propietarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diaField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel5.setText("PRÁCTICA 3");

        botonCrearCuenta.setText("Crear cuenta");
        botonCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearCuentaActionPerformed(evt);
            }
        });

        botonSiguiente.setText("Siguiente");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        botonAnterior.setText("Anterior");
        botonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(117, 117, 117))))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(botonCrearCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCrearCuentaActionPerformed

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonSiguienteActionPerformed

    private void numeroFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroFieldActionPerformed

    private void saldoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saldoFieldActionPerformed

    private void botonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnteriorActionPerformed
        /*Cuenta cuentaAc = (Cuenta) lista.getActual().getAnterior().getDato();
                
                if(creandoCuenta == false){
                    if(lista.getActual().getAnterior() != null){

                           lista.setActual(lista.getActual().getAnterior());

                           numeroField.setText(""+cuentaAc.getNumero());
                           propietarioField.setText(""+cuentaAc.getPropietario());
                           diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                           mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                           yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 
                           saldoField.setText(""+cuentaAc.getSaldo());
                           botonSiguiente.setEnabled(true);
                           if(lista.getActual().getAnterior() == null){
                                botonAnterior.setEnabled(false);

                           }
                    }
                }
                else{
                    
                      
                        propietarioField.setEditable(false);
                        saldoField.setEditable(false);
                        
                        numeroField.setBackground(null);
                        diaField.setBackground(null);
                        
                        
                        botonSiguiente.setText("Siguiente");
                        botonAnterior.setText("Anterior");
                        
                        botonCrearCuenta.setVisible(true);
                        
                        
                        numeroField.setText(""+cuentaAc.getNumero());
                        propietarioField.setText(""+cuentaAc.getPropietario());
                        diaField.setText(""+cuentaAc.getFecha().get(Calendar.DATE));
                        mesField.setText(""+cuentaAc.getFecha().get(Calendar.MONTH));
                        yearField.setText(""+cuentaAc.getFecha().get(Calendar.YEAR)); 


                        saldoField.setText(""+cuentaAc.getSaldo());
                        creandoCuenta = false;
                    
                }
        */
    }//GEN-LAST:event_botonAnteriorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JAVA03_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAVA03_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAVA03_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAVA03_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JAVA03_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnterior;
    private javax.swing.JButton botonCrearCuenta;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JTextField diaField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField mesField;
    private javax.swing.JTextField numeroField;
    private javax.swing.JTextField propietarioField;
    private javax.swing.JTextField saldoField;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables
}

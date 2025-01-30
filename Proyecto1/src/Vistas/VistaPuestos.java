
package Vistas;

import Controladores.ControladorPuestosLaboratorio;
import Modelo.Laboratorio;
import Modelo.Puesto;
import Modelo.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class VistaPuestos extends javax.swing.JFrame implements ActionListener{
    
    Laboratorio laboratorio;
    Usuario usuario;
    ControladorPuestosLaboratorio controladorPuestos;
    JButton[][]puestos;

    public VistaPuestos(Laboratorio laboratorio, Usuario usuario) {
        this.laboratorio = laboratorio;
        controladorPuestos = new ControladorPuestosLaboratorio(laboratorio);
        initComponents();
        setLocationRelativeTo(this);
        this.usuario = usuario;
        if(usuario.getRol().equals(Usuario.ADMINLAB)){
            panelAdminLab.setVisible(true);
        }else if(usuario.getRol().equals(Usuario.ESTUDIANTE)){
            panelAdminLab.setVisible(false);
        }
        panelColaPrestamos.setVisible(false);
        if(usuario.getRol().equals(Usuario.ADMINLAB) && !laboratorio.isColaPrestamoVacia()){
            panelColaPrestamos.setVisible(true);
            lblColaPrestamos.setText(laboratorio.getSiguienteEnCola().getId()+"-"+laboratorio.getSiguienteEnCola().getNombre());
        }
        lblMantenimiento.setVisible(false);
        if(laboratorio.isMantenimiento()){
            lblMantenimiento.setVisible(true);
            lblMantenimiento.setText("<html><p>El día de hoy este laboratorio se encuentra en mantenimiento."+"<br>Por lo tanto no se podrán hacer prestamos"+"<br>¡Disculpa las incomodidades!</html></p>");
        }
        crearPuestos(); 
        validarPuestos();
    }

    public void crearPuestos(){
        int cantidadPuestos = controladorPuestos.getCantidadPuestos();
        int filas = cantidadPuestos /4;
        JButton[][]matrizPuestos;
        
        if(cantidadPuestos % 4 != 0){
            matrizPuestos = new JButton[filas+1][];
            
            for(int i = 0; i < filas; i++){
                matrizPuestos[i] = new JButton[4];
            }
            int puestosFaltantes = cantidadPuestos % 4;
            matrizPuestos[matrizPuestos.length-1] = new JButton[puestosFaltantes];
        }else{
            matrizPuestos = new JButton[filas][4];
        }
        this.puestos = matrizPuestos;
        cargarPuestos();
    }
    
    public void cargarPuestos(){
        int ancho = 50;
        int alto = 50;
        int separado = 10;
        int identificador = 1;
        
        for(int i = 0; i < puestos.length; i++ ){
            for(int j = 0; j < puestos[i].length; j++){
                puestos[i][j] = new JButton();
                puestos[i][j].setBounds(j*ancho + separado,
                        i*alto+separado, ancho, alto);
                puestos[i][j].setBackground(Color.WHITE);
                puestos[i][j].addActionListener(this);
                puestos[i][j].setText(""+identificador);
                panelPuestos.add(puestos[i][j]);
                identificador += 1;
            }
        }
        int anchoPanel = (puestos[0].length)*ancho+separado+10;
        int altoPanel = (puestos.length)*ancho+separado+10;
        panelPuestos.setPreferredSize(new Dimension(anchoPanel,altoPanel));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelAdminLab = new javax.swing.JPanel();
        btnGestLab = new javax.swing.JButton();
        panelColaPrestamos = new javax.swing.JPanel();
        lblInfoCola = new javax.swing.JLabel();
        lblColaPrestamos = new javax.swing.JLabel();
        panelPuestos = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblMantenimiento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 204));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        panelAdminLab.setBackground(new java.awt.Color(255, 255, 255));

        btnGestLab.setBackground(new java.awt.Color(255, 255, 102));
        btnGestLab.setText("Gestión Laboraratorio");
        btnGestLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestLabActionPerformed(evt);
            }
        });

        panelColaPrestamos.setBackground(new java.awt.Color(255, 255, 255));

        lblInfoCola.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblInfoCola.setText("Siguiente estudiante: ");

        lblColaPrestamos.setText("jLabel1");

        javax.swing.GroupLayout panelColaPrestamosLayout = new javax.swing.GroupLayout(panelColaPrestamos);
        panelColaPrestamos.setLayout(panelColaPrestamosLayout);
        panelColaPrestamosLayout.setHorizontalGroup(
            panelColaPrestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelColaPrestamosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelColaPrestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblInfoCola, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(lblColaPrestamos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelColaPrestamosLayout.setVerticalGroup(
            panelColaPrestamosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelColaPrestamosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblInfoCola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblColaPrestamos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelAdminLabLayout = new javax.swing.GroupLayout(panelAdminLab);
        panelAdminLab.setLayout(panelAdminLabLayout);
        panelAdminLabLayout.setHorizontalGroup(
            panelAdminLabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGestLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminLabLayout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(panelColaPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelAdminLabLayout.setVerticalGroup(
            panelAdminLabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLabLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnGestLab, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelColaPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPuestos.setBackground(new java.awt.Color(255, 255, 153));

        javax.swing.GroupLayout panelPuestosLayout = new javax.swing.GroupLayout(panelPuestos);
        panelPuestos.setLayout(panelPuestosLayout);
        panelPuestosLayout.setHorizontalGroup(
            panelPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );
        panelPuestosLayout.setVerticalGroup(
            panelPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        btnRegresar.setBackground(new java.awt.Color(255, 255, 153));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 255, 153));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblMantenimiento.setBackground(new java.awt.Color(153, 255, 204));
        lblMantenimiento.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelAdminLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 111, Short.MAX_VALUE)
                                .addComponent(btnRegresar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalir))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(panelPuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(lblMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnRegresar))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAdminLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaLaboratorio vistaLaboratorio = new VistaLaboratorio(usuario);
        vistaLaboratorio.setVisible(true);
        vistaLaboratorio.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGestLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestLabActionPerformed
        VistaGestionLaboratorio vistaGestLab = new VistaGestionLaboratorio(laboratorio, usuario);
        vistaGestLab.setVisible(true);
        vistaGestLab.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnGestLabActionPerformed
    
    public void validarPuestos(){
        for(int i = 0; i < puestos.length; i++){
            for(int j = 0; j < puestos[i].length; j++){
                Puesto puesto = controladorPuestos.getPuesto(i, j);
                if(puesto.getEstado().equals(Puesto.OCUPADO)){
                    puestos[i][j].setBackground(Color.BLUE);
                }else{
                    puestos[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VistaPuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VistaPuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VistaPuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VistaPuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaPuestos().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGestLab;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblColaPrestamos;
    private javax.swing.JLabel lblInfoCola;
    private javax.swing.JLabel lblMantenimiento;
    private javax.swing.JPanel panelAdminLab;
    private javax.swing.JPanel panelColaPrestamos;
    private javax.swing.JPanel panelPuestos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                if (ae.getSource().equals(puestos[i][j])) {
                    Puesto puesto = controladorPuestos.getPuesto(i, j);
                    if (usuario.getRol().equals(Usuario.ADMINLAB)) {
                        VistaPrestamo_Reserva vistaPrestamo_Reserva = new VistaPrestamo_Reserva(usuario, laboratorio, puesto);
                        vistaPrestamo_Reserva.setVisible(true);
                        vistaPrestamo_Reserva.setLocationRelativeTo(this);
                        this.dispose();
                    }else if(usuario.getRol().equals(Usuario.ESTUDIANTE)){
                        VistaReserva vistaReserva = new VistaReserva(usuario, laboratorio, puesto);
                        vistaReserva.setVisible(true);
                        vistaReserva.setLocationRelativeTo(this);
                        this.dispose();
                    } 
                }
            }
        }
    }
}

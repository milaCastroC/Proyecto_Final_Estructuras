
package Vistas;

import Controladores.ControladorPrestamos;
import Excepciones.PrestamoExistenteException;
import Excepciones.PrestamoInexistenteException;
import Excepciones.PuestoNoDisponibleException;
import Excepciones.ReservaProximaException;
import Excepciones.SinPuestoDisponibleException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Estudiante;
import Modelo.Laboratorio;
import Modelo.Puesto;
import Modelo.Usuario;
import javax.swing.JOptionPane;

public class VistaPrestamo extends javax.swing.JFrame {

    Usuario usuario;
    Laboratorio laboratorio;
    Puesto puesto;
    ControladorPrestamos controladorPrestamos;
    
    public VistaPrestamo(Usuario usuario, Laboratorio laboratorio, Puesto puesto) {
        initComponents();
        this.usuario = usuario;
        this.laboratorio = laboratorio;
        this.puesto = puesto;
        controladorPrestamos = new ControladorPrestamos(laboratorio);
        btnFinalizarPrestamo.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPrograma = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAgregarPrestamo = new javax.swing.JButton();
        btnFinalizarPrestamo = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        panelInfo.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setText("Documento:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Programa:");

        btnBuscar.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregarPrestamo.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarPrestamo.setText("Agregar prestamo");
        btnAgregarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPrestamoActionPerformed(evt);
            }
        });

        btnFinalizarPrestamo.setBackground(new java.awt.Color(153, 153, 255));
        btnFinalizarPrestamo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFinalizarPrestamo.setText("FinalizarPrestamo");
        btnFinalizarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPrestamoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(25, 25, 25)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelInfoLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(28, 28, 28)
                                    .addComponent(lblPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnFinalizarPrestamo)
                                    .addComponent(btnAgregarPrestamo))))
                        .addContainerGap(72, Short.MAX_VALUE))))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblPrograma))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarPrestamo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizarPrestamo)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnRegresar.setBackground(new java.awt.Color(255, 255, 153));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 255, 153));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("PRESTAMOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir))
                    .addComponent(panelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnSalir)
                    .addComponent(jLabel12))
                .addGap(2, 2, 2)
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String id = txtId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese el código del estudiante");
        } else {
            try {
                Estudiante estudiante = (Estudiante) controladorPrestamos.buscarEstudiante(id);
                lblNombre.setText(estudiante.getNombre());
                lblPrograma.setText(estudiante.getPrograma().getNombre());
                if (puesto.getEstado().equals(Puesto.OCUPADO) && puesto.getPrestamo() != null && puesto.getPrestamo().isActivo() && puesto.getPrestamo().getEstudiante().getId().equals(id)) {
                    btnFinalizarPrestamo.setVisible(true);
                }
            } catch (UsuarioInexistenteException | UsuarioNoCoincideException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaPrestamo_Reserva vistaPrestamo_Reserva = new VistaPrestamo_Reserva(usuario, laboratorio, puesto);
        vistaPrestamo_Reserva.setVisible(true);
        vistaPrestamo_Reserva.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPrestamoActionPerformed
        String id = txtId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese el código del estudiante");
        } else {
            try {
                Estudiante estudiante = (Estudiante) controladorPrestamos.buscarEstudiante(id);
                if (controladorPrestamos.buscarPuestosDisponibles(laboratorio)) {
                    controladorPrestamos.agregarPrestamo(estudiante, puesto);
                    JOptionPane.showMessageDialog(null, "Prestamo asignado con exito");
                    VistaPuestos vistaPuestos = new VistaPuestos(laboratorio, usuario);
                    vistaPuestos.setVisible(true);
                    vistaPuestos.setLocationRelativeTo(this);
                    this.dispose();
                } else {
                    int opcion = JOptionPane.showConfirmDialog(null, "Todos los puestos de este laboratorio están ocupados ¿Desea entrar en lista de espera?");
                    if (opcion == JOptionPane.YES_OPTION) {
                        controladorPrestamos.agregarPrestamo(estudiante, puesto);
                        JOptionPane.showMessageDialog(null, "Estudiante en lista de espera");
                        VistaPuestos vistaPuestos = new VistaPuestos(laboratorio, usuario);
                        vistaPuestos.setVisible(true);
                        vistaPuestos.setLocationRelativeTo(this);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Prestamo cancelado");
                        VistaPuestos vistaPuestos = new VistaPuestos(laboratorio, usuario);
                        vistaPuestos.setVisible(true);
                        vistaPuestos.setLocationRelativeTo(this);
                        this.dispose();
                    }
                }
            } catch (UsuarioInexistenteException | UsuarioNoCoincideException | PrestamoExistenteException | ReservaProximaException | PuestoNoDisponibleException | SinPuestoDisponibleException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAgregarPrestamoActionPerformed

    private void btnFinalizarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPrestamoActionPerformed
       String id = txtId.getText();
       if(id.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor ingrese el código del estudiante");
        }else{
            try{
                Estudiante estudiante = (Estudiante) controladorPrestamos.buscarEstudiante(id);
                controladorPrestamos.finalizarPrestamo(estudiante, puesto);
                JOptionPane.showMessageDialog(null,"Prestamo finalizado con exito"); 
                VistaPuestos vistaPuestos = new VistaPuestos(laboratorio, usuario);
                vistaPuestos.setVisible(true);
                vistaPuestos.setLocationRelativeTo(this);
                this.dispose();
            } catch (PrestamoInexistenteException |  UsuarioInexistenteException | UsuarioNoCoincideException  ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnFinalizarPrestamoActionPerformed

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
//            java.util.logging.Logger.getLogger(VistaPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VistaPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VistaPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VistaPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaPrestamo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPrestamo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFinalizarPrestamo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrograma;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}

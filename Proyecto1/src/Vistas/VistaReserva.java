package Vistas;

import Controladores.ControladorReserva;
import Excepciones.FechaReservaInvalidaException;
import Excepciones.FueraDeJornadaException;
import Excepciones.LaboratorioEnMantenimientoException;
import Excepciones.ReservaExistenteException;
import Excepciones.ReservaInexistenteException;
import Excepciones.TiempoReservaInvalidoException;
import Excepciones.UsuarioInexistenteException;
import Excepciones.UsuarioNoCoincideException;
import Modelo.Estudiante;
import Modelo.Laboratorio;
import Modelo.Programacion;
import Modelo.Puesto;
import Modelo.Reserva;
import Modelo.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.swing.JOptionPane;

public class VistaReserva extends javax.swing.JFrame {

    Usuario usuario;
    Laboratorio laboratorio;
    ControladorReserva controladorReserva;

    public VistaReserva(Usuario usuario, Laboratorio laboratorio, Puesto puesto) {
        initComponents();
        setLocationRelativeTo(this);
        this.usuario = usuario;
        this.laboratorio = laboratorio;
        controladorReserva = new ControladorReserva(puesto);
        lblReservas.setText("");
        if (usuario.getRol().equals(Usuario.ADMINLAB)) {
            panelInfo.setVisible(true);
            lblNombre.setText("");
            lblPrograma.setText("");
            btnCancelarReserva.setVisible(false);
        } else if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            panelInfo.setVisible(false);
            Estudiante estudiante = (Estudiante) usuario;
            if(estudiante.isTieneReserva().equals(Estudiante.RESERVA_ACTIVA)){
                lblReservas.setText("Tiene reserva");
                btnCancelarReserva.setVisible(true);
            }else{
                lblReservas.setText("No tiene reserva");
                btnCancelarReserva.setVisible(false);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPrograma = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblReservas = new javax.swing.JLabel();
        btnCancelarReserva = new javax.swing.JButton();
        txtHoraInicio = new javax.swing.JFormattedTextField();
        txtHoraFin = new javax.swing.JFormattedTextField();
        btnReservar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        btnSalir.setBackground(new java.awt.Color(255, 255, 153));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(255, 255, 153));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));

        jLabel7.setText("Día: ");

        jLabel8.setText("Hora inicio:");

        jLabel9.setText("Hora fin:");

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
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(lblPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));

        jLabel4.setText("Reservas activas:");

        lblReservas.setText("jLabel5");

        btnCancelarReserva.setBackground(new java.awt.Color(153, 153, 255));
        btnCancelarReserva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarReserva.setText("Cancelar Reserva");
        btnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarReserva))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelarReserva)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        try {
            txtHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtHoraFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calendario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtHoraFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnReservar.setBackground(new java.awt.Color(255, 255, 153));
        btnReservar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addGap(24, 24, 24)
                        .addComponent(btnSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(btnReservar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnRegresar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReservar)
                .addContainerGap(24, Short.MAX_VALUE))
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
                Estudiante estudiante = (Estudiante) controladorReserva.buscarEstudiante(id);
                lblNombre.setText(estudiante.getNombre());
                lblPrograma.setText(estudiante.getPrograma().getNombre());
                if(estudiante.isTieneReserva().equals(Estudiante.RESERVA_ACTIVA)){
                    lblReservas.setVisible(true);
                    lblReservas.setText("Tiene reserva");
                    btnCancelarReserva.setVisible(true);
                }else{
                    lblReservas.setText("No tiene reserva");
                    btnCancelarReserva.setVisible(false);
                }
            } catch (UsuarioInexistenteException | UsuarioNoCoincideException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        if (usuario.getRol().equals(Usuario.ADMINLAB)) {
            VistaPuestos vistaAdminLab = new VistaPuestos(laboratorio, usuario);
            vistaAdminLab.setVisible(true);
            vistaAdminLab.setLocationRelativeTo(this);
            this.dispose();
        } else if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            VistaPuestos vistaPuestos = new VistaPuestos(laboratorio, usuario);
            vistaPuestos.setVisible(true);
            vistaPuestos.setLocationRelativeTo(this);
            this.dispose();
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        if (validarCamposTexto()) {
            if (usuario.getRol().equals(Usuario.ADMINLAB)) {
                String id = txtId.getText();
                try {
                    Estudiante estudiante = (Estudiante) controladorReserva.buscarEstudiante(id);
                    LocalDate dia = convertirDateALocalDate(calendario.getDate());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaInicio = LocalTime.parse(txtHoraInicio.getText(), formatter);
                    LocalTime horaFin = LocalTime.parse(txtHoraFin.getText(), formatter);
                    Programacion programacion = new Programacion(dia, horaInicio, horaFin);
                    Reserva reserva = new Reserva(estudiante, programacion);
                    controladorReserva.agendarReserva(reserva, laboratorio);
                    JOptionPane.showMessageDialog(null, "Reserva éxitosa");
                    VistaPuestos vistaAdminLab = new VistaPuestos(laboratorio, usuario);
                    vistaAdminLab.setVisible(true);
                    vistaAdminLab.setLocationRelativeTo(this);
                    this.dispose();
                    
                } catch (UsuarioInexistenteException | UsuarioNoCoincideException | ReservaExistenteException | TiempoReservaInvalidoException | FechaReservaInvalidaException | FueraDeJornadaException | LaboratorioEnMantenimientoException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Hora inválida");
                }
            } else if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
                try {
                    LocalDate dia = convertirDateALocalDate(calendario.getDate());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaInicio = LocalTime.parse(txtHoraInicio.getText(), formatter);
                    LocalTime horaFin = LocalTime.parse(txtHoraFin.getText(), formatter);
                    Programacion programacion = new Programacion(dia, horaInicio, horaFin);
                    Reserva reserva = new Reserva((Estudiante) usuario, programacion);
                    controladorReserva.agendarReserva(reserva, laboratorio);
                    JOptionPane.showMessageDialog(null, "Reserva éxitosa");
                    VistaPuestos vistaAdminLab = new VistaPuestos(laboratorio, usuario);
                    vistaAdminLab.setVisible(true);
                    vistaAdminLab.setLocationRelativeTo(this);
                    this.dispose();
                } catch (ReservaExistenteException | TiempoReservaInvalidoException | FechaReservaInvalidaException | FueraDeJornadaException | LaboratorioEnMantenimientoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Hora inválida");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
        }

    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarReservaActionPerformed
        if (usuario.getRol().equals(Usuario.ADMINLAB)) {
            String id = txtId.getText();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el código del estudiante");
            } else {
                try {
                    Estudiante est = (Estudiante) controladorReserva.buscarEstudiante(id);
                    controladorReserva.cancelarReserva(est);
                    JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito");
                    VistaPuestos vistaAdminLab = new VistaPuestos(laboratorio, usuario);
                    vistaAdminLab.setVisible(true);
                    vistaAdminLab.setLocationRelativeTo(this);
                } catch (ReservaInexistenteException | UsuarioInexistenteException | UsuarioNoCoincideException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            try {
                controladorReserva.cancelarReserva((Estudiante) usuario);
                JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito");
                VistaPuestos vistaAdminLab = new VistaPuestos(laboratorio, usuario);
                vistaAdminLab.setVisible(true);
                vistaAdminLab.setLocationRelativeTo(this);
            } catch (ReservaInexistenteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnCancelarReservaActionPerformed

    public boolean validarCamposTexto() {
        Date dia = calendario.getDate();

        if (usuario.getRol().equals(Usuario.ADMINLAB)) {
            String id = txtId.getText();
            if (id.isEmpty() || dia == null) {
                return false;
            }
        } else if (usuario.getRol().equals(Usuario.ESTUDIANTE)) {
            if (dia == null) {
                return false;
            }
        }
        return true;
    }

    /*date.toInstant() convierte el date en instant para representar el punto en el tiempo
    atZone(ZoneId.systemDefault()) transforma el instant a la zona horaria del sistema
    toLocalDate()convierte el tiempo de la zona horaria a localDate*/
    public static LocalDate convertirDateALocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
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
//            java.util.logging.Logger.getLogger(VistaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VistaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VistaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VistaReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaReserva().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarReserva;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrograma;
    private javax.swing.JLabel lblReservas;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JFormattedTextField txtHoraFin;
    private javax.swing.JFormattedTextField txtHoraInicio;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}

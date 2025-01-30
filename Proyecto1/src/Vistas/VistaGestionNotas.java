
package Vistas;

import Controladores.ControladorGestionNotas;
import Excepciones.CantidadNotasInvalidaException;
import Excepciones.NotaExistenteException;
import Excepciones.NotaInvalidaException;
import Excepciones.PorcentajeInvalidoException;
import Modelo.Curso;
import Modelo.DetalleNota;
import Modelo.Docente;
import Modelo.Estudiante;
import Modelo.Nota;
import Util.IList;
import Util.Listas;
import excepciones.NotaNoEncontradaException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaGestionNotas extends javax.swing.JFrame {
    
    private Docente docente;
    private ControladorGestionNotas controladorGestionNotas;

    public VistaGestionNotas(Docente docente) {
        initComponents();
        this.docente = docente;
        this.controladorGestionNotas = new ControladorGestionNotas(docente);
        llenarCbCursos();
        if(cbCursos.getSelectedIndex() != -1){
            llenarTablaCurso((Curso)cbCursos.getSelectedItem());
        } else {
            llenarTablaCurso(null);
        }
    }
    
    public VistaGestionNotas(Docente docente, Curso curso){
        initComponents();
        this.docente = docente;
        this.controladorGestionNotas = new ControladorGestionNotas(docente);
        if(curso.getEstado().equals(Curso.FINALIZADO)){
            llenarTablaCurso(curso);
            jPanel3.setVisible(false);
            tablaNotasCurso.setVisible(false);
            cbCursos.setVisible(false);
            lblCurso.setVisible(false);
            lblDescripcion.setVisible(false);
            txtDescripcion.setVisible(false);
            btnAgregar.setVisible(false);
            btnGuardarCambiosNotas.setVisible(false);
            btnEliminar.setVisible(false);
            btnGuardarNotas.setVisible(false);
        }
    }
    
    public void llenarTablaCurso(Curso curso){
        DefaultTableModel model;
        if(curso != null){
            String[]columnas = generarItemsTabla(curso);
            model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 0 && column != 1 && column != columnas.length - 1;
                }
                @Override
                public Class getColumnClass(int columnIndex) {
                    if (columnIndex > 1) {
                        return Double.class;
                    }
                    return Object.class;
                }
            };
            model.setColumnIdentifiers(columnas);
            IList<Nota> notas = controladorGestionNotas.getListaNotas(curso);
            IList<Estudiante> estudiantes = controladorGestionNotas.getEstudiantesMatriculados(curso);
            for (int i = 0; i < estudiantes.size(); i++) {
                Object[] fila = new Object[columnas.length];
                fila[0] = estudiantes.get(i).getId();
                fila[1] = estudiantes.get(i).getNombre();
                for (int j = 0; j < notas.size(); j++) {
                    fila[2 + j] = controladorGestionNotas.obtenerNota(estudiantes.get(i), notas.get(j)).getValor();
                }
                fila[fila.length - 1] = controladorGestionNotas.obtnerNotaFinal(estudiantes.get(i), curso);
                model.addRow(fila);
            }
            
        } else {
            model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{
                "No se ha seleccionado ningún curso"
            });
        }
        tablaNotas.setModel(model);
    }
    
    public void llenarTablaNotasCurso(Curso curso){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
                public boolean isCellEditable(int row, int column) {
                    return column != 0;
                }
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Integer.class;
                }
                return Object.class;
            }
        };
        model.setColumnIdentifiers(new Object[]{
            "ID",
            "Descripcion",
            "Porcentaje (%)"
        });
        IList<Nota> listaNotas = controladorGestionNotas.getListaNotas((Curso)cbCursos.getSelectedItem());
        for (int i = 0; i < listaNotas.size(); i++) {
            model.addRow(new Object[]{
                i + 1,
                listaNotas.get(i).getDescripcion(),
                listaNotas.get(i).getPorcentaje()
            });
        }
        tablaNotasCurso.setModel(model);
    }
    
    public void llenarCbCursos(){
        IList<Curso>cursos = controladorGestionNotas.getListaCursosActivos();
        for (int i = 0; i < cursos.size(); i++) {
            cbCursos.addItem(cursos.get(i));
        }
    }
    
    public String[]generarItemsTabla(Curso curso){
        String[] columnas = new String[controladorGestionNotas.getCantNotas(curso)+3];
        IList<Nota>notas = controladorGestionNotas.getListaNotas(curso);
        
        columnas[0] = "ID";
        columnas[1] = "Nombre";
        
        for(int i = 0; i < notas.size(); i++){
            columnas[i+2]= notas.get(i).toString();     
        }
        columnas[2+notas.size()] = "Nota final";
        return columnas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblCurso = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaNotasCurso = new javax.swing.JTable();
        btnGuardarCambiosNotas = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotas = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardarNotas = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        lblCurso.setText("Curso: ");

        cbCursos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursosItemStateChanged(evt);
            }
        });
        cbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursosActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion Notas"));

        lblDescripcion.setText("Descripción:");

        btnAgregar.setBackground(new java.awt.Color(153, 153, 255));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setText("Agregar Nota");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tablaNotasCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaNotasCurso);

        btnGuardarCambiosNotas.setBackground(new java.awt.Color(153, 153, 255));
        btnGuardarCambiosNotas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarCambiosNotas.setText("Guardar Cambios");
        btnGuardarCambiosNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosNotasActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDescripcion)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcion)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(125, 125, 125))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnGuardarCambiosNotas)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCambiosNotas)
                    .addComponent(btnEliminar)))
        );

        tablaNotas.setBackground(new java.awt.Color(255, 255, 153));
        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaNotas);

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

        btnGuardarNotas.setBackground(new java.awt.Color(255, 255, 102));
        btnGuardarNotas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarNotas.setText("Guardar Cambios");
        btnGuardarNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarNotasActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("GESTIÓN DE NOTAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(396, 396, 396)
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardarNotas)
                .addGap(347, 347, 347))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegresar)
                        .addComponent(btnSalir))
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCurso)
                            .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarNotas))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbCursosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursosItemStateChanged
        if (cbCursos.getSelectedIndex() != -1) {
            llenarTablaCurso((Curso) cbCursos.getSelectedItem());
            llenarTablaNotasCurso((Curso) cbCursos.getSelectedItem());
        }
    }//GEN-LAST:event_cbCursosItemStateChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String descripcion = txtDescripcion.getText();
        if(descripcion.isEmpty() && cbCursos.getSelectedIndex() < 0){
            if(cbCursos.getSelectedIndex() < 0){
                JOptionPane.showMessageDialog(null, "Seleccione un curso");
            }else if(descripcion.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese la nota que desea crear");
            }
        }else{
            try{
                controladorGestionNotas.agregarNota((Curso) cbCursos.getSelectedItem(), descripcion);
                llenarTablaCurso((Curso) cbCursos.getSelectedItem());
                llenarTablaNotasCurso((Curso) cbCursos.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Nota creada con éxito");
            }catch(CantidadNotasInvalidaException | NotaExistenteException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarNotasActionPerformed
        Curso curso = (Curso) cbCursos.getSelectedItem();
        int cantidadNotas = curso.getNotasEstudiantes().size();
        for (int i = 0; i < tablaNotas.getRowCount(); i++) {
            Estudiante estudiante = controladorGestionNotas.obtenerEstudiante((String) tablaNotas.getValueAt(i, 0));
            for (int j = 0; j < cantidadNotas; j++) {
                try {
                    if (tablaNotas.getValueAt(i, j + 2) == null) {
                        tablaNotas.setValueAt(0.0, i, j + 2);
                    }
                    double notaObtenida = (Double) tablaNotas.getValueAt(i, j + 2);
                    DetalleNota detalleNota = new DetalleNota(estudiante, notaObtenida);
                    String razonNota = tablaNotas.getColumnName(j + 2).split(" - ")[0].trim();
                    controladorGestionNotas.actualizarNotas(curso, razonNota, detalleNota);
                } catch (NotaNoEncontradaException | NotaInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Notas agregadas con éxito");
        llenarTablaCurso(curso);
    }//GEN-LAST:event_btnGuardarNotasActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VistaPrincipalDocente vistaDocente = new VistaPrincipalDocente(docente);
        vistaDocente.setVisible(true);
        vistaDocente.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tablaNotasCurso.getSelectedRow();
        if(fila != -1){
            try{
                Curso curso = (Curso) cbCursos.getSelectedItem();
                String  descripcion = (String) tablaNotasCurso.getValueAt(fila, 1);
                controladorGestionNotas.eliminarNota(curso, descripcion);
                llenarTablaCurso(curso);
                llenarTablaNotasCurso(curso);
                JOptionPane.showMessageDialog(null, "Nota eliminada con éxito");
            }catch(NotaNoEncontradaException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursosActionPerformed

    private void btnGuardarCambiosNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosNotasActionPerformed
       IList<Nota> notasModificadas = new Listas<>();
        for (int i = 0; i < tablaNotasCurso.getRowCount(); i++) {
            String razon = (String)tablaNotasCurso.getValueAt(i, 1);
            if(razon.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ingrese la descripción de la nota");
                return;
            }
            if(tablaNotasCurso.getValueAt(i, 2) == null){
                tablaNotasCurso.setValueAt(0.0,i, 2);
            }
            Nota nota = new Nota(razon, (int)tablaNotasCurso.getValueAt(i, 2));
            notasModificadas.agregar(nota);
        }
        try{
            Curso curso = (Curso) cbCursos.getSelectedItem();
            controladorGestionNotas.actualizarNota(notasModificadas, curso);
            llenarTablaCurso(curso);
            llenarTablaNotasCurso(curso);
            JOptionPane.showMessageDialog(null, "Notas modificadas correctamente");
        }catch(NotaInvalidaException | PorcentajeInvalidoException | NotaExistenteException | NotaNoEncontradaException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnGuardarCambiosNotasActionPerformed

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
//            java.util.logging.Logger.getLogger(VistaGestionNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VistaGestionNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VistaGestionNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VistaGestionNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new VistaGestionNotas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarCambiosNotas;
    private javax.swing.JButton btnGuardarNotas;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Object> cbCursos;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JTable tablaNotas;
    private javax.swing.JTable tablaNotasCurso;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}

package Vistas;

public class CargaDeNotasVista extends javax.swing.JInternalFrame {
    private BD.AlumnoData alumnoData;
    private BD.InscripcionData inscripcionData;
    private javax.swing.table.DefaultTableModel modeloPorDefecto;
    
    public CargaDeNotasVista() {
        initComponents();
        alumnoData = new BD.AlumnoData (TPTransversal.Universidad.c);
        inscripcionData = new BD.InscripcionData (TPTransversal.Universidad.c);
        modeloPorDefecto = new javax.swing.table.DefaultTableModel (new String [] {"ID", "Nombre", "Nota"}, 20);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setText("CARGA DE NOTAS");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("ALUMNO");

        java.util.Iterator <Recursos.Alumno> itAlumnos = alumnoData.obtenerAlumnos().iterator();
        cbAlumno.setModel(new javax.swing.DefaultComboBoxModel<Recursos.Alumno>());
        while (itAlumnos.hasNext()) {
            cbAlumno.addItem (itAlumnos.next ());
        }
        cbAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAlumnoItemStateChanged(evt);
            }
        });

        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane1.setViewportView(TablaMaterias);

        btnGuardar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnGuardar)
                        .addGap(37, 37, 37)
                        .addComponent(btnCancelar)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(0, 75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (cbAlumno.getSelectedIndex() != -1 && TablaMaterias.getSelectedRow() != -1) {
            inscripcionData.actualizarNota(((Recursos.Alumno) cbAlumno.getSelectedItem()).getIdAlumno(),
                       Integer.parseInt (modeloPorDefecto.getValueAt (TablaMaterias.getSelectedRow (), 0).toString ()),
                       Double.parseDouble (modeloPorDefecto.getValueAt (TablaMaterias.getSelectedRow (), 2).toString ()));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Aparentemente vacío por ahora...
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAlumnoItemStateChanged
        Recursos.Alumno temp0 = (Recursos.Alumno) evt.getItem();
        int id = temp0.getIdAlumno();
        java.util.List <Recursos.Materia> materias = inscripcionData.listarMaterias(id);
        TablaMaterias.removeAll();
        java.util.Iterator <Recursos.Inscripcion> it0 = inscripcionData.obtenerInscripciones().stream ().filter (item -> item.getAlumno().getIdAlumno() == id).collect (java.util.stream.Collectors.toList ()).iterator();
        Recursos.Inscripcion temp2; //Un ítem.
        while (it0.hasNext ()) {
            temp2 = it0.next ();
            for (Recursos.Materia temp1:materias) {
                modeloPorDefecto.addRow(new Object [] {temp1.getIdMateria(), temp1.getNombreMateria(), temp2.getNota()});
            }
        }
    }//GEN-LAST:event_cbAlumnoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Recursos.Alumno> cbAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}


import Capa_Dato.Conexion;
import javax.swing.JOptionPane;
import desplazable.Desface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cr
 */
public class Primero extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    Connection cin = conexion.getConexion();
    PreparedStatement ps;
    /**
     * Creates new form Alumnos
     */
    Desface desplace;
    Desface des;
    int Activado = 0;
    int btnCRUD = 1;

    static ResultSet res;

    void Registrar() {
        //String insertar = "INSERT INTO Alumnos (CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto)VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = cin.prepareStatement("SELECT * FROM Grupos WHERE CURPAlumno = ?");
            ps.setObject(1, this.T_CurpA.getText());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "CURP del Alumno Repetida, Cambiela");
            } else {
                ps = cin.prepareStatement("INSERT INTO Grupos (Grupo, CURPEducadora, CURPAlumno, AsistenciasMes, InasistenciasMes, AsistenciasTotales, InasistenciasTotales)VALUES (?,?,?,?,?,?,?)");
                //String grup = "G1";
                ps.setString(1, "G1");
                ps.setString(2, T_CurpE.getText());
                ps.setString(3, T_CurpA.getText());
                ps.setInt(4, Integer.parseInt(T_AsisMes.getText()));
                ps.setInt(5, Integer.parseInt(T_InasisMes.getText()));
                ps.setInt(6, Integer.parseInt(T_Tasis.getText()));
                ps.setInt(7, Integer.parseInt(T_Tinasis.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Dato Guardado", "Grupo", JOptionPane.QUESTION_MESSAGE);
                Limpiar();
            }

        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dato No Guardado", "Grupo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void CargarArticulo() {
        DefaultTableModel modelo = (DefaultTableModel) TB_Primero.getModel();
        modelo.setRowCount(0);
        //String grup = "G1";
        //contador numero progresivo
         int c = 1;
        //CURPEducadora (Agregar una nueva columna a la tabla)
        try {
            ps = cin.prepareStatement("SELECT CURPAlumno, AsistenciasMes, InasistenciasMes, AsistenciasTotales, InasistenciasTotales FROM Grupos WHERE Grupo=?");
            ps.setObject(1, "G1");
            res = ps.executeQuery();
            while (res.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(c));
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                //v.add(res.getString(6));
                modelo.addRow(v);
                TB_Primero.setModel(modelo);
                c++;
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No Existen Datos", "Grupo", JOptionPane.QUESTION_MESSAGE);
        }
    }
    public void CargarArticulo2() {
        DefaultTableModel modelo = (DefaultTableModel) TB_Primero1.getModel();
        modelo.setRowCount(1);
        //String grup = "G1";
        //contador numero progresivo
         int c = 1;
        //CURPEducadora (Agregar una nueva columna a la tabla)
        try {
            ps = cin.prepareStatement("SELECT Alumnos.NombreAlumno, Grupos.AsistenciasMes, Grupos.InasistenciasMes, Grupos.AsistenciasTotales, Grupos.InasistenciasTotales FROM Alumnos FULL JOIN Grupos ON Alumnos.CURPAlumno=Grupos.CURPAlumno WHERE Grupo=?");
            ps.setObject(1, "G1");
            res = ps.executeQuery();
            while (res.next()) {
                Vector v = new Vector();
                v.add(String.valueOf(c));
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                //v.add(res.getString(6));
                modelo.addRow(v);
                TB_Primero1.setModel(modelo);
                c++;
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "No Existen Datos", "Grupo", JOptionPane.QUESTION_MESSAGE);
        }
    }
    public Primero() {
        initComponents();

        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Primero");
        MenuDesplegable.setBackground(new Color(150, 102, 255));
        PanelTabla.setBackground(new Color(150, 102, 255));

        TextPrompt holder;
        holder = new TextPrompt("Introduzca el dato que desea buscar", T_Buscar);
        holder = new TextPrompt("CURP de la educadora", T_CurpE);
        holder = new TextPrompt("CURP del alumno", T_CurpA);
        holder = new TextPrompt("Asistencias en el mes", T_AsisMes);
        holder = new TextPrompt("Inasistencias en el mes", T_InasisMes);
        holder = new TextPrompt("Total de asistencias", T_Tasis);
        holder = new TextPrompt("Total de inasistencias", T_Tinasis);

        /*PanelTabla.setLayout(new FlowLayout());
        PanelTabla.validate();*/
        desplace = new Desface();
        des = new Desface();

        //desplace.desplazarIzquierda(MenuDesplegable, MenuDesplegable.getX(), -300, 300, 300);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        B_ClicR = new javax.swing.JMenuItem();
        B_ClicA = new javax.swing.JMenuItem();
        B_ClicE = new javax.swing.JMenuItem();
        MenuDesplegable = new javax.swing.JPanel();
        TituloPanel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        T_CurpE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        T_CurpA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        T_AsisMes = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        T_InasisMes = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        T_Tasis = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        T_Tinasis = new javax.swing.JTextField();
        B_Datos = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TB_Primero = new javax.swing.JTable();
        btnMostrarDatos = new javax.swing.JButton();
        B_Desplegar = new javax.swing.JButton();
        B_BuscarDatos = new javax.swing.JButton();
        T_Buscar = new javax.swing.JTextField();
        T_NombreArchivo = new javax.swing.JTextField();
        B_Exportar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TB_Primero1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        B_Inicio = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        B_Opciones = new javax.swing.JMenuItem();
        B_CerrarS = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        B_Director = new javax.swing.JMenuItem();
        B_Alumnos = new javax.swing.JMenuItem();
        B_Educadoras = new javax.swing.JMenuItem();
        B_General = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        B_Segundo = new javax.swing.JMenuItem();
        B_Tercero = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        B_VerE = new javax.swing.JMenuItem();
        B_Registrar = new javax.swing.JMenuItem();

        B_ClicR.setText("Registrar");
        B_ClicR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ClicRActionPerformed(evt);
            }
        });
        jPopupMenu1.add(B_ClicR);

        B_ClicA.setText("Actualizar");
        B_ClicA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ClicAActionPerformed(evt);
            }
        });
        jPopupMenu1.add(B_ClicA);

        B_ClicE.setText("Eliminar");
        B_ClicE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ClicEActionPerformed(evt);
            }
        });
        jPopupMenu1.add(B_ClicE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(150, 102, 255));

        MenuDesplegable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MenuDesplegable.setPreferredSize(new java.awt.Dimension(290, 547));

        TituloPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloPanel.setText("Registre los datos");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("CURP de la educadora");

        T_CurpE.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("CURP del alumno");

        T_CurpA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Asistencias del alumno en el mes");

        T_AsisMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Inasistencias del alumno en el mes");

        T_InasisMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Asistencias totales del alumno");

        T_Tasis.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Inasistencias totales del alumno");

        T_Tinasis.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        B_Datos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Datos.setText("Registrar datos");
        B_Datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DatosActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuDesplegableLayout = new javax.swing.GroupLayout(MenuDesplegable);
        MenuDesplegable.setLayout(MenuDesplegableLayout);
        MenuDesplegableLayout.setHorizontalGroup(
            MenuDesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TituloPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_CurpE, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_CurpA, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_AsisMes, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_InasisMes, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
            .addComponent(T_Tasis, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Tinasis, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuDesplegableLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(MenuDesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addComponent(B_Datos)))
        );
        MenuDesplegableLayout.setVerticalGroup(
            MenuDesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuDesplegableLayout.createSequentialGroup()
                .addComponent(TituloPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_CurpE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_CurpA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_AsisMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_InasisMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Tasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Tinasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(B_Datos)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TB_Primero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TB_Primero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Progresivo", "Curp del Alumno", "Asistencias Mes", "Inasistencias Mes", "Asistencias Totales", "Inasistencias Totales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TB_Primero.setComponentPopupMenu(jPopupMenu1);
        TB_Primero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_PrimeroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TB_Primero);

        btnMostrarDatos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnMostrarDatos.setText("Mostrar todos los datos");
        btnMostrarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarDatosActionPerformed(evt);
            }
        });

        B_Desplegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IconoRegistrar.png"))); // NOI18N
        B_Desplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DesplegarActionPerformed(evt);
            }
        });

        B_BuscarDatos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_BuscarDatos.setText("Buscar");
        B_BuscarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_BuscarDatosActionPerformed(evt);
            }
        });

        B_Exportar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Excel_logo.png"))); // NOI18N
        B_Exportar.setText("Exportar");
        B_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ExportarActionPerformed(evt);
            }
        });

        TB_Primero1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TB_Primero1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"N.P.", "Nombre", "A", "I", "A Totales", "I Totales"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Progresivo", "Alumno", "Asistencias Mes", "Inasistencias Mes", "Asistencias Totales", "Inasistencias Totales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TB_Primero1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_Primero1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TB_Primero1);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel1.setText("Formato de Exportacion");

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addComponent(B_Desplegar)
                .addGap(18, 18, 18)
                .addComponent(T_Buscar)
                .addGap(18, 18, 18)
                .addComponent(B_BuscarDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrarDatos)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTablaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(T_NombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(B_Exportar))
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addGroup(PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTablaLayout.createSequentialGroup()
                .addGroup(PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(B_Desplegar)
                    .addGroup(PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMostrarDatos)
                        .addComponent(B_BuscarDatos)
                        .addComponent(T_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Exportar)
                    .addComponent(T_NombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        B_Inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Inicio.png"))); // NOI18N
        B_Inicio.setText("Inicio");
        B_Inicio.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        B_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_InicioMouseClicked(evt);
            }
        });
        B_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_InicioActionPerformed(evt);
            }
        });
        jMenuBar1.add(B_Inicio);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Sesion.png"))); // NOI18N
        jMenu1.setText("Sesión");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        B_Opciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Opciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Sesion.png"))); // NOI18N
        B_Opciones.setText("Opciones");
        B_Opciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_OpcionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_OpcionesMouseExited(evt);
            }
        });
        B_Opciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_OpcionesActionPerformed(evt);
            }
        });
        jMenu1.add(B_Opciones);

        B_CerrarS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_CerrarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Sesion.png"))); // NOI18N
        B_CerrarS.setText("Cerrar Sesión");
        B_CerrarS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_CerrarSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_CerrarSMouseExited(evt);
            }
        });
        B_CerrarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_CerrarSActionPerformed(evt);
            }
        });
        jMenu1.add(B_CerrarS);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        jMenu2.setText("Personal");
        jMenu2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        B_Director.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Director.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        B_Director.setText("Director");
        B_Director.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_DirectorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_DirectorMouseExited(evt);
            }
        });
        B_Director.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DirectorActionPerformed(evt);
            }
        });
        jMenu2.add(B_Director);

        B_Alumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Alumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        B_Alumnos.setText("Alumnos");
        B_Alumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_AlumnosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_AlumnosMouseExited(evt);
            }
        });
        B_Alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_AlumnosActionPerformed(evt);
            }
        });
        jMenu2.add(B_Alumnos);

        B_Educadoras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Educadoras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        B_Educadoras.setText("Educadoras");
        B_Educadoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_EducadorasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_EducadorasMouseExited(evt);
            }
        });
        B_Educadoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_EducadorasActionPerformed(evt);
            }
        });
        jMenu2.add(B_Educadoras);

        B_General.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_General.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        B_General.setText("General");
        B_General.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_GeneralMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_GeneralMouseExited(evt);
            }
        });
        B_General.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_GeneralActionPerformed(evt);
            }
        });
        jMenu2.add(B_General);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Grupos.png"))); // NOI18N
        jMenu3.setText("Grupos");
        jMenu3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        B_Segundo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Segundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Grupos.png"))); // NOI18N
        B_Segundo.setText("Segundo");
        B_Segundo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_SegundoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_SegundoMouseExited(evt);
            }
        });
        B_Segundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_SegundoActionPerformed(evt);
            }
        });
        jMenu3.add(B_Segundo);

        B_Tercero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Grupos.png"))); // NOI18N
        B_Tercero.setText("Tercero");
        B_Tercero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_TerceroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_TerceroMouseExited(evt);
            }
        });
        B_Tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_TerceroActionPerformed(evt);
            }
        });
        jMenu3.add(B_Tercero);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Evaluaciones.png"))); // NOI18N
        jMenu4.setText("Evaluaciones");
        jMenu4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        B_VerE.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_VerE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Evaluaciones.png"))); // NOI18N
        B_VerE.setText("Ver Evaluaciones");
        B_VerE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_VerEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_VerEMouseExited(evt);
            }
        });
        B_VerE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_VerEActionPerformed(evt);
            }
        });
        jMenu4.add(B_VerE);

        B_Registrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Evaluaciones.png"))); // NOI18N
        B_Registrar.setText("Registrar");
        B_Registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_RegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_RegistrarMouseExited(evt);
            }
        });
        B_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_RegistrarActionPerformed(evt);
            }
        });
        jMenu4.add(B_Registrar);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(MenuDesplegable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuDesplegable, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
            .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_OpcionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_OpcionesMouseEntered

    }//GEN-LAST:event_B_OpcionesMouseEntered

    private void B_OpcionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_OpcionesMouseExited

    }//GEN-LAST:event_B_OpcionesMouseExited

    private void B_CerrarSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_CerrarSMouseEntered

    }//GEN-LAST:event_B_CerrarSMouseEntered

    private void B_CerrarSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_CerrarSMouseExited

    }//GEN-LAST:event_B_CerrarSMouseExited

    private void B_CerrarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_CerrarSActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            InicioSesion p = new InicioSesion();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_CerrarSActionPerformed

    private void B_DirectorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_DirectorMouseEntered

    }//GEN-LAST:event_B_DirectorMouseEntered

    private void B_DirectorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_DirectorMouseExited

    }//GEN-LAST:event_B_DirectorMouseExited

    private void B_AlumnosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_AlumnosMouseEntered

    }//GEN-LAST:event_B_AlumnosMouseEntered

    private void B_AlumnosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_AlumnosMouseExited

    }//GEN-LAST:event_B_AlumnosMouseExited

    private void B_AlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AlumnosActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Alumnos p = new Alumnos();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_AlumnosActionPerformed

    private void B_GeneralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_GeneralMouseEntered

    }//GEN-LAST:event_B_GeneralMouseEntered

    private void B_GeneralMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_GeneralMouseExited

    }//GEN-LAST:event_B_GeneralMouseExited

    private void B_SegundoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_SegundoMouseEntered

    }//GEN-LAST:event_B_SegundoMouseEntered

    private void B_SegundoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_SegundoMouseExited

    }//GEN-LAST:event_B_SegundoMouseExited

    private void B_TerceroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_TerceroMouseEntered

    }//GEN-LAST:event_B_TerceroMouseEntered

    private void B_TerceroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_TerceroMouseExited

    }//GEN-LAST:event_B_TerceroMouseExited

    private void B_VerEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_VerEMouseEntered

    }//GEN-LAST:event_B_VerEMouseEntered

    private void B_VerEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_VerEMouseExited

    }//GEN-LAST:event_B_VerEMouseExited

    private void B_RegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_RegistrarMouseEntered

    }//GEN-LAST:event_B_RegistrarMouseEntered

    private void B_RegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_RegistrarMouseExited

    }//GEN-LAST:event_B_RegistrarMouseExited

    private void B_DesplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DesplegarActionPerformed
        //Animacion del menu

        if (Activado == 0) {
            //MenuDesplegable.setSize(new Dimension(0, MenuDesplegable.getHeight()));
            desplace.desplazarIzquierda(MenuDesplegable, MenuDesplegable.getX(), -310, 10, 10);
            des.desplazarIzquierda(PanelTabla, PanelTabla.getX(), 20, 10, 10);
            this.setSize(new Dimension(this.getWidth() - 250, this.getHeight()));
            //PanelTabla.setSize(new Dimension(900,PanelTabla.getHeight()));
            Activado = 1;
        } else {
            //MenuDesplegable.setSize(new Dimension(300, MenuDesplegable.getHeight()));
            desplace.desplazarDerecha(MenuDesplegable, MenuDesplegable.getX(), 10, 10, 10);
            des.desplazarDerecha(PanelTabla, PanelTabla.getX(), 310, 10, 10);
            this.setSize(new Dimension(this.getWidth() + 250, this.getHeight()));
            //PanelTabla.setSize(new Dimension(618,PanelTabla.getHeight()));
            Activado = 0;
        }

        /*if (MenuDesplegable.getX() == 0) {
            desplace.desplazarIzquierda(MenuDesplegable, MenuDesplegable.getX(), -300, 10, 10);

        } else if (MenuDesplegable.getX() == -300) {
            desplace.desplazarDerecha(MenuDesplegable, MenuDesplegable.getX(), 0, 10, 10);
        }*/
    }//GEN-LAST:event_B_DesplegarActionPerformed

    private void B_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_InicioActionPerformed

    }//GEN-LAST:event_B_InicioActionPerformed

    private void B_InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_InicioMouseClicked
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Principal p = new Principal();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_InicioMouseClicked

    private void B_ClicRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ClicRActionPerformed
        if (Activado == 1) {
            desplace.desplazarDerecha(MenuDesplegable, MenuDesplegable.getX(), 10, 10, 10);
            des.desplazarDerecha(PanelTabla, PanelTabla.getX(), 310, 10, 10);
            this.setSize(new Dimension(this.getWidth() + 250, this.getHeight()));
            Activado = 0;
        }
        btnCRUD = 1;
        if (btnCRUD == 1) {
            T_CurpA.setEnabled(true);
            TituloPanel.setText("Registre los datos");
            B_Datos.setText("Registrar datos");
        }

    }//GEN-LAST:event_B_ClicRActionPerformed

    private void B_ClicAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ClicAActionPerformed
        if (Activado == 1) {
            desplace.desplazarDerecha(MenuDesplegable, MenuDesplegable.getX(), 10, 10, 10);
            des.desplazarDerecha(PanelTabla, PanelTabla.getX(), 310, 10, 10);
            this.setSize(new Dimension(this.getWidth() + 250, this.getHeight()));
            Activado = 0;
        }

        btnCRUD = 2;
        if (btnCRUD == 2) {
            T_CurpA.setEnabled(false);
            TituloPanel.setText("Actualice los datos");
            B_Datos.setText("Actualice datos");
        }

    }//GEN-LAST:event_B_ClicAActionPerformed

    private void B_ClicEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ClicEActionPerformed
        if (Activado == 1) {
            desplace.desplazarDerecha(MenuDesplegable, MenuDesplegable.getX(), 10, 10, 10);
            des.desplazarDerecha(PanelTabla, PanelTabla.getX(), 310, 10, 10);
            this.setSize(new Dimension(this.getWidth() + 250, this.getHeight()));
            Activado = 0;
        }
        btnCRUD = 3;
        if (btnCRUD == 3) {
            T_CurpA.setEnabled(true);
            TituloPanel.setText("Elimine los datos");
            B_Datos.setText("Elimine datos");
        }

    }//GEN-LAST:event_B_ClicEActionPerformed

    private void B_DatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DatosActionPerformed
        //acciones del boton Registrar datos
        if (btnCRUD == 1) {
            Registrar();
            System.out.println("entro registar");
        } else if (btnCRUD == 2) {
            Modificar();
            System.out.println("entro modificar");
        } else if (btnCRUD == 3) {
            if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                    "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                int fila = TB_Primero.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Debe Seleccionar una Fila");
                } else {
                    //int id = Integer.parseInt(tblAlumnos.getValueAt(fila, 0).toString());
                    Eliminar();
                    //JOptionPane.showMessageDialog(this, "Producto Eliminado");
                }

            }
            //Eliminar();
            System.out.println("entro eliminar");
        }

    }//GEN-LAST:event_B_DatosActionPerformed

    private void btnMostrarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarDatosActionPerformed
        // TODO add your handling code here:
        CargarArticulo();
        CargarArticulo2();
    }//GEN-LAST:event_btnMostrarDatosActionPerformed

    private void TB_PrimeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_PrimeroMouseClicked
        // TODO add your handling code here:
        try {
            int fila = TB_Primero.getSelectedRow();
            String curp = TB_Primero.getValueAt(fila, 1).toString();

            ps = cin.prepareStatement("SELECT CURPEducadora, CURPAlumno, AsistenciasMes, InasistenciasMes, AsistenciasTotales, InasistenciasTotales FROM Grupos WHERE CURPAlumno=?");
            ps.setString(1, curp);
            //ps.setString(2, "G1");
            res = ps.executeQuery();

            while (res.next()) {
                T_CurpE.setText(res.getString("CURPEducadora"));
                T_CurpA.setText(String.valueOf(curp));
                T_AsisMes.setText(res.getString("AsistenciasMes"));
                T_InasisMes.setText(res.getString("InasistenciasMes"));
                T_Tasis.setText(res.getString("AsistenciasTotales"));
                T_Tinasis.setText(res.getString("InasistenciasTotales"));
            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());

        }
    }//GEN-LAST:event_TB_PrimeroMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void B_BuscarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BuscarDatosActionPerformed
        // TODO add your handling code here:
          int c = 1;
        try {
            ps = cin.prepareStatement("SELECT * FROM Grupos WHERE CURPAlumno=? AND Grupo=?");
            ps.setObject(1, this.T_Buscar.getText());
            ps.setObject(2, "G1");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DefaultTableModel modelo = (DefaultTableModel) TB_Primero.getModel();
                modelo.setRowCount(0);

                //String curp = T_Buscar.getText();
                // CURPEducadora
                ps = cin.prepareStatement("SELECT  CURPAlumno, AsistenciasMes, InasistenciasMes, AsistenciasTotales, InasistenciasTotales FROM Grupos WHERE CURPAlumno=? AND Grupo=?");
                ps.setString(1, T_Buscar.getText());
                ps.setString(2, "G1");
                res = ps.executeQuery();
              
                //try {
                while (res.next()) {
                    Vector v = new Vector();
                    v.add(String.valueOf(c));
                    v.add(res.getString(1));
                    v.add(res.getString(2));
                    v.add(res.getString(3));
                    v.add(res.getString(4));
                    v.add(res.getString(5));
                    //v.add(res.getString(6));
                    modelo.addRow(v);
                    TB_Primero.setModel(modelo);
                    
                }
                JOptionPane.showMessageDialog(this, "Dato Encontrado", "Consulta", JOptionPane.QUESTION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El Dato No Existe");

                //Limpiar();
            }
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Dato No Guardado", "Registro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_B_BuscarDatosActionPerformed

    private void B_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_RegistrarActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Registrar p = new Registrar();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_RegistrarActionPerformed

    private void B_OpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_OpcionesActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Opciones p = new Opciones();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_OpcionesActionPerformed

    private void B_DirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DirectorActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Director p = new Director();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_DirectorActionPerformed

    private void B_GeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_GeneralActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            General p = new General();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_GeneralActionPerformed

    private void B_SegundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_SegundoActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Segundo p = new Segundo();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_SegundoActionPerformed

    private void B_TerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_TerceroActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Tercero p = new Tercero();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_TerceroActionPerformed

    private void B_VerEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_VerEActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            VerEvaluaciones p = new VerEvaluaciones();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_VerEActionPerformed

    private void B_EducadorasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_EducadorasMouseEntered

    }//GEN-LAST:event_B_EducadorasMouseEntered

    private void B_EducadorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_EducadorasMouseExited

    }//GEN-LAST:event_B_EducadorasMouseExited

    private void B_EducadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_EducadorasActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Educadoras p = new Educadoras();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_EducadorasActionPerformed

    private void B_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ExportarActionPerformed
        impresion();
    }//GEN-LAST:event_B_ExportarActionPerformed

    private void TB_Primero1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_Primero1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_Primero1MouseClicked

    void impresion() {

        String rutaCar = "C:\\Josefa Ortiz de Dominguez\\Primero";
        File carpeta = new File(rutaCar);
        String nombre = T_NombreArchivo.getText();
        String rutaArch = "";

        if (T_NombreArchivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduzca un nombre para el archivo");
        }else{
            if (carpeta.exists()) {
                rutaArch = "C:\\Josefa Ortiz de Dominguez\\Primero\\" + nombre + ".xls";
                File file = new File(rutaArch);
                ExportarExcel excel = new ExportarExcel(TB_Primero1, file, T_NombreArchivo.getText());
                excel.export();
                if(true){
                    JOptionPane.showMessageDialog(this, "Documento generado con exito \n" + "Espere...");
                }

            } else {
                carpeta.mkdirs();

                rutaArch = "C:\\Josefa Ortiz de Dominguez\\Primero\\" + nombre + ".xls";
                File file = new File(rutaArch);
                ExportarExcel excel = new ExportarExcel(TB_Primero1, file, nombre);
                excel.export();
                if(true){
                    JOptionPane.showMessageDialog(this, "Documento generado con exito \n" + "Espere...");
                }
            }
        }
        
        try{
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", rutaArch);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(VerEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Se intentó abrir el documento pero ocurrió un error");
        }

    }
    
    void Modificar() {
        try {
            ps = cin.prepareStatement("UPDATE Grupos SET CURPEducadora=?, AsistenciasMes=?, InasistenciasMes=?, AsistenciasTotales=?, InasistenciasTotales=? WHERE CURPAlumno=?");
            ps.setString(1, T_CurpE.getText());
            ps.setInt(2, Integer.parseInt(T_AsisMes.getText()));
            ps.setInt(3, Integer.parseInt(T_InasisMes.getText()));
            ps.setInt(4, Integer.parseInt(T_Tasis.getText()));
            ps.setInt(5, Integer.parseInt(T_Tinasis.getText()));
            ps.setString(6, T_CurpA.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Dato Modificado", "Modificar", JOptionPane.QUESTION_MESSAGE);
            Limpiar();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dato No Modificado", "Modificar", JOptionPane.ERROR_MESSAGE);
        }

    }

    void Eliminar() {
        try {
            ps = cin.prepareStatement("DELETE FROM Grupos WHERE CURPAlumno=?");
            ps.setString(1, T_CurpA.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Alumno Eliminado", "Ingresar", JOptionPane.QUESTION_MESSAGE);
            Limpiar();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Alumno No Guardado", "Ingresar", JOptionPane.ERROR_MESSAGE);
        }

    }

    void Limpiar() {
        T_CurpE.setText("");
        T_CurpA.setText("");
        T_AsisMes.setText("");
        T_InasisMes.setText("");
        T_Tasis.setText("");
        T_Tinasis.setText("");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introducedT_AsisMesva SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*T_AsisMese and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch (ClassNotFoundException ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
                }catch (InstantiationException ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
                }catch (IllegalAccessException ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
                }catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Primero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem B_Alumnos;
    private javax.swing.JButton B_BuscarDatos;
    private javax.swing.JMenuItem B_CerrarS;
    private javax.swing.JMenuItem B_ClicA;
    private javax.swing.JMenuItem B_ClicE;
    private javax.swing.JMenuItem B_ClicR;
    private javax.swing.JButton B_Datos;
    private javax.swing.JButton B_Desplegar;
    private javax.swing.JMenuItem B_Director;
    private javax.swing.JMenuItem B_Educadoras;
    private javax.swing.JButton B_Exportar;
    private javax.swing.JMenuItem B_General;
    private javax.swing.JMenu B_Inicio;
    private javax.swing.JMenuItem B_Opciones;
    private javax.swing.JMenuItem B_Registrar;
    private javax.swing.JMenuItem B_Segundo;
    private javax.swing.JMenuItem B_Tercero;
    private javax.swing.JMenuItem B_VerE;
    private javax.swing.JPanel MenuDesplegable;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JTable TB_Primero;
    private javax.swing.JTable TB_Primero1;
    private javax.swing.JTextField T_AsisMes;
    private javax.swing.JTextField T_Buscar;
    private javax.swing.JTextField T_CurpA;
    private javax.swing.JTextField T_CurpE;
    private javax.swing.JTextField T_InasisMes;
    private javax.swing.JTextField T_NombreArchivo;
    private javax.swing.JTextField T_Tasis;
    private javax.swing.JTextField T_Tinasis;
    private javax.swing.JLabel TituloPanel;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMostrarDatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

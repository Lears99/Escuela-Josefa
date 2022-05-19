
import Capa_Dato.Conexion;
import javax.swing.JOptionPane;
import desplazable.Desface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
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
public class Alumnos extends javax.swing.JFrame {

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

    void Registrar() {
        //String insertar = "INSERT INTO Alumnos (CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto)VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = cin.prepareStatement("SELECT * FROM Alumnos WHERE CURPAlumno = ?");
            ps.setObject(1, this.T_Curp.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "CURP Repetida, Cambiela");
            } else {

                ps = cin.prepareStatement("INSERT INTO Alumnos (CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto)VALUES (?,?,?,?,?,?,?,?)");
                ps.setString(1, T_Curp.getText());
                ps.setString(2, T_Nombre.getText());
                ps.setString(3, T_Madre.getText());
                ps.setString(4, T_Padre.getText());
                ps.setInt(5, Integer.parseInt(T_Anio.getText()));
                ps.setInt(6, Integer.parseInt(T_Meses.getText()));
                ps.setString(7, T_Direccion.getText());
                ps.setString(8, T_Tel.getText());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Dato Guardado", "Registro", JOptionPane.QUESTION_MESSAGE);
                Limpiar();
            }
            /*int registro = ps.executeUpdate();
           if(registro > 0){
               JOptionPane.showMessageDialog(this, "Dato Guardado","Ingresar",JOptionPane.QUESTION_MESSAGE);
               Limpiar();
           }else{
               JOptionPane.showMessageDialog(this, "Dato No Guardado","Ingresar",JOptionPane.ERROR_MESSAGE);
           }*/
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dato No Guardado", "Registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    static ResultSet res;

    public void CargarArticulo() {
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        modelo.setRowCount(0);
        res = conexion.Consulta("select * from Alumnos");

        try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                modelo.addRow(v);
                tblAlumnos.setModel(modelo);
            }
        }catch (SQLException e) {

        }
    }

    public Alumnos() {
        initComponents();

        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle("Alumnos");
        MenuDesplegable.setBackground(new Color(122, 164, 255));
        PanelTabla.setBackground(new Color(122, 164, 255));

        TextPrompt holder;
        holder = new TextPrompt("Introduzca el dato que desea buscar", T_Buscar);
        holder = new TextPrompt("Introduzca la CURP", T_Curp);
        holder = new TextPrompt("Introduzca el nombre", T_Nombre);
        holder = new TextPrompt("Nombre del padre", T_Madre);
        holder = new TextPrompt("Nombre de la madre", T_Padre);
        holder = new TextPrompt("Introduzca los años cumplidos", T_Anio);
        holder = new TextPrompt("Introduzca los meses cumplidos", T_Meses);
        holder = new TextPrompt("Introduzca la direccion proporcionada", T_Direccion);
        holder = new TextPrompt("Introduzca el telefono de contacto", T_Tel);

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
        T_Curp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        T_Nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        T_Madre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        T_Padre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        T_Anio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        T_Meses = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        T_Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        T_Tel = new javax.swing.JTextField();
        B_BuscarDocumentos = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        B_Datos = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnMostrarDatos = new javax.swing.JButton();
        B_Desplegar = new javax.swing.JButton();
        B_BuscarDatos = new javax.swing.JButton();
        T_Buscar = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        B_Inicio = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        B_Opciones = new javax.swing.JMenuItem();
        B_CerrarS = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        B_Director = new javax.swing.JMenuItem();
        B_Educadoras = new javax.swing.JMenuItem();
        B_General = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        B_Primero = new javax.swing.JMenuItem();
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

        MenuDesplegable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        MenuDesplegable.setPreferredSize(new java.awt.Dimension(290, 547));

        TituloPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TituloPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloPanel.setText("Registre los datos");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("CURP del alumno");

        T_Curp.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Nombre del alumno");

        T_Nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nombre de la madre");

        T_Madre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Nombre del padre");

        T_Padre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Años del alumno");

        T_Anio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Meses del alumno");

        T_Meses.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Direccion proporcionada");

        T_Direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Telefono de contacto");

        T_Tel.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        B_BuscarDocumentos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_BuscarDocumentos.setText("Buscar");
        B_BuscarDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_BuscarDocumentosActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Seleccione los documentos del alumno");

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
            .addComponent(T_Curp, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Nombre, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Madre, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Padre, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Anio, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Meses, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Direccion, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(T_Tel, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
            .addGroup(MenuDesplegableLayout.createSequentialGroup()
                .addComponent(B_BuscarDocumentos)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(T_Curp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Madre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Padre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_BuscarDocumentos)
                .addGap(18, 18, 18)
                .addComponent(B_Datos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Curp Alumno", "Nombre Alumno", "Nombre Madre", "Nombre Padre", "Años del Alumno", "Meses del Alumno", "Direccion", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAlumnos.setComponentPopupMenu(jPopupMenu1);
        tblAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlumnos);

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

        T_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T_BuscarActionPerformed(evt);
            }
        });

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
                .addGap(18, 18, 18)
                .addComponent(btnMostrarDatos))
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
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

        B_Primero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        B_Primero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Grupos.png"))); // NOI18N
        B_Primero.setText("Primero");
        B_Primero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                B_PrimeroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B_PrimeroMouseExited(evt);
            }
        });
        B_Primero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_PrimeroActionPerformed(evt);
            }
        });
        jMenu3.add(B_Primero);

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
            .addComponent(MenuDesplegable, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
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

    private void B_GeneralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_GeneralMouseEntered

    }//GEN-LAST:event_B_GeneralMouseEntered

    private void B_GeneralMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_GeneralMouseExited

    }//GEN-LAST:event_B_GeneralMouseExited

    private void B_PrimeroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_PrimeroMouseEntered

    }//GEN-LAST:event_B_PrimeroMouseEntered

    private void B_PrimeroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_PrimeroMouseExited

    }//GEN-LAST:event_B_PrimeroMouseExited

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

                int fila = tblAlumnos.getSelectedRow();
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
    }//GEN-LAST:event_btnMostrarDatosActionPerformed

    private void tblAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnosMouseClicked
        // TODO add your handling code here:
        try {
            int fila = tblAlumnos.getSelectedRow();
            String curp = tblAlumnos.getValueAt(fila, 0).toString();

            ps = cin.prepareStatement("SELECT CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto FROM Alumnos WHERE CURPAlumno=?");
            ps.setString(1, curp);
            res = ps.executeQuery();

            while (res.next()) {
                T_Curp.setText(String.valueOf(curp));
                T_Nombre.setText(res.getString("NombreAlumno"));
                T_Madre.setText(res.getString("NombreMadre"));
                T_Padre.setText(res.getString("NombrePadre"));
                T_Anio.setText(res.getString("Años"));
                T_Meses.setText(res.getString("Meses"));
                T_Direccion.setText(res.getString("Direccion"));
                T_Tel.setText(res.getString("TelefonoContacto"));

            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());

        }
    }//GEN-LAST:event_tblAlumnosMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void B_BuscarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BuscarDatosActionPerformed
        // TODO add your handling code here:
        try {
            ps = cin.prepareStatement("SELECT * FROM Alumnos WHERE CURPAlumno = ?");
            ps.setObject(1, this.T_Buscar.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
                modelo.setRowCount(0);

                //String curp = T_Buscar.getText();
                ps = cin.prepareStatement("SELECT CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto FROM Alumnos WHERE CURPAlumno=?");
                ps.setString(1, T_Buscar.getText());
                res = ps.executeQuery();

                //try {
                while (res.next()) {
                    Vector v = new Vector();
                    v.add(res.getString(1));
                    v.add(res.getString(2));
                    v.add(res.getString(3));
                    v.add(res.getString(4));
                    v.add(res.getString(5));
                    v.add(res.getString(6));
                    v.add(res.getString(7));
                    v.add(res.getString(8));
                    modelo.addRow(v);
                    tblAlumnos.setModel(modelo);
                }
                JOptionPane.showMessageDialog(this, "Dato Encontrado", "Consulta", JOptionPane.QUESTION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El Dato No Existe");

                
                //Limpiar();
            }
        }catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Dato No Guardado", "Registro", JOptionPane.ERROR_MESSAGE);
        }

        /*try {
            DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
            modelo.setRowCount(0);

            //String curp = T_Buscar.getText();
            ps = cin.prepareStatement("SELECT CURPAlumno, NombreAlumno, NombreMadre, NombrePadre, Años, Meses, Direccion, TelefonoContacto FROM Alumnos WHERE CURPAlumno=?");
            ps.setString(1, T_Buscar.getText());
            res = ps.executeQuery();

            //try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getString(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getString(8));
                modelo.addRow(v);
                tblAlumnos.setModel(modelo);
            }
        }catch (SQLException e) {

        }*/
    }//GEN-LAST:event_B_BuscarDatosActionPerformed

    private void B_BuscarDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_BuscarDocumentosActionPerformed
        JOptionPane.showMessageDialog(this, "Seleccione documentos uno por uno");

        String rutaAct;

        //crear carpeta
        String rutaCar = "C:\\Josefa Ortiz de Dominguez\\Alumnos\\" + T_Nombre.getText();
        File carpeta = new File(rutaCar);

        try {
            //escoger archivo
            JFileChooser file = new JFileChooser();
            file.showOpenDialog(this);
            rutaAct = file.getSelectedFile().getPath();

            if (carpeta.exists()) {
                //mover archivo
                Path origen = Paths.get(rutaAct);
                Path destino = Paths.get(rutaCar);

                try {
                    Path mover = Files.move(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);

                    JOptionPane.showMessageDialog(this, "Documento transferido con exito");
                }catch (Exception ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);

                    JOptionPane.showMessageDialog(this, "Ocurrio un error");
                }

            } else {
                //crear carpeta
                carpeta.mkdirs();

                //mover archivo
                Path origen = Paths.get(rutaAct);
                Path destino = Paths.get(rutaCar);

                try {
                    Path mover = Files.move(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(this, "Documento transferido con exito");
                }catch (Exception ex) {
                    Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);

                    JOptionPane.showMessageDialog(this, "Ocurrio un error");
                }
            }

        }catch (Exception e) {
        }

    }//GEN-LAST:event_B_BuscarDocumentosActionPerformed

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

    private void B_PrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_PrimeroActionPerformed
        int salida;
        salida = JOptionPane.showConfirmDialog(this, "¿Seguro/a que desea salir? Se perderan los datos no guardados", "Confirmar", JOptionPane.OK_CANCEL_OPTION);

        if (salida == 0) {
            this.dispose();
            Primero p = new Primero();
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            p.setResizable(false);
        }
    }//GEN-LAST:event_B_PrimeroActionPerformed

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

    private void T_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T_BuscarActionPerformed

    void Modificar() {
        try {

            ps = cin.prepareStatement("UPDATE Alumnos SET NombreAlumno=?,NombreMadre=?, NombrePadre=?, Años=?, Meses=?, Direccion=?, TelefonoContacto=? WHERE CURPAlumno=?");
            ps.setString(1, T_Nombre.getText());
            ps.setString(2, T_Madre.getText());
            ps.setString(3, T_Padre.getText());
            ps.setInt(4, Integer.parseInt(T_Anio.getText()));
            ps.setInt(5, Integer.parseInt(T_Meses.getText()));
            ps.setString(6, T_Direccion.getText());
            ps.setString(7, T_Tel.getText());
            ps.setString(8, T_Curp.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Dato Modificado", "Ingresar", JOptionPane.QUESTION_MESSAGE);
            Limpiar();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dato No Modificado", "Ingresar", JOptionPane.ERROR_MESSAGE);
        }

    }

    void Eliminar() {
        try {

            ps = cin.prepareStatement("DELETE FROM Alumnos WHERE CURPAlumno=?");
            ps.setString(1, T_Curp.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Alumno Eliminado", "Ingresar", JOptionPane.QUESTION_MESSAGE);
            Limpiar();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Alumno No Eliminado", "Ingresar", JOptionPane.ERROR_MESSAGE);
        }

    }

    void Limpiar() {
        T_Curp.setText("");
        T_Nombre.setText("");
        T_Madre.setText("");
        T_Padre.setText("");
        T_Anio.setText("");
        T_Meses.setText("");
        T_Direccion.setText("");
        T_Tel.setText("");
    }

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

        /* Create and display the form */
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
                new Alumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_BuscarDatos;
    private javax.swing.JButton B_BuscarDocumentos;
    private javax.swing.JMenuItem B_CerrarS;
    private javax.swing.JMenuItem B_ClicA;
    private javax.swing.JMenuItem B_ClicE;
    private javax.swing.JMenuItem B_ClicR;
    private javax.swing.JButton B_Datos;
    private javax.swing.JButton B_Desplegar;
    private javax.swing.JMenuItem B_Director;
    private javax.swing.JMenuItem B_Educadoras;
    private javax.swing.JMenuItem B_General;
    private javax.swing.JMenu B_Inicio;
    private javax.swing.JMenuItem B_Opciones;
    private javax.swing.JMenuItem B_Primero;
    private javax.swing.JMenuItem B_Registrar;
    private javax.swing.JMenuItem B_Segundo;
    private javax.swing.JMenuItem B_Tercero;
    private javax.swing.JMenuItem B_VerE;
    private javax.swing.JPanel MenuDesplegable;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JTextField T_Anio;
    private javax.swing.JTextField T_Buscar;
    private javax.swing.JTextField T_Curp;
    private javax.swing.JTextField T_Direccion;
    private javax.swing.JTextField T_Madre;
    private javax.swing.JTextField T_Meses;
    private javax.swing.JTextField T_Nombre;
    private javax.swing.JTextField T_Padre;
    private javax.swing.JTextField T_Tel;
    private javax.swing.JLabel TituloPanel;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMostrarDatos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAlumnos;
    // End of variables declaration//GEN-END:variables
}

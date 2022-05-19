/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

/**
 *
 * @author cr
 */
public class ExportarExcel {

    private JTable table; //la tabla que recibira
    private File file;// archivo que manejaremos
    private String nombreTab; //nombre del archivo
//constructor ( inicializa las variables)

    public ExportarExcel(JTable table, File file, String nombreTab) {
        this.table = table;
        this.file = file;
        this.nombreTab = nombreTab;
        System.out.println(nombreTab);
    }
//metodo para exportar

    public boolean export() {
        try {
            System.out.print("iniciando proceso de exportar");
//hacemos referencia al archivo deseado
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
//Representa nuestro archivo en excel y necesita un OutputStream para saber donde va locoar los datos
            WritableWorkbook w = Workbook.createWorkbook(out);
            System.out.print("colocando nombre");
//Coloca el nombre del "tab"(el nombre del arcchivo en el archivo y tambien en la hoja de excel)
            WritableSheet s = w.createSheet(nombreTab, 0);
//ocupamos dos ciclos para recorrer nuestra tabla y escribir en las celdas de excel
            System.out.print("recorriendo la tabla");
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object objeto = table.getValueAt(i, j);
                    s.addCell(new Label(j, i, String.valueOf(objeto)));
                }
            }
//escribimos en el archivo
            w.write();
            System.out.print("Cerramos el WritableWorkbook y DataOutputStream");
            w.close();
            out.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WriteException ex) {
            ex.printStackTrace();
        }
        System.out.print("ocurrio un error");
        return false;
    }
}

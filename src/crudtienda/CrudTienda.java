
package crudtienda;

import Controlador.CtrlProducto;
import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto;
import javax.swing.table.DefaultTableModel;


public class CrudTienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Producto mod = new Producto();
        ConsultasProducto modC = new ConsultasProducto();
        frmProducto frm = new frmProducto();
        
        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
        
        DefaultTableModel mostrarDatos = modC.mostrarDatos();
        frm.tablaProductos.setModel(mostrarDatos);
        
           
        
        
        //frm.tablaProductos.addMouseListener(new PasarDatosTabla(frm));
        
    }
    
}

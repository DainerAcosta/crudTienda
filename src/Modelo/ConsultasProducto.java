
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasProducto extends Conexion {
    
    public boolean registrar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        if (!buscar(pro)) {
            String sql = "INSERT INTO producto (codigo, nombre, precio, cantidad) VALUES(?, ?, ?, ?)";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }finally{
            
            try {
                
                con.close();
                
            } catch (SQLException e) {
                
                System.err.println(e);
                
            }
        }
        } else {
            
            JOptionPane.showMessageDialog(null, "El registro ya existe");
            
        }
        return false;
        
    }
    
    public boolean modificar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE producto SET codigo=?, nombre=?, precio=?, cantidad=? WHERE id=?";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }finally{
            
            try {
                
                con.close();
                
            } catch (SQLException e) {
                
                System.err.println(e);
                
            }
        }
    }
    
    public boolean eliminar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM producto WHERE id=?";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            
            return false;
            
        }finally{
            
            try {
                
                con.close();
                
            } catch (SQLException e) {
                
                System.err.println(e);
                
            }
        }
    }
    
    public boolean buscar(Producto pro){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM producto WHERE codigo=?";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            
            return false;
            
        }finally{
            
            try {
                
                con.close();
                
            } catch (SQLException e) {
                
                System.err.println(e);
                
            }
        }
    }
    
    /**
     *
     * @return
     */
    public DefaultTableModel mostrarDatos(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String[] titulos = {"ID", "Codigo", "Nombre", "Precio", "Cantidad"};
        String[] registros = new String[5];
        
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        
        String sql = "SELECT * FROM producto";
        
        try {
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                registros[0]=rs.getString("id");
                registros[1]=rs.getString("codigo");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("precio");
                registros[4]=rs.getString("cantidad");
                                
                modelo.addRow(registros);
            }
            
            return modelo;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al mostrar datos "+ e.getMessage());
        }
        return (null);
    }
    
    //Metodo para filtrar datos de la tabla tablaProductos atraves del textfield txtFiltrar
    /*
    public DefaultTableModel filtrarDatos(String valor){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String[] titulos = {"ID", "Codigo", "Nombre", "Precio", "Cantidad"};
        String[] registros = new String[5];
        
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        
        String sql = "select * from producto where nombre like '%"+valor+"%'";
        
        try {
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                registros[0]=rs.getString("id");
                registros[1]=rs.getString("codigo");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("precio");
                registros[4]=rs.getString("cantidad");
                                
                modelo.addRow(registros);
            }
            
            return modelo;
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al filtrar datos "+ e.getMessage());
        }
        
        return (null);
    }
    */
    
}

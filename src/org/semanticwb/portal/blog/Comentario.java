package org.semanticwb.portal.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticwb.SWBUtils;

/**
 *
 * @author UTIC
 */
public class Comentario{
    String nombre;
    String comentario;

    public Comentario(String nombre, String comentario) {
        this.nombre = nombre;
        this.comentario = comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    static Iterator<Comentario> listarComentarios(){
        ArrayList <Comentario> comentarios = new ArrayList<>();
        try {
            Connection con = SWBUtils.DB.getDefaultConnection();
            PreparedStatement ps = con.prepareStatement("select * from comments");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                comentarios.add(new Comentario(rs.getString("name"), rs.getString("comment")));
            }
            ps.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comentario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comentarios.iterator();
    }
    
    static void agregraComentario(Comentario comentario){
         try( Connection con = SWBUtils.DB.getDefaultConnection();
                PreparedStatement ps = con.prepareStatement("Insert into comments (name,comment) values (?,?)");
            ) {
            ps.setString(1, comentario.getNombre());
            ps.setString(2, comentario.getComentario());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Comentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
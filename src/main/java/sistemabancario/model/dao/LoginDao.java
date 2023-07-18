/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.conection.MySQL;
import sistemabancario.model.domain.Login;
import sistemabancario.model.domain.Usuario;

/**
 *
 * @author Matheus
 */
public class LoginDao {
    
      public void inserir(Login login) {
        String sql = "INSERT INTO CONTROLE_LOGIN(USUARIO_ID) VALUES (?) ";

        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, login.getUsuario().getId());
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public boolean editarDataLogOff(Login login) {
          
              
        String sql = "UPDATE CONTROLE_LOGIN SET DATA_LOGOFF = ? WHERE ID = ?";
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
             stmt.setInt(1, login.getId());
           var result = stmt.executeUpdate();
            return result > 0;
                             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Login gerarLoginResultSet(ResultSet rs) throws SQLException {
        
        var usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("usuario_nome"));
        usuario.setEmail(rs.getString("usuario_email"));
        //usuario.setSenha(rs.getString("senha"));
       // usuario.setDataCadastro(rs.getDate("data_cadastro"));
        
        var login = new Login();
        //cria uma novo login
        login.setId(rs.getInt("id"));
        login.setUsuario(usuario);
        login.setDataLogin(rs.getDate("data_login"));
        login.setDataLogoff(rs.getDate("data_logoff"));
        return login;
        
        
    }
    
    
    public List<Login> buscarTodosLogins(){
        String sql = """
                    SELECT controle_login.id, controle_login.usuario_id,
                             		controle_login.data_login, controle_login.data_logoff,
                                     usuarios.nome as usuario_nome, usuarios.email as usuario_email
                                     from controle_login
                                     inner join usuarios on usuarios.id = controle_login.usuario_id
                             order by id;
                     """;
        
        List<Login> logins = new ArrayList<>();
        try(Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                logins.add(gerarLoginResultSet(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logins;
    }
    
    public List<Login> buscaUltimoLogin(){
         String sql = """
                   SELECT controle_login.id, controle_login.usuario_id,
                                                 		controle_login.data_login, controle_login.data_logoff,
                                                         usuarios.nome as usuario_nome, usuarios.email as usuario_email
                                                         from controle_login
                                                         inner join usuarios on usuarios.id = controle_login.usuario_id
                                                 order by id desc limit 1;
                     """;
        
        List<Login> logins = new ArrayList<>();
        try(Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                logins.add(gerarLoginResultSet(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logins;
    }
    }


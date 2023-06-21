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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.conection.MySQL;
import sistemabancario.model.domain.Usuario;
import sistemabancario.utils.Criptografar;

/**
 *
 * @author Matheus
 */
public class UsuarioDao {

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS(NOME,EMAIL,SENHA) VALUES (?, ?, ?) ";

        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Optional<Usuario> buscaUsuarioPorEmail(String email) {
        String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ?";
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return Optional.of(gerarUsuarioResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public int deletar(Integer id) {
        String sql = """
                     DELETE FROM USUARIOS WHERE ID = ?
                     """;
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public boolean editar(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET NOME = ?, EMAIL = ?, SENHA = ? WHERE ID = ?";
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Usuario gerarUsuarioResultSet(ResultSet rs) throws SQLException {
        var usuario = new Usuario();
        //cria uma nova  cliente
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setDataCadastro(rs.getDate("data_cadastro"));

        return usuario;
    }
    
    
    public List<Usuario> buscarTodosUsuarios(){
        String sql = """
                     SELECT * FROM USUARIOS;
                     """;
        
        List<Usuario> usuarios = new ArrayList<>();
        try(Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuarios.add(gerarUsuarioResultSet(rs));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    
    public static void main(String[] args) {
        
        Usuario usuario = new Usuario(null, "Matheus", "matheus@gmail.com",
                Criptografar.encryp("123456"), null);
        
                new UsuarioDao().inserir(usuario);
        
    }
    
/*
    public Usuario buscarPorId(Integer id) {
        String sql = " SELECT * FROM USUARIOS WHERE ID = ?";
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return gerarUsuarioResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
*/
}

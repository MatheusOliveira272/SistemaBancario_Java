
package sistemabancario.model.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.conection.MySQL;
import sistemabancario.model.domain.Agencia;

/**
 *
 * @author alunos
 */
public class AgenciaDao {
    
    public void inserir(Agencia agencia){
        String sql = "INSERT INTO AGENCIAS(NOME, ENDERECO, TELEFONE) VALUES (?, ?, ?) ";
        
        try(Connection con = MySQL.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.setString(3, agencia.getTelefone());
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Agencia> buscaTodasAgencias(){
        String sql = """
                     SELECT * FROM AGENCIAS ORDER BY NOME
                     """;
        List<Agencia> agencias = new ArrayList<>();
         try(Connection con = MySQL.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
             ResultSet rs = stmt.executeQuery();
             //executa a instrucao
             while(rs.next()){
                 //coloca a agencia em uma lista
                 agencias.add(gerarAgenciaResultSet(rs));
             }
             
         }catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return agencias;
    }
    
    public int deletar(Integer id){
        String sql = """
                     DELETE FROM AGENCIAS WHERE ID = ?
                     """;
        try(Connection con = MySQL.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        
        }catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
        
    } 
    
    public boolean editar(Agencia agencia){
        String sql = "UPDATE AGENCIAS SET NOME = ?, ENDERECO = ?, TELEFONE = ? WHERE ID = ?";
        try (Connection con = MySQL. getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.setString(3, agencia.getTelefone());
            stmt.setInt(4, agencia.getId());
            
            var result = stmt.executeUpdate();
            return result > 0;
        }catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;     
    }
    
    private Agencia gerarAgenciaResultSet(ResultSet rs) throws SQLException{
        var agencia = new Agencia();
        //cria uma nova agencia
                 agencia.setId(rs.getInt("id"));
                 agencia.setNome(rs.getString("nome"));
                 agencia.setEndereco(rs.getString("endereco"));
                 agencia.setTelefone(rs.getString("telefone"));
                 return agencia;
    }
    
    public Agencia buscarPorId( Integer id){
        String sql =" SELECT * FROM AGENCIAS WHERE ID = ?";
        try (Connection con = MySQL. getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return gerarAgenciaResultSet(rs);
            }
        }catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public static void main(String[] args) {
        var agenciaDao = new AgenciaDao();
        //editar/alterar agencia
        /*
        Agencia agencia = agenciaDao.buscarPorId(13);
        agencia.setNome("Unincor");
        agencia.setEndereco("Rua xxx 155");
        agencia.setTelefone("32325586");
        agenciaDao.editar(agencia);
               
          */  
        //
        //
        //
        
        //insert
        //Agencia ag1 = new Agencia(null, "BB", "Três Corações","12312345");
        //agenciaDao.inserir(ag1);
        //list
       // agenciaDao.buscaTodasAgencias().forEach(System.out::println);
       //deletar
        //agenciaDao.deletar(53);
        
        
    }
}

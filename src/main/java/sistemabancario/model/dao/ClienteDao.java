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
import sistemabancario.model.domain.Cliente;

/**
 *
 * @author Matheus
 */
public class ClienteDao {

    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES(NOME, CPF, EMAIL, TELEFONE, ENDERECO) VALUES (?, ?, ?, ?, ?) ";

        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> buscaTodosClientes() {
        String sql = """
                     SELECT * FROM CLIENTES ORDER BY NOME
                     """;
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(gerarClienteResultSet(rs));
               // var cliente = new Cliente();
                /*cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));

                clientes.add(cliente);*/
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public int deletar(Integer id) {
        String sql = """
                     DELETE FROM CLIENTES WHERE ID = ?
                     """;
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public boolean editar(Cliente cliente) {
        String sql = "UPDATE CLIENTES SET NOME = ?, CPF = ?, EMAIL = ?, TELEFONE = ?, ENDERECO = ? WHERE ID = ?";
        try (Connection con = MySQL.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId());

            var result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Cliente gerarClienteResultSet(ResultSet rs) throws SQLException {
        var cliente = new Cliente();
        //cria uma nova  cliente
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEndereco(rs.getString("endereco"));
        
        return cliente;
    }

    public Cliente buscarPorId(Integer id) {
        String sql = " SELECT * FROM CLIENTES WHERE ID = ?";
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return gerarClienteResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        var clienteDao = new ClienteDao();

        //insert
        // Cliente cl = new Cliente(null,"aaa","1111","bbbbb","33333","ggggf");
        // clienteDao.inserir(cl);
        //list
        clienteDao.buscaTodosClientes().forEach(System.out::println);
        //deletar
        //clienteDao.deletar(53);
     //  Cliente cliente = clienteDao.buscarPorId(13);
       /* agencia.setNome("Unincor");
        agencia.setEndereco("Rua xxx 155");
        agencia.setTelefone("32325586");
        agenciaDao.editar(agencia);*/
      // agenciaDao.buscaTodasAgencias().forEach(System.out::println);
    }

}

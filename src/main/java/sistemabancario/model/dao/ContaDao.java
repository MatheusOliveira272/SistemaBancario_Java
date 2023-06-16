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
import sistemabancario.model.domain.Agencia;
import sistemabancario.model.domain.Cliente;
import sistemabancario.model.domain.Conta;

/**
 *
 * @author alunos
 */
public class ContaDao {

    public void inserir(Conta conta) {
        String sql = "INSERT INTO CONTAS(CLIENTE_ID, AGENCIA_ID, SALDO) VALUES (?, ?, ?) ";

        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
          
            stmt.setInt(1, conta.getCliente().getId());
            stmt.setInt(2, conta.getAgencia().getId());
            stmt.setDouble(3, conta.getSaldo());

            stmt.execute();

        } catch (SQLException ex) {
            // 23/04
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Conta> buscaTodasContas() {
        String sql = """
                     select contas.id, contas.cliente_id, contas.saldo,
                     	   contas.agencia_id, clientes.nome as nome_cliente,
                            clientes.cpf, clientes.email, 
                            clientes.telefone as telefone_cliente,
                            clientes.endereco as endereco_cliente, 
                            agencias.nome as nome_agencia, 
                            agencias.endereco as endereco_agencia,
                            agencias.telefone as telefone_agencia
                     from contas 
                     inner join clientes on clientes.id = contas.cliente_id
                     inner join agencias on agencias.id = contas.agencia_id
                     order by clientes.nome;
                     """;
        List<Conta> contas = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contas.add(construirContaResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contas;
    }

    //construimos isso com a ajuda das instruções sql 
    private Conta construirContaResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("cliente_id"));
        cliente.setNome(rs.getString("nome_cliente"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEmail(rs.getString("email"));
        cliente.setEndereco(rs.getString("endereco_cliente"));
        cliente.setTelefone(rs.getString("telefone_cliente"));

        Agencia agencia = new Agencia();
        agencia.setId(rs.getInt("agencia_id"));
        agencia.setNome(rs.getString("nome_agencia"));
        agencia.setEndereco(rs.getString("endereco_agencia"));
        agencia.setTelefone(rs.getString("telefone_agencia"));

        Conta conta = new Conta();
        conta.setId(rs.getInt("id"));
        conta.setCliente(cliente);
        conta.setAgencia(agencia);
        conta.setSaldo(rs.getDouble("saldo"));

        return conta;

    }

    public int deletar(Integer id) {
        String sql = """
                     DELETE FROM CONTAS WHERE ID = ?
                     """;
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public static void main(String[] args) {
        var contaDao = new ContaDao();
        
        List<Conta> contas = contaDao.buscaTodasContas();

        //insert
        // Conta cl = new Conta(null,1,2,300.00);
        //   contaDao.inserir(cl);
        //list
        contas.forEach(System.out::println);
        //deletar
        //contaDao.deletar(40);
    }

}

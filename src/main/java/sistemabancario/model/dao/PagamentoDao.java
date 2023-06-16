/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.model.dao;

import java.sql.Connection;
import java.sql.Date;
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
import sistemabancario.model.domain.Pagamento;
import sistemabancario.view.TelaCadastroPagamento;

/**
 *
 * @author alunos
 */
public class PagamentoDao {

    public void inserir(Pagamento pagamento) {
        String sql = "INSERT INTO PAGAMENTOS(CONTA_ID, VALOR, DATA_PAGAMENTO) VALUES (?, ?, ?) ";

        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getConta().getId());
            stmt.setDouble(2, pagamento.getValorPago());
            stmt.setDate(3, (Date) pagamento.getDataPagamento());
            // stmt.setDate(3, Date.valueOf(LocalDateTime.now()));

            stmt.execute();

        } catch (SQLException ex) {
            // 23/04
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pagamento> buscaTodosPagamentosGeral() {
              
        String sql = """
                     select clientes.nome as nome_cliente, contas.id as id_conta, agencias.nome as nome_agencia, pagamentos.valor, pagamentos.data_pagamento, pagamentos.id,
                      contas.cliente_id, contas.saldo as conta_saldo,
                      contas.agencia_id,
                      clientes.nome as nome_cliente,
                                                                      clientes.cpf, clientes.email,
                                                                      clientes.telefone as telefone_cliente,
                                                                      clientes.endereco as endereco_cliente,
                                                                     
                                                                      agencias.nome as nome_agencia,
                                                                      agencias.endereco as endereco_agencia,
                                                                      agencias.telefone as telefone_agencia
                                                               from pagamentos
                                                               inner join contas on contas.id = pagamentos.conta_id
                                                               inner join clientes on clientes.id = contas.cliente_id
                                                               inner join agencias on agencias.id = contas.agencia_id
                     order by clientes.nome 
                                                              ;""";
       
       
       
        List<Pagamento> pagamento = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pagamento.add(construirPagamentoResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagamento;
    }
      
    
    public List<Pagamento> buscaTodosPagamentos(TelaCadastroPagamento t) {
        String sql = """
                     select clientes.nome as nome_cliente, contas.id as id_conta, agencias.nome as nome_agencia, pagamentos.valor,
                     pagamentos.data_pagamento, pagamentos.id,
                      contas.cliente_id, contas.saldo as conta_saldo,
                      contas.agencia_id,
                      clientes.nome as nome_cliente,
                                                                      clientes.cpf, clientes.email,
                                                                      clientes.telefone as telefone_cliente,
                                                                      clientes.endereco as endereco_cliente,
                                                                     
                                                                      agencias.nome as nome_agencia,
                                                                      agencias.endereco as endereco_agencia,
                                                                      agencias.telefone as telefone_agencia
                                                               from pagamentos
                                                               inner join contas on contas.id = pagamentos.conta_id
                                                               inner join clientes on clientes.id = contas.cliente_id
                                                               inner join agencias on agencias.id = contas.agencia_id
                                                               where clientes.cpf = (?)
                                                               order by clientes.nome ;""";
        List<Pagamento> pagamento = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, t.getTxtPesquisaClientePagamento().getText());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pagamento.add(construirPagamentoResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagamento;
    }
    
    

    //construimos isso com a ajuda das instruções sql 
    private Pagamento construirPagamentoResultSet(ResultSet rs) throws SQLException {

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
        conta.setSaldo(rs.getDouble("conta_saldo"));

        Pagamento pagamento = new Pagamento();
        pagamento.setId(rs.getInt("id"));
        pagamento.setValorPago(rs.getDouble("valor"));
        pagamento.setDataPagamento(rs.getDate("data_pagamento"));
       // pagamento.setCliente(cliente);
        pagamento.setConta(conta);
       // pagamento.setAgencia(agencia);

        return pagamento;

    }

    public int deletar(Integer id) {
        String sql = """
                     DELETE FROM PAGAMENTOS WHERE ID = ?
                     """;
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PagamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

}

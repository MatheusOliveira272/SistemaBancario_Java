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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemabancario.conection.MySQL;
import sistemabancario.model.domain.Agencia;
import sistemabancario.model.domain.Cliente;
import sistemabancario.model.domain.Conta;
import sistemabancario.model.domain.Transferencia;
import sistemabancario.view.TelaCadastroTransferencia;

/**
 *
 * @author Luiz Fernando
 */
public class TransferenciaDao {
   
    public void inserir(Transferencia transferencia) {
        String sql = "insert into transferencias(conta_origem_id, conta_destino_id, valor, data_transferencia) values (?,?,?,?);";
       
        try(Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
         stmt.setInt(1, transferencia.getContaOrigem().getId());
         stmt.setInt(2, transferencia.getContaDestino().getId());
         stmt.setDouble(3, transferencia.getValorTransferido());
          stmt.setTimestamp(4, new Timestamp(transferencia.getDataPagamento().getTime()));
        // stmt.setDate(4, Date.valueOf(LocalDate.now()));
         
         stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public List<Transferencia> buscaTodasTransferenciasGeral(){
        String sql = """
                     select transferencias.id, transferencias.conta_destino_id, transferencias.conta_origem_id, transferencias.valor,
                     transferencias.data_transferencia,
                     c0.id as cliente_ori_id, c0.nome as nome_cliente_ori, c0.cpf as cpf_ori, c0.email as email_ori,
                     c0.endereco as endereco_ori, c0.telefone as telefone_ori,
                     c1.id as cliente_dest_id, c1.nome as nome_cliente_dest, c1.cpf as cpf_dest, c1.email as email_dest,
                     c1.endereco as endereco_dest, c1.telefone as telefone_dest,
                     a0.id as agencia_ori_id, a0.nome as nome_agencia_ori, a0.endereco as agencia_ori_end, a0.telefone as agencia_tel_ori,
                     a1.id as agencia_dest_id, a1.nome as nome_agencia_dest, a1.endereco as agencia_dest_end, a1.telefone as agencia_tel_dest,
                     ct0.id as conta_ori_id, ct0.saldo as conta_saldo_ori,
                     ct1.id as conta_dest_id, ct1.saldo as conta_saldo_dest
                     from transferencias
                     inner join contas ct0 on ct0.id = transferencias.conta_origem_id
                     inner join contas ct1 on ct1.id = transferencias.conta_destino_id
                     inner join agencias a0 on a0.id = ct0.agencia_id
                     inner join agencias a1 on a1.id = ct1.agencia_id
                     inner join clientes c0 on c0.id = ct0.cliente_id
                     inner join clientes c1 on c1.id = ct1.cliente_id
                     order by id;
                     
                     """;
        List<Transferencia> transferencia = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                transferencia.add(construirTransferenciasResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transferencia;
    }
   
    public List<Transferencia> buscaTodasTransferenciasCpf(TelaCadastroTransferencia t){
        String sql = """
                     select transferencias.id, transferencias.conta_destino_id, transferencias.conta_origem_id, transferencias.valor,
                     transferencias.data_transferencia,
                     c0.id as cliente_ori_id, c0.nome as nome_cliente_ori, c0.cpf as cpf_ori, c0.email as email_ori,
                     c0.endereco as endereco_ori, c0.telefone as telefone_ori,
                     c1.id as cliente_dest_id, c1.nome as nome_cliente_dest, c1.cpf as cpf_dest, c1.email as email_dest,
                     c1.endereco as endereco_dest, c1.telefone as telefone_dest,
                     a0.id as agencia_ori_id, a0.nome as nome_agencia_ori, a0.endereco as agencia_ori_end, a0.telefone as agencia_tel_ori,
                     a1.id as agencia_dest_id, a1.nome as nome_agencia_dest, a1.endereco as agencia_dest_end, a1.telefone as agencia_tel_dest,
                     ct0.id as conta_ori_id, ct0.saldo as conta_saldo_ori,
                     ct1.id as conta_dest_id, ct1.saldo as conta_saldo_dest
                     from transferencias
                     inner join contas ct0 on ct0.id = transferencias.conta_origem_id
                     inner join contas ct1 on ct1.id = transferencias.conta_destino_id
                     inner join agencias a0 on a0.id = ct0.agencia_id
                     inner join agencias a1 on a1.id = ct1.agencia_id
                     inner join clientes c0 on c0.id = ct0.cliente_id
                     inner join clientes c1 on c1.id = ct1.cliente_id
                     where c0.cpf = (?)
                     order by id;
                     
                     """;
       List<Transferencia> transferencia = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, t.getTxtPesquisaCpf().getText());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transferencia.add(construirTransferenciasResultSet(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transferencia;
    }
   
    
    
    
    
    public Transferencia construirTransferenciasResultSet(ResultSet rs) throws SQLException{
        Cliente c0 = new Cliente();
        c0.setId(rs.getInt("cliente_ori_id"));
        c0.setNome(rs.getString("nome_cliente_ori"));
        c0.setCpf(rs.getString("cpf_ori"));
        c0.setEmail(rs.getString("email_ori"));
        c0.setEndereco(rs.getString("endereco_ori"));
        c0.setTelefone(rs.getString("telefone_ori"));
       
        Cliente c1 = new Cliente();
        c1.setId(rs.getInt("cliente_dest_id"));
        c1.setNome(rs.getString("nome_cliente_dest"));
        c1.setCpf(rs.getString("cpf_dest"));
        c1.setEmail(rs.getString("email_dest"));
        c1.setEndereco(rs.getString("endereco_dest"));
        c1.setTelefone(rs.getString("telefone_dest"));

        Agencia a0 = new Agencia();
        a0.setId(rs.getInt("agencia_ori_id"));
        a0.setNome(rs.getString("nome_agencia_ori"));
        a0.setEndereco(rs.getString("agencia_ori_end"));
        a0.setTelefone(rs.getString("agencia_tel_ori"));
       
        Agencia a1 = new Agencia();
        a1.setId(rs.getInt("agencia_dest_id"));
        a1.setNome(rs.getString("nome_agencia_dest"));
        a1.setEndereco(rs.getString("agencia_dest_end"));
        a1.setTelefone(rs.getString("agencia_tel_dest"));

        Conta ct0 = new Conta();
        ct0.setId(rs.getInt("conta_ori_id"));
        ct0.setSaldo(rs.getDouble("conta_saldo_ori"));
        ct0.setAgencia(a0);
        ct0.setCliente(c0);
       
        Conta ct1 = new Conta();
        ct1.setId(rs.getInt("conta_dest_id"));
        ct1.setSaldo(rs.getDouble("conta_saldo_dest"));
        ct1.setAgencia(a1);
        ct1.setCliente(c1);
       
       
       
        Transferencia transferencia = new Transferencia();
        transferencia.setId(rs.getInt("id"));
        transferencia.setContaDestino(ct1);
        transferencia.setContaOrigem(ct0);
        transferencia.setDataPagamento(rs.getDate("data_transferencia"));
        transferencia.setValorTransferido(rs.getDouble("valor"));
       
        
        return transferencia;
        
        
        
    }
    
     public int deletar(Integer id) {
        String sql = """
                     DELETE FROM TRANSFERENCIAS WHERE ID = ?
                     """;
        try (Connection con = MySQL.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TransferenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
     
     public static void main(String[] args) {
        new TransferenciaDao().buscaTodasTransferenciasGeral().forEach(System.out::println);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.model.domain;

import java.util.Date;

/**
 *
 * @author alunos
 */
public class Pagamento {
    
    private Integer id;
    private Conta conta;
   // private Agencia agencia;
   // private Cliente cliente;
    private Double valorPago;
    private Date dataPagamento;

    public Pagamento() {
    }

    public Pagamento(Integer id, Conta conta, Double valorPago, Date dataPagamento) {
        this.id = id;
        this.conta = conta;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
    }

    
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

   

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "id=" + id + ", conta=" + conta + ", valorPago=" + valorPago + ", dataPagamento=" + dataPagamento + '}';
    }

    
    
    
    
}

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
public class Transferencia {
    private Integer id;
    private Conta contaOrigem;
    private Conta contaDestino;
    private Double valorTransferido;
    private Date dataPagamento;

    public Transferencia() {
    }

    public Transferencia(Integer id, Conta contaOrigem, Conta contaDestino, Double valorTransferido, Date dataPagamento) {
        this.id = id;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorTransferido = valorTransferido;
        this.dataPagamento = dataPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValorTransferido() {
        return valorTransferido;
    }

    public void setValorTransferido(Double valorTransferido) {
        this.valorTransferido = valorTransferido;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    // toString para ver as informações
    @Override
    public String toString() {
        return "Transferencia{" + "id=" + id + ", contaOrigem=" + contaOrigem + ", contaDestino=" + contaDestino + ", valorTransferido=" + valorTransferido + ", dataPagamento=" + dataPagamento + '}';
    }
    
    
   

    
    
}

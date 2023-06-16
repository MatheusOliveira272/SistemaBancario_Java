/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.util.List;
import javax.swing.JComboBox;
import sistemabancario.exception.TransferenciaException;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.model.dao.TransferenciaDao;
import sistemabancario.model.domain.Conta;
import sistemabancario.model.domain.Transferencia;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroTransferencia;
import sistemabancario.view.TelaNovaTransferencia;
import sistemabancario.view.tables.TabelaTransferencia;

/**
 *
 * @author alunos
 */
public class ControllerTransferencia {
    
    
    public void salvar(TelaNovaTransferencia c) throws TransferenciaException {

        if (c.getComboBoxContaOrigem().getSelectedIndex() == 0) {
            throw new TransferenciaException("A Conta Origem Não Foi selecionada!");
        }
        
           if (c.getComboBoxContaDestino().getSelectedIndex() == 0) {
            throw new TransferenciaException("A Conta Destino Não Foi selecionada!");
        }

        if (c.getTxtValorTransferido().getText().isBlank()) {
            throw new TransferenciaException("O Valor não foi Informado!");

        }

        //crio objeto
        var transferencia = new Transferencia(null, (Conta)c.getComboBoxContaOrigem().getSelectedItem(), (Conta)c.getComboBoxContaDestino().getSelectedItem(),
                Double.valueOf(c.getTxtValorTransferido().getText()), new java.sql.Date(new java.util.Date().getTime()));

        //valido objeto
        var transferenciaDao = new TransferenciaDao();
        //validando o saldo 
        if (Double.valueOf(c.getTxtValorTransferido().getText()) <= transferencia.getContaOrigem().getSaldo()) {
            //Realizando o pagamento 
            if (GerarMensagens.confirmar(c, "Deseja efetuar a transferencia de R$ " + Double.valueOf(c.getTxtValorTransferido().getText())+ " ? ")) {
                transferenciaDao.inserir(transferencia);
                GerarMensagens.alerta(c, "Salvo com Sucesso!");
                pesquisarTodos((TelaCadastroTransferencia) c.getParent());
                c.dispose();
            }

        } else {
            GerarMensagens.erro(c, "Saldo Insuficiente!");
        }

    }

    public void pesquisarCpf(TelaCadastroTransferencia t) {
        TabelaTransferencia tabelaTransferencia = (TabelaTransferencia) t.getTableTransferencia().getModel();

        var transferenciaDao  = new TransferenciaDao();
        tabelaTransferencia.setRegistros(transferenciaDao.buscaTodasTransferenciasCpf(t));
    }
    
    
    public void pesquisarTodos(TelaCadastroTransferencia t) {
      TabelaTransferencia tabelaTransferencia= (TabelaTransferencia) t.getTableTransferencia().getModel();

        var transferenciaDao = new TransferenciaDao();
        tabelaTransferencia.setRegistros(transferenciaDao.buscaTodasTransferenciasGeral());
    }
    

    public void excluirTransferencia(TelaCadastroTransferencia t) throws TransferenciaException {
        if (t.getTableTransferencia().getSelectedRowCount() == 0) {
            throw new TransferenciaException("Nenhum registro selecionado!");
        }
        //pega alinha selecionada na tabela 
        int row = t.getTableTransferencia().getSelectedRow();
        //recupera o objeto da teabela 
        TabelaTransferencia tabelaTransferencia = (TabelaTransferencia) t.getTableTransferencia().getModel();
        //obtem o objeto selecionado da lista
        Transferencia transferencia = tabelaTransferencia.getRegistros().get(row);
        //confirma a intenção de remover o objeto do BD
        if (GerarMensagens.confirmar(t, "Deseja excluír a transferencia feita para a conta " + transferencia.getContaDestino().getCliente().getNome() + " ?")) {
            //Executa a instrução de deletar do BD
            var transferenciaDao = new TransferenciaDao();
            transferenciaDao.deletar(transferencia.getId());
            // ContaDao.de(conta.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisarTodos(t);
    }
  public void carregarTransferenciaCombo(JComboBox<Object> comboBox) {
        List<Conta> contas = new ContaDao().buscaTodasContas();
        comboBox.removeAllItems();
        comboBox.addItem("Selecione");
        contas.forEach(comboBox::addItem);
    }
    
}

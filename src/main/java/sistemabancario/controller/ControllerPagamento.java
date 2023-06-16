/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import sistemabancario.exception.PagamentoException;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.model.dao.PagamentoDao;
import sistemabancario.model.domain.Conta;
import sistemabancario.model.domain.Pagamento;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroPagamento;
import sistemabancario.view.TelaNovoPagamento;
import sistemabancario.view.tables.TabelaPagamento;

/**
 *
 * @author alunos
 */
public class ControllerPagamento {

    public void salvar(TelaNovoPagamento c) throws PagamentoException {

        if (c.getComboContaId().getSelectedIndex() == 0) {
            throw new PagamentoException("A Conta Não Foi selecionada!");
        }

        if (c.getTxtValorPagamento().getText().isBlank()) {
            throw new PagamentoException("O Valor não foi Informado!");

        }

        //crio objeto
        var pagamento = new Pagamento(null, (Conta) c.getComboContaId().getSelectedItem(),
                Double.valueOf(c.getTxtValorPagamento().getText()), new java.sql.Date(new java.util.Date().getTime()));

        //valido objeto
        var pagamentoDao = new PagamentoDao();
        //validando o saldo 
        if (Double.valueOf(c.getTxtValorPagamento().getText()) <= pagamento.getConta().getSaldo()) {
            //Realizando o pagamento 
            if (GerarMensagens.confirmar(c, "Deseja efetuar o pagamento de R$ " + Double.valueOf(c.getTxtValorPagamento().getText()))) {
                pagamentoDao.inserir(pagamento);
                GerarMensagens.alerta(c, "Salvo com Sucesso!");
                pesquisar((TelaCadastroPagamento) c.getParent());
                c.dispose();
            }

        } else {
            GerarMensagens.erro(c, "Saldo Insuficiente!");
        }

    }

    public void pesquisar(TelaCadastroPagamento t) {
        TabelaPagamento tabelaPagamento = (TabelaPagamento) t.getTabelaListaPagamentos().getModel();

        var pagamentoDao = new PagamentoDao();
        tabelaPagamento.setRegistros(pagamentoDao.buscaTodosPagamentos(t));
    }
    
    
    public void pesquisarTodos(TelaCadastroPagamento t) {
        TabelaPagamento tabelaPagamento = (TabelaPagamento) t.getTabelaListaPagamentos().getModel();

        var pagamentoDao = new PagamentoDao();
        tabelaPagamento.setRegistros(pagamentoDao.buscaTodosPagamentosGeral());
    }
    

    public void excluirPagamento(TelaCadastroPagamento t) throws PagamentoException {
        if (t.getTabelaListaPagamentos().getSelectedRowCount() == 0) {
            throw new PagamentoException("Nenhum registro selecionado!");
        }
        //pega alinha selecionada na tabela 
        int row = t.getTabelaListaPagamentos().getSelectedRow();
        //recupera o objeto da teabela 
        TabelaPagamento tabelaPagamento = (TabelaPagamento) t.getTabelaListaPagamentos().getModel();
        //obtem o objeto selecionado da lista
        Pagamento pagamento = tabelaPagamento.getRegistros().get(row);
        //confirma a intenção de remover o objeto do BD
        if (GerarMensagens.confirmar(t, "Deseja excluír com o Id " + pagamento.getId() + " ?")) {
            //Executa a instrução de deletar do BD
            var pagameentoDao = new PagamentoDao();
            pagameentoDao.deletar(pagamento.getId());
            // ContaDao.de(conta.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisar(t);
    }

}

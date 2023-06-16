/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.util.List;
import javax.swing.JComboBox;
import sistemabancario.exception.ContaException;
import sistemabancario.model.dao.AgenciaDao;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.dao.ContaDao;
import sistemabancario.model.domain.Agencia;
import sistemabancario.model.domain.Cliente;
import sistemabancario.model.domain.Conta;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroConta;
import sistemabancario.view.TelaNovaConta;
import sistemabancario.view.tables.TabelaConta;

/**
 *
 * @author alunos
 */
public class ControllerConta {
    
    public void salvar(TelaNovaConta c) throws ContaException {

        if (c.getComboBoxCliente().getSelectedIndex()==0) {
            throw new ContaException("O Cliente Não Foi selecionado");

        }
        if (c.getComboBoxAgencia().getSelectedIndex()==0) {
            throw new ContaException("A Agência Não foi Selecionada");

        }
        //crio objeto
        var conta = new Conta(null, (Cliente) c.getComboBoxCliente().getSelectedItem(),(Agencia)c.getComboBoxAgencia().getSelectedItem(),0.0);
       
        //var conta = new Conta(null, c.getComboBoxCliente().getSelectedIndex(),c.getComboBoxAgencia().getSelectedIndex());

      /*  Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setAgencia(() c.getComboBoxAgencia().getSelectedItem());
        conta.setCliente((cliente_id) c.getComboBoxCliente().getSelectedItem());
        */
        
        
        //valido objeto
        var contaDao = new ContaDao();
        contaDao.inserir(conta);
        
        GerarMensagens.alerta(c, "Salvo com Sucesso!");
        pesquisar((TelaCadastroConta)c.getParent());
         c.dispose();
       
    }    
       public void pesquisar (TelaCadastroConta t){
           TabelaConta tabelaConta = (TabelaConta) t.getTabelaListaConta().getModel();
        
        var contaDao = new ContaDao();
        tabelaConta.setRegistros(contaDao.buscaTodasContas());
    }
       
        public void excluirConta(TelaCadastroConta t) throws ContaException {
        if(t.getTabelaListaConta().getSelectedRowCount() == 0){
            throw new ContaException("Nenhum registro selecionado!");
        }
        //pega alinha selecionada na tabela 
        int row = t.getTabelaListaConta().getSelectedRow();
        //recupera o objeto da teabela 
        TabelaConta tabelaConta = (TabelaConta) t.getTabelaListaConta().getModel();
        //obtem o objeto selecionado da lista
        Conta conta = tabelaConta.getRegistros().get(row);
        //confirma a intenção de remover o objeto do BD
        if(GerarMensagens.confirmar(t, "Deseja excluír "+ conta.getCliente()+ " ?")){
            //Executa a instrução de deletar do BD
            var contaDao = new ContaDao();
            contaDao.deletar(conta.getId());
           // ContaDao.de(conta.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisar(t);
    }
        
         public void carregarContaCombo(JComboBox<Object> comboBox) {
        List<Conta> contas = new ContaDao().buscaTodasContas();
        comboBox.removeAllItems();
        comboBox.addItem("Selecione");
        contas.forEach(comboBox::addItem);
    }
}

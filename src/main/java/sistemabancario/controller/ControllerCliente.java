/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.util.List;
import javax.swing.JComboBox;
import sistemabancario.exception.ClienteException;
import sistemabancario.model.dao.ClienteDao;
import sistemabancario.model.domain.Cliente;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroCliente;
import sistemabancario.view.TelaNovoCliente;
import sistemabancario.view.tables.TabelaCliente;

/**
 *
 * @author Matheus
 */
public class ControllerCliente {
    
    public void salvar(TelaNovoCliente c) throws ClienteException {

        if (c.getTxtField_Nome().getText().isBlank()) {
            throw new ClienteException("O Nome não foi Informado!!");

        }
        if (c.getTxtField_Cpf().getText().isBlank()) {
            throw new ClienteException("O Cpf não foi Informado!!");

        }
        if (c.getTxtField_Email().getText().isBlank()) {
            throw new ClienteException("O Email não foi Informado!!");

        }
          if (c.getTxtField_Telefone().getText().isBlank()) {
            throw new ClienteException("O telefone não foi Informado!!");
        }

        if (c.getTxtField_Endereco().getText().isBlank()) {
            throw new ClienteException("O endereço não foi Informado!!");
        }

      
        Integer id = c.getClienteEdit()== null ? null : c.getClienteEdit().getId();

        //crio objeto
        var cliente = new Cliente(id, c.getTxtField_Nome().getText(),c.getTxtField_Cpf().getText(),c.getTxtField_Email().getText(), c.getTxtField_Telefone().getText(), c.getTxtField_Endereco().getText());

        //valido objeto
        var clienteDao = new ClienteDao();
        if (c.getClienteEdit() == null) {
            clienteDao.inserir(cliente);
        } else {
            clienteDao.editar(cliente);
        }

        GerarMensagens.alerta(c, "Salvo com Sucesso!");
        pesquisar((TelaCadastroCliente) c.getParent());
        c.dispose();
    }
    
    public void pesquisar (TelaCadastroCliente t){
        TabelaCliente tabelaCliente = (TabelaCliente) t.getTableListaCliente().getModel();
        
        var clienteDao = new ClienteDao();
        tabelaCliente.setRegistros(clienteDao.buscaTodosClientes());
    }
    
      public void abrirTelaEdicao(TelaCadastroCliente t) throws ClienteException {
        if (t.getTableListaCliente().getSelectedRowCount() == 0) {
            throw new ClienteException("Nenhum Registro foi selecionado!");
        }

        TabelaCliente tabelaCliente = (TabelaCliente) t.getTableListaCliente().getModel();
        int row = t.getTableListaCliente().getSelectedRow();
        var agencia = tabelaCliente.getRegistros().get(row);
        new TelaNovoCliente(t, true, agencia).setVisible(true);

    }

    public void carregarClientesCombo(JComboBox<Object> comboBox) {
        List<Cliente> clientes = new ClienteDao().buscaTodosClientes();
        comboBox.removeAllItems();
        comboBox.addItem("Selecione");     
        clientes.forEach(comboBox::addItem);
    }
    
     public void excluirCliente(TelaCadastroCliente t) throws ClienteException {
        if(t.getTableListaCliente().getSelectedRowCount() == 0){
            throw new ClienteException("Nenhum registro selecionado!");
        }
        //pega alinha selecionada na tabela 
        int row = t.getTableListaCliente().getSelectedRow();
        //recupera o objeto da teabela 
        TabelaCliente tabelacliente = (TabelaCliente) t.getTableListaCliente().getModel();
        //obtem o objeto selecionado da lista
        Cliente cliente = tabelacliente.getRegistros().get(row);
        //confirma a intenção de remover o objeto do BD
        if(GerarMensagens.confirmar(t, "Deseja excluír "+ cliente.getNome() + " ?")){
            //Executa a instrução de deletar do BD
            var agenciaDao = new ClienteDao();
            agenciaDao.deletar(cliente.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisar(t);
    }
    
    
    
}

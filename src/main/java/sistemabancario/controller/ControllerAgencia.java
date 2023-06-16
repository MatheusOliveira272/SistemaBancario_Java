/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.util.List;
import javax.swing.JComboBox;
import sistemabancario.exception.AgenciaException;
import sistemabancario.model.dao.AgenciaDao;
import sistemabancario.model.domain.Agencia;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroAgencia;
import sistemabancario.view.TelaNovaAgencia;
import sistemabancario.view.tables.TabelaAgencia;

/**
 *
 * @author alunos
 */
public class ControllerAgencia {

    public void salvar(TelaNovaAgencia t) throws AgenciaException {

        if (t.getTxtField_Nome().getText().isBlank()) {
            throw new AgenciaException("O Nome não foi Informado!!");

        }

        if (t.getTxtField_Endereco().getText().isBlank()) {
            throw new AgenciaException("O endereço não foi Informado!!");
        }

        if (t.getTxtField_Telefone().getText().isBlank()) {
            throw new AgenciaException("O telefone não foi Informado!!");
        }

        Integer id = t.getAgenciaEdit() == null ? null : t.getAgenciaEdit().getId();
        //crio objeto
        var agencia = new Agencia(id, t.getTxtField_Nome().getText(), t.getTxtField_Endereco().getText(), t.getTxtField_Telefone().getText());

        //valido objeto
        var agenciaDao = new AgenciaDao();
        if (t.getAgenciaEdit() == null) {
            agenciaDao.inserir(agencia);
        } else {
            agenciaDao.editar(agencia);
        }

        GerarMensagens.alerta(t, "Salvo com Sucesso!");
        pesquisar((TelaCadastroAgencia) t.getParent());
        t.dispose();

    }

    public void pesquisar(TelaCadastroAgencia t) {
        TabelaAgencia tabelaAgencia = (TabelaAgencia) t.getTableListaAgencia().getModel();

        var agenciaDao = new AgenciaDao();
        tabelaAgencia.setRegistros(agenciaDao.buscaTodasAgencias());
    }

    public void abrirTelaEdicao(TelaCadastroAgencia t) throws AgenciaException {
        if (t.getTableListaAgencia().getSelectedRowCount() == 0) {
            throw new AgenciaException("Nenhum Registro foi selecionado!");
        }

        TabelaAgencia tabelaAgencia = (TabelaAgencia) t.getTableListaAgencia().getModel();
        int row = t.getTableListaAgencia().getSelectedRow();
        var agencia = tabelaAgencia.getRegistros().get(row);
        new TelaNovaAgencia(t, true, agencia).setVisible(true);

    }

    public void carregarAgenciasCombo(JComboBox<Object> comboBox) {
        List<Agencia> agencias = new AgenciaDao().buscaTodasAgencias();
        comboBox.removeAllItems();
        comboBox.addItem("Selecione");
        agencias.forEach(comboBox::addItem);
    }
    
    public void excluirAgencia(TelaCadastroAgencia t) throws AgenciaException {
        if(t.getTableListaAgencia().getSelectedRowCount() == 0){
            throw new AgenciaException("Nenhum registro selecionado!");
        }
        //pega alinha selecionada na tabela 
        int row = t.getTableListaAgencia().getSelectedRow();
        //recupera o objeto da teabela 
        TabelaAgencia tabelaAgencia = (TabelaAgencia) t.getTableListaAgencia().getModel();
        //obtem o objeto selecionado da lista
        Agencia agencia = tabelaAgencia.getRegistros().get(row);
        //confirma a intenção de remover o objeto do BD
        if(GerarMensagens.confirmar(t, "Deseja excluír "+ agencia.getNome() + " ?")){
            //Executa a instrução de deletar do BD
            var agenciaDao = new AgenciaDao();
            agenciaDao.deletar(agencia.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisar(t);
    }
}

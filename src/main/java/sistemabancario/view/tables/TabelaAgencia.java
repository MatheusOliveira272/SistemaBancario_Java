/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Agencia;

/**
 *
 * @author alunos
 */
//tabela de agencia herdou a tabela generica
public class TabelaAgencia extends AbstractTable<Agencia> {

    public TabelaAgencia() {
        //o que o usuário verá
        this("Nome", "Endereço", "Telefone");
    }
    
    //os ... é para não explicar o que vai ter em cada coluna
    public TabelaAgencia(String... colunas) {
        super(colunas);
    }
    //matriz
    //objeto são as linhas 
    //parametros/atributos colunas 

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agencia agencia = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            //case 0 -> agencia.getId();
            case 0-> agencia.getNome();
            case 1 -> agencia.getEndereco();
            case 2 -> agencia.getTelefone();
            default -> null;
        };
    }

}

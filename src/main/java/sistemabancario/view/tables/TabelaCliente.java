/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Cliente;

/**
 *
 * @author alunos
 */
public class TabelaCliente extends AbstractTable<Cliente>{
    
     public TabelaCliente() {
        //o que o usuário verá
        this( "Nome", "CPF", "Email","Telefone", "Endereço");
    }

    public TabelaCliente(String... colunas) {
        super(colunas);
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = getRegistros().get(rowIndex);
        return switch (columnIndex) {
          //  case 0 -> cliente.getId();
            case 0 -> cliente.getNome();
            case 1 -> cliente.getCpf();
            case 2 -> cliente.getEmail();
            case 3 -> cliente.getTelefone();
           case 4 -> cliente.getEndereco();
            default -> null;
        };
    }
    
    
    
    
}

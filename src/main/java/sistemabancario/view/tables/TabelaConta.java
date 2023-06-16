/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Conta;

/**
 *
 * @author alunos
 */
public class TabelaConta extends AbstractTable<Conta>{

    
     public TabelaConta() {
        //o que o usuário verá
        this( "Nome Cliente", "Nome Agencia", "Saldo");
    }
     
    public TabelaConta(String... colunas) {
        super(colunas);
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Conta conta = getRegistros().get(rowIndex);
        return switch (columnIndex) {
          //  case 0 -> cliente.getId();
            case 0 -> conta.getCliente().getNome();
            case 1 -> conta.getAgencia().getNome();
            case 2 -> conta.getSaldo();
            default -> null;
    };
    
}
}
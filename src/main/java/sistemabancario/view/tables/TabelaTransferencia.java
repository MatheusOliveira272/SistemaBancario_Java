/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Transferencia;

/**
 *
 * @author alunos
 */
public class TabelaTransferencia extends AbstractTable<Transferencia>{
    
    public TabelaTransferencia(){
          this( "Nome origem", "Nome destino","Valor", "Data Transferencia");
    }
    
      public TabelaTransferencia(String... colunas) {
        super(colunas);
    }
     

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transferencia transferencia = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> transferencia.getContaOrigem().getCliente().getNome();
            case 1 -> transferencia.getContaDestino().getCliente().getNome();
            case 2 -> transferencia.getValorTransferido();
            case 3 -> transferencia.getDataPagamento();  
            default -> null;
    };
    
    
    }
    
}

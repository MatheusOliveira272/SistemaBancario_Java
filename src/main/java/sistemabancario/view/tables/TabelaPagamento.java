/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Pagamento;

/**
 *
 * @author alunos
 */
public class TabelaPagamento extends AbstractTable<Pagamento>{
    
    
     public TabelaPagamento() {
        //o que o usuário verá
        this( "Nome Cliente", "Id Conta","Agência", "Valor Pago", "Data Pagamento");
    }

    public TabelaPagamento(String... colunas) {
        super(colunas);
    }
     
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Pagamento pagamento = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> pagamento.getConta().getCliente().getNome();
            case 1 -> pagamento.getConta().getId();
            case 2 -> pagamento.getConta().getAgencia().getNome();
            case 3 -> pagamento.getValorPago();
            case 4 -> pagamento.getDataPagamento();
            default -> null;
    };
    
}
    
    
}

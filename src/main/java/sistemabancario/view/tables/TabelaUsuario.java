/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Usuario;

/**
 *
 * @author Matheus
 */

public class TabelaUsuario extends AbstractTable<Usuario>{
    
     
     public TabelaUsuario() {
        //o que o usuário verá
        this( "Nome", "Email", "Status");
     }

    public TabelaUsuario(String... colunas) {
        super(colunas);
    }
     
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Usuario usuario = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> usuario.getNome();
            case 1 -> usuario.getEmail();
            case 2 -> usuario.getStatus();
          
            default -> null;
    };
    
}
    
    
}

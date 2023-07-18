/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import sistemabancario.model.domain.Login;

/**
 *
 * @author Matheus
 */
public class TabelaLoginPrincipal extends AbstractTable<Login>{
     public TabelaLoginPrincipal() {
        //o que o usuário verá
        this("Usuário", "Email", "Data Login");
    }

    public TabelaLoginPrincipal(String... colunas) {
        super(colunas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Login login = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> login.getUsuario().getNome();
            case 1 -> login.getUsuario().getEmail();
            case 2 -> login.getDataLogin();
          
            default -> null;
        };

    }
}

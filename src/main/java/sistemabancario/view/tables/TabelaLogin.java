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
public class TabelaLogin extends AbstractTable<Login> {

    public TabelaLogin() {
        //o que o usuário verá
        this("Nome", "Email", "Data Login", "Data Logoff");
    }

    public TabelaLogin(String... colunas) {
        super(colunas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Login login = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> login.getUsuario().getNome();
            case 1 -> login.getUsuario().getEmail();
            case 2 -> login.getDataLogin();
            case 3 -> login.getDataLogoff();
            default -> null;
        };

    }
}

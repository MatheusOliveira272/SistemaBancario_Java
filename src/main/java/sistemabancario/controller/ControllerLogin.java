/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.sql.Date;
import java.time.LocalDate;
import sistemabancario.exception.LoginException;
import sistemabancario.model.dao.LoginDao;
import sistemabancario.model.dao.UsuarioDao;
import sistemabancario.model.domain.Login;
import sistemabancario.model.domain.Usuario;
//import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaListaLogin;
import sistemabancario.view.TelaLogin;
import sistemabancario.view.TelaPrincipal;
import sistemabancario.view.tables.TabelaLogin;

/**
 *
 * @author Matheus
 */
public class ControllerLogin {

    public void cadastraLogin(TelaLogin t) throws LoginException {
        LoginDao loginDao = new LoginDao();
        Login login = new Login();
        login.setId(null);
        
        

        //encontrando o id do email
        var usuario = t.getTxtFieldEmailLogin().getText();
        var idUsuario = new UsuarioDao().buscaUsuarioPorEmail(usuario);
        var usuarioLogado = idUsuario.get();
        login.setUsuario((Usuario) usuarioLogado);
        
        
        login.setDataLogin(Date.valueOf(LocalDate.now()));
        loginDao.inserir(login);
      /*  
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        telaPrincipal.setLabelNomeUsuario(login.getUsuario().getNome());
        telaPrincipal.setLabelEmailUsuario(login.getUsuario().getEmail());
        telaPrincipal.setLabelAcessoUsuario(login.getDataLogin());
       */

    }
    
    public void logOff(){
        
    }
    
     public void pesquisar(TelaListaLogin t) {
        TabelaLogin tabelaLogin = (TabelaLogin) t.getTabelaListaLogin().getModel();
        LoginDao loginDao = new LoginDao();
        tabelaLogin.setRegistros(loginDao.buscarTodosLogins());
    }

}

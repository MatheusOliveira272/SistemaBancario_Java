/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.controller;

import java.sql.Date;
import java.time.LocalDate;
import sistemabancario.exception.LoginException;
import sistemabancario.exception.UsuarioException;
import sistemabancario.model.dao.LoginDao;
import sistemabancario.model.dao.UsuarioDao;
import sistemabancario.model.domain.Usuario;
import sistemabancario.utils.Criptografar;
import sistemabancario.utils.GerarMensagens;
import sistemabancario.view.TelaCadastroUsuario;
import sistemabancario.view.TelaLogin;
import sistemabancario.view.TelaNovoUsuario;
import sistemabancario.view.TelaPrincipal;
import sistemabancario.view.tables.TabelaUsuario;

/**
 *
 * @author Matheus
 */
public class ControllerUsuario {
    
    public void efetuarLogin(TelaLogin t) throws UsuarioException, LoginException{
        var usuario = t.getTxtFieldEmailLogin().getText();
        //cria uma String a partir de um array de bytes para senha
        var senha = new String (t.getTxtFieldSenhaLogin().getPassword());
        
        var senhaEncriptografada = Criptografar.encryp(senha);
        //o optional é para deixar mais legivel
        var optionalUsuario = new UsuarioDao().buscaUsuarioPorEmail(usuario);
        
        if(optionalUsuario.isEmpty()){
            throw new UsuarioException("Usuário/senha Inválido" + "Não Encontrado");
            
        }
        
        var usuarioLogado = optionalUsuario.get();
        if(usuarioLogado.getSenha().equals(senhaEncriptografada)){
         //aqui23/04
           var controller = new ControllerLogin();
                 controller.cadastraLogin(t);
            
          
            
            new TelaPrincipal().setVisible(true);
                t.dispose();
        }else{
            throw new UsuarioException("Usuário/senha Inválido" + "Não Encontrado");
        }
        
    }
    
     public void pesquisar(TelaCadastroUsuario t) {
        TabelaUsuario tabelaUsuario = (TabelaUsuario) t.getTableListaUsuario().getModel();
        UsuarioDao usuarioDao = new UsuarioDao();
        tabelaUsuario.setRegistros(usuarioDao.buscarTodosUsuarios());
    }

    public void excluirUsuario(TelaCadastroUsuario t) throws UsuarioException {
        if (t.getTableListaUsuario().getSelectedRowCount() == 0) {
            throw new UsuarioException("Nenhum registro selecionado!");
        }

        int row = t.getTableListaUsuario().getSelectedRow();
        TabelaUsuario tabelaUsuario = (TabelaUsuario) t.getTableListaUsuario().getModel();
        Usuario usuario = tabelaUsuario.getRegistros().get(row);

        if (GerarMensagens.confirmar(t, "Deseja excluir a conta de nome " + usuario.getNome() + "?")) {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.deletar(usuario.getId());
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
    }

    public void salvar(TelaNovoUsuario t) throws UsuarioException {
        if (t.getTxtEmailUsuario().getText().isBlank()) {
            throw new UsuarioException("Email não informado!");
        }
        if (new String(t.getPasswordSenhaUsuario().getPassword()).isBlank()) {
            throw new UsuarioException("Senha não informada!");
        }
        if (new String(t.getPasswoerdConfirmaSenhaUsuario().getPassword()).isBlank()) {
            throw new UsuarioException("Senha não informada!");
        }
        if (t.getTxtNomeUsuario().getText().isBlank()) {
            throw new UsuarioException("Nome não informado!");
        }

        if (new String(t.getPasswoerdConfirmaSenhaUsuario().getPassword())
                .equals(new String(t.getPasswordSenhaUsuario().getPassword()))) {
            UsuarioDao usuarioDao = new UsuarioDao();
            var email = usuarioDao.buscaUsuarioPorEmail(t.getTxtEmailUsuario().getText());
            if (email.isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setId(null);
                usuario.setNome(t.getTxtNomeUsuario().getText());
                usuario.setEmail(t.getTxtEmailUsuario().getText());
                usuario.setSenha(Criptografar.encryp(new String(t.getPasswordSenhaUsuario().getPassword())));
                usuario.setDataCadastro(Date.valueOf(LocalDate.now()));
                usuarioDao.inserir(usuario);
                GerarMensagens.alerta(t, "Salvo com sucesso!");
                t.dispose();
            } else {
                GerarMensagens.erro(t, "O email fornecido já está cadastrado");
            }

        } else {
            GerarMensagens.erro(t, "Senha não bate!");
        }

    }
}

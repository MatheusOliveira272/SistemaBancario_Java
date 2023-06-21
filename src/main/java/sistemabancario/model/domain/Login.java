/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.model.domain;

import java.util.Date;

/**
 *
 * @author Matheus
 */
public class Login {
    
    private Integer id;
    private Usuario usuario;
    private Date dataLogin;
    private Date dataLogoff;

    public Login() {
    }

    public Login(Integer id, Usuario usuario, Date dataLogin, Date dataLogoff) {
        this.id = id;
        this.usuario = usuario;
        this.dataLogin = dataLogin;
        this.dataLogoff = dataLogoff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(Date dataLogin) {
        this.dataLogin = dataLogin;
    }

    public Date getDataLogoff() {
        return dataLogoff;
    }

    public void setDataLogoff(Date dataLogoff) {
        this.dataLogoff = dataLogoff;
    }
    
    
}

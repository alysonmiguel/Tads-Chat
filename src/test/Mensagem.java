package test;

import java.io.Serializable;
import java.util.UUID;

public class Mensagem implements Serializable {
	public String nome;
	public String msg;
	
	Mensagem (String nomeUsuario, String mensagem) {
		this.nome = nomeUsuario;
		this.msg = mensagem;
	}
	public String toString() {
		return this.nome + "\t" + " msg: " + this.msg;
	}

}

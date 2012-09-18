package org.dejava.service.address.model.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.TipoLogradouro;

/**
 * Tipo do logradouro de um endere_o.
 */
@Entity
@Table(name = "tipologradouro")
@A2MAnotacaoVOCorrespondente(classeVO = TipoLogradouro.class)
public class TipoLogradouroEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = -5327632958932973124L;

	/**
	 * Nome do tipo do logradouro.
	 */
	private String nome;

	/**
	 * Modifica o nome do tipo do logradouro.
	 * 
	 * @param nome
	 *            Novo nome do tipo do logradouro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do tipo do logradouro.
	 * 
	 * @return O nome do tipo do logradouro.
	 */
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
}

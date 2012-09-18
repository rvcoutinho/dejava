package org.dejava.service.address.model.endereco;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoRecuperacaoAlternativa;
import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Logradouro;

/**
 * Logradouro de um endere_o.
 */
@Entity
@Table(name = "logradouro")
@A2MAnotacaoVOCorrespondente(classeVO = Logradouro.class)
@A2MAnotacaoRecuperacaoAlternativa(nomesAtributos = { "nome", "bairro.nome" }, operacoesLogicas = "AND")
public class LogradouroEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 7911093742052594406L;

	/**
	 * Bairro do logradouro.
	 */
	private BairroEP bairro;

	/**
	 * Modifica o bairro do logradouro.
	 * 
	 * @param bairro
	 *            Novo bairro do logradouro.
	 */
	public void setBairro(BairroEP bairro) {
		this.bairro = bairro;
	}

	/**
	 * Retorna o bairro do logradouro.
	 * 
	 * @return O bairro do logradouro.
	 */
	@JoinColumn(name = "bairro")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public BairroEP getBairro() {
		return bairro;
	}

	/**
	 * Tipo do logradouro.
	 */
	private TipoLogradouroEP tipo;

	/**
	 * Modifica o tipo do logradouro.
	 * 
	 * @param tipo
	 *            Novo tipo do logradouro.
	 */
	public void setTipo(TipoLogradouroEP tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna o tipo do logradouro.
	 * 
	 * @return O tipo do logradouro.
	 */
	@JoinColumn(name = "tipo")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	public TipoLogradouroEP getTipo() {
		return tipo;
	}

	/**
	 * Nome do logradouro.
	 */
	private String nome;

	/**
	 * Modifica o nome do logradouro.
	 * 
	 * @param nome
	 *            Novo nome do logradouro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do logradouro.
	 * 
	 * @return O nome do logradouro.
	 */
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
}

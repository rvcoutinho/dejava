package org.dejava.service.address.model.endereco;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoRecuperacaoAlternativa;
import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Bairro;

/**
 * Bairro de um endere_o.
 */
@Entity
@Table(name = "bairro")
public class BairroEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 2230938008830472319L;

	/**
	 * Cidade do bairro.
	 */
	private CidadeEP cidade;

	/**
	 * Modifica a cidade do bairro.
	 * 
	 * @param cidade
	 *            Nova cidade do bairro.
	 */
	public void setCidade(CidadeEP cidade) {
		this.cidade = cidade;
	}

	/**
	 * Retorna a cidade do bairro.
	 * 
	 * @return A cidade do bairro.
	 */
	@JoinColumn(name = "cidade")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public CidadeEP getCidade() {
		return cidade;
	}

	/**
	 * Nome do bairro.
	 */
	private String nome;

	/**
	 * Modifica o nome do bairro.
	 * 
	 * @param nome
	 *            Novo nome do bairro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do bairro.
	 * 
	 * @return O nome do bairro.
	 */
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
}
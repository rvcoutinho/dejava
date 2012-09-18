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
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Cidade;

/**
 * Cidade de um endere_o.
 */
@Entity
@Table(name = "cidade")
@A2MAnotacaoVOCorrespondente(classeVO = Cidade.class)
@A2MAnotacaoRecuperacaoAlternativa(nomesAtributos = { "nome", "uf.nome" }, operacoesLogicas = "AND")
public class CidadeEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 5057633694511508712L;

	/**
	 * UF da cidade.
	 */
	private UFEP uf;

	/**
	 * Modifica a UF da cidade.
	 * 
	 * @param uf
	 *            Nova UF da cidade.
	 */
	public void setUf(UFEP uf) {
		this.uf = uf;
	}

	/**
	 * Retorna a UF da cidade.
	 * 
	 * @return A UF da cidade.
	 */
	@JoinColumn(name = "uf")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public UFEP getUf() {
		return uf;
	}

	/**
	 * Nome da cidade.
	 */
	private String nome;

	/**
	 * Modifica o nome da cidade.
	 * 
	 * @param nome
	 *            Novo nome da cidade.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome da cidade.
	 * 
	 * @return O nome da cidade.
	 */
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
}

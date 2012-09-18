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
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.UF;

/**
 * UF de um endere_o.
 */
@Entity
@Table(name = "uf")
@A2MAnotacaoVOCorrespondente(classeVO = UF.class)
@A2MAnotacaoRecuperacaoAlternativa(nomesAtributos = { "nome", "pais.nome" }, operacoesLogicas = "AND")
public class UFEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = -6863190631437468120L;

	/**
	 * Pa_s da UF.
	 */
	private PaisEP pais;

	/**
	 * Modifica o pa_s da UF.
	 * 
	 * @param pais
	 *            Novo pa_s da UF.
	 */
	public void setPais(PaisEP pais) {
		this.pais = pais;
	}

	/**
	 * Retorna o pa_s da UF.
	 * 
	 * @return O pa_s da UF.
	 */
	@JoinColumn(name = "pais")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public PaisEP getPais() {
		return pais;
	}

	/**
	 * Sigla da UF.
	 */
	private String sigla;

	/**
	 * Modifica a sigla da UF.
	 * 
	 * @param sigla
	 *            Nova sigla da UF.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * Retorna a sigla da UF.
	 * 
	 * @return A sigla da UF.
	 */
	@Column(name = "sigla")
	public String getSigla() {
		return sigla;
	}

	/**
	 * Nome da UF.
	 */
	private String nome;

	/**
	 * Modifica o nome da UF.
	 * 
	 * @param nome
	 *            Novo nome da UF.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome da UF.
	 * 
	 * @return O nome da UF.
	 */
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
}

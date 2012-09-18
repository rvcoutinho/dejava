package org.dejava.service.address.model.endereco;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Endereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.UsoEndereco;

/**
 * Endere_o de uma endereco.
 */
@Entity
@Table(name = "endereco")
@A2MAnotacaoVOCorrespondente(classeVO = Endereco.class)
public class EnderecoEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 5815188488841094478L;

	/**
	 * Uso do endere_o.
	 */
	private UsoEndereco uso;

	/**
	 * Modifica o uso do endere_o.
	 * 
	 * @param uso
	 *            Novo uso do endere_o.
	 */
	public void setUso(UsoEndereco uso) {
		this.uso = uso;
	}

	/**
	 * Retorna o uso do endere_o.
	 * 
	 * @return O uso do endere_o.
	 */
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "uso")
	public UsoEndereco getUso() {
		return uso;
	}

	/**
	 * CEP do endere_o.
	 */
	private CEPEP cep;

	/**
	 * Modifica o CEP do endere_o.
	 * 
	 * @param cep
	 *            Novo CEP do endere_o.
	 */
	public void setCep(CEPEP cep) {
		this.cep = cep;
	}

	/**
	 * Retorna o CEP do endere_o.
	 * 
	 * @return O CEP do endere_o.
	 */
	@JoinColumn(name = "cep")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public CEPEP getCep() {
		return cep;
	}

	/**
	 * N_mero do endere_o.
	 */
	private NumeroEP numero;

	/**
	 * Modifica o n_mero do endere_o.
	 * 
	 * @param numero
	 *            Novo n_mero do endere_o.
	 */
	public void setNumero(NumeroEP numero) {
		this.numero = numero;
	}

	/**
	 * Retorna o n_mero do endere_o.
	 * 
	 * @return O n_mero do endere_o.
	 */
	@JoinColumn(name = "numero")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public NumeroEP getNumero() {
		return numero;
	}

	/**
	 * Complemento do endere_o.
	 */
	private String complemento;

	/**
	 * Modifica o complemento do endere_o.
	 * 
	 * @param complemento
	 *            Novo complemento do endere_o.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Retorna o complemento do endere_o.
	 * 
	 * @return O complemento do endere_o.
	 */
	@Column(name = "complemento")
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Descri__o do endere_o.
	 */
	private String descricao;

	/**
	 * Modifica a descri__o do endere_o.
	 * 
	 * @param descricao
	 *            Nova descri__o do endere_o.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a descri__o do endere_o.
	 * 
	 * @return a descri__o do endere_o.
	 */
	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}
}

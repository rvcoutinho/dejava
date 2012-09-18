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
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Numero;

/**
 * N_mero de um endere_o.
 */
@Entity
@Table(name = "numero")
@A2MAnotacaoVOCorrespondente(classeVO = Numero.class)
@A2MAnotacaoRecuperacaoAlternativa(nomesAtributos = { "valor",
		"logradouro.nome" }, operacoesLogicas = "AND")
public class NumeroEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 7295767544513160500L;

	/**
	 * Logradouro do n_mero.
	 */
	private LogradouroEP logradouro;

	/**
	 * Modifica o logradouro do n_mero.
	 * 
	 * @param logradouro
	 *            Novo logradouro do n_mero.
	 */
	public void setLogradouro(LogradouroEP logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Retorna o logradouro do n_mero.
	 * 
	 * @return O logradouro do n_mero.
	 */
	@JoinColumn(name = "logradouro")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public LogradouroEP getLogradouro() {
		return logradouro;
	}

	/**
	 * Valor do n_mero.
	 */
	private String valor;

	/**
	 * Modifica o valor do n_mero.
	 * 
	 * @param valor
	 *            Novo valor do n_mero.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Retorna o valor do n_mero.
	 * 
	 * @return O valor do n_mero.
	 */
	@Column(name = "valor")
	public String getValor() {
		return valor;
	}
}

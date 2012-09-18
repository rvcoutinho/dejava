package org.dejava.service.address.model.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.CEP;

/**
 * CEP de um endere_o.
 */
@Entity
@Table(name = "cep")
@A2MAnotacaoVOCorrespondente(classeVO = CEP.class)
public class CEPEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 1636229982189890682L;

	/**
	 * N_mero do CEP.
	 */
	private String numero;

	/**
	 * Modifica o n_mero do CEP.
	 * 
	 * @param numero
	 *            Novo n_mero do CEP.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Retorna o n_mero do CEP.
	 * 
	 * @return O n_mero do CEP.
	 */
	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}
}

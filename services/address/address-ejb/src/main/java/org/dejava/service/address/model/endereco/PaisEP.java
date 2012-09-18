package org.dejava.service.address.model.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoRecuperacaoAlternativa;
import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.eps.EPEndereco;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.endereco.Pais;

/**
 * Pa_s de um endere_o.
 */
@Entity
@Table(name = "pais")
@A2MAnotacaoVOCorrespondente(classeVO = Pais.class)
@A2MAnotacaoRecuperacaoAlternativa(nomesAtributos = "nome")
public class PaisEP extends EPEndereco {

	/**
	 * Versão da classe.
	 */
	private static final long serialVersionUID = 6123690032128252822L;

	/**
	 * C_digo do pa_s.
	 */
	private Integer codigo;

	/**
	 * Modifica o c_digo do pa_s.
	 * 
	 * @param codigo
	 *            Novo c_digo do pa_s.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o c_digo do pa_s.
	 * 
	 * @return O c_digo do pa_s.
	 */
	@Column(name = "codigo", unique = true)
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sigla do pa_s.
	 */
	private String sigla;

	/**
	 * Modifica a sigla do pa_s.
	 * 
	 * @param sigla
	 *            Nova sigla do pa_s.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * Retorna a sigla do pa_s.
	 * 
	 * @return A sigla do pa_s.
	 */
	@Column(name = "sigla", unique = true)
	public String getSigla() {
		return sigla;
	}

	/**
	 * Nome do pa_s.
	 */
	private String nome;

	/**
	 * Modifica o nome do pa_s.
	 * 
	 * @param nome
	 *            Novo nome do pa_s.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do pa_s.
	 * 
	 * @return O nome do pa_s.
	 */
	@Column(name = "nome", unique = true)
	public String getNome() {
		return nome;
	}
}

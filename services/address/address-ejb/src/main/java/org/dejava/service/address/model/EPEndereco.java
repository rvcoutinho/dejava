package org.dejava.service.address.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.anotacoes.A2MAnotacaoVOCorrespondente;
import br.ufrj.cos.lens.a2m.componentes.persistencia.superclasses.ep.A2MModeloEP;
import br.ufrj.cos.lens.a2m.ferramentas.endereco.vos.VOEndereco;

/**
 * Superclasse das entidades persistentes da ferramenta Endereco.
 */
@MappedSuperclass
@A2MAnotacaoVOCorrespondente(classeVO = VOEndereco.class)
public abstract class EPEndereco extends A2MModeloEP {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -6607891927374872871L;

	/**
	 * Identificador do objeto.
	 */
	private Integer id;

	/**
	 * Modifica o id;
	 * 
	 * @param id
	 *            Novo id.
	 */
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retorna o ID do objeto.
	 * 
	 * @return ID do objeto.
	 */
	@Override
	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "idGenerator")
	@TableGenerator(name = "idGenerator", table = "a2m", pkColumnName = "id", valueColumnName = "ultimoid", pkColumnValue = "1", allocationSize = 1)
	public Integer getId() {
		return id;
	}

	/**
	 * Versão para o lock otimista dos objetos.
	 */
	private Integer versaoLock;

	/**
	 * Modifica o(a) versaoLock.
	 * 
	 * @param versaoLock
	 *            Novo(a) versaoLock.
	 */
	public void setVersaoLock(Integer versaoLock) {
		this.versaoLock = versaoLock;
	}

	/**
	 * Retorna o(a) versaoLock.
	 * 
	 * @return O(A) versaoLock.
	 */
	@Override
	@Version
	@Column(name = "versaolock")
	public Integer getVersaoLock() {
		return versaoLock;
	}
}

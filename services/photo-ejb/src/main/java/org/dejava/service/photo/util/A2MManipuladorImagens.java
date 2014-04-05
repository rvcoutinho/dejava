//package org.dejava.service.photo.util;
//
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
///**
// * Classe que auxiliza a manipulação de imagens.
// */
//public class A2MManipuladorImagens {
//
//	/**
//	 * Recupera a imagem a partir de um array de bytes.
//	 * 
//	 * @param bytesImagem
//	 *            Bytes da imagem passada.
//	 * @return A imagem a partir de um array de bytes.
//	 * @throws A2MException
//	 *             Se a imagem não pôde ser recuperada.
//	 */
//	public static Image recuperarImagem(byte[] bytesImagem) throws A2MException {
//		// Tenta recuperar a imagem.
//		try {
//			return ImageIO.read(new ByteArrayInputStream(bytesImagem));
//		}
//		// Se alguam exceção for encontrada.
//		catch (IOException exception) {
//			// A exceção é relançada como causa da A2MException.
//			throw new A2MParametroInvalidoException("componentes.utilitarios.imagens.imagem.invalida", null,
//					exception);
//		}
//	}
//
//	/**
//	 * Recupera um array de bytes a partir de uma imagem.
//	 * 
//	 * @param imagem
//	 *            Bytes da imagem passada.
//	 * @param formato
//	 *            Formato que deve ter a imagem.
//	 * @return Um array de bytes a partir de uma imagem.
//	 * @throws A2MException
//	 *             Se a imagem não pôde ser recuperada.
//	 */
//	public static byte[] recuperarBytes(BufferedImage imagem, String formato) throws A2MException {
//		// Cria a cadeia de bytes de saída para a imagem.
//		ByteArrayOutputStream cadeiaBytes = new ByteArrayOutputStream();
//		// Tenta escrever a imagem na cadeia.
//		try {
//			ImageIO.write(imagem, formato, cadeiaBytes);
//			// Fecha a cadeia.
//			cadeiaBytes.close();
//		}
//		// Se alguma exceção for encontrada.
//		catch (Exception exception) {
//			// Relança a exceção.
//			throw new A2MParametroInvalidoException(
//					"componentes.utilitarios.manipuladorimagens.imagem.naoconvertida", null);
//		}
//		// Retorna o conteúdo da mesma.
//		return cadeiaBytes.toByteArray();
//	}
//
//	/**
//	 * Cria a BufferedImage para uma Imagem.
//	 * 
//	 * @param imagem
//	 *            Imagem fonte.
//	 * @return A BufferedImage para uma Imagem.
//	 */
//	public static BufferedImage getBufferedImage(Image imagem) {
//		// Cria a nova BufferedImage.
//		BufferedImage bufferedImage = new BufferedImage(imagem.getWidth(null), imagem.getHeight(null),
//				BufferedImage.TYPE_INT_ARGB);
//		// Desenha a imagem original na BufferedImage.
//		bufferedImage.getGraphics().drawImage(imagem, 0, 0, null);
//		// Retorna a BufferedImage.
//		return bufferedImage;
//	}
//
//	/**
//	 * Tipo de recorte a ser feito em uma imagem.
//	 */
//	public enum TipoRecorte {
//
//		/**
//		 * Se a área a ser mantida é a de cima a esquerda (área oposta é recortada).
//		 */
//		ManterEsquerdaCima,
//
//		/**
//		 * Se a área a ser mantida é a de baixo a esquerda (área oposta é recortada).
//		 */
//		ManterEsquerdaBaixo,
//
//		/**
//		 * Se a área a ser mantida é a de baixo a direita (área oposta é recortada).
//		 */
//		ManterDireitaBaixo,
//
//		/**
//		 * Se a área a ser mantida é a de cima a direita (área oposta é recortada).
//		 */
//		ManterDireitaCima,
//
//		/**
//		 * Se a área a ser mantida é a do meio (bordas são recortadas).
//		 */
//		ManterCentro;
//
//	}
//
//	/**
//	 * Recorta a imagem a partir de uma altura e largura máxima.
//	 * 
//	 * @param imagem
//	 *            Imagem que deve ser recortada.
//	 * @param alturaMaxima
//	 *            Altura máxima que a imagem deve ter.
//	 * @param larguraMaxima
//	 *            Largura máxima que a imagem deve ter.
//	 * @param tipo
//	 *            tipo de recorte da imagem.
//	 * @return Retorna a aimagem recortada.
//	 */
//	public static Image recortarImagem(Image imagem, Integer alturaMaxima, Integer larguraMaxima,
//			TipoRecorte tipo) {
//		// Altura do recorte é a altura da imagem por padrão.
//		Integer alturaRecorte = imagem.getHeight(null);
//		// Se a altura máxima é menor que a altura da imagem.
//		if (imagem.getHeight(null) > alturaMaxima) {
//			// A altura é a altura máxima.
//			alturaRecorte = alturaMaxima;
//		}
//		// Largura do recorte é a largura da imagem por padrão.
//		Integer larguraRecorte = imagem.getWidth(null);
//		// Se a largura máxima é menor que a largura da imagem.
//		if (imagem.getWidth(null) > larguraMaxima) {
//			// A largura é a largura máxima.
//			larguraRecorte = larguraMaxima;
//		}
//		// Cria uma nova imagem do tamanho do recorte.
//		BufferedImage novaImagem = new BufferedImage(larguraRecorte, alturaRecorte,
//				BufferedImage.TYPE_INT_ARGB);
//		// Para cada caso.
//		switch (tipo) {
//		// Se deve manter a esquerda em cima.
//		case ManterEsquerdaCima:
//			// Recorta a imagem.
//			novaImagem.getGraphics().drawImage(imagem, 0, 0, null);
//			// Termina o recorte.
//			break;
//		// Se deve manter a esquerda em baixo.
//		case ManterEsquerdaBaixo:
//			// Recorta a imagem.
//			novaImagem.getGraphics().drawImage(imagem, 0, alturaRecorte - imagem.getHeight(null), null);
//			// Termina o recorte.
//			break;
//		// Se deve manter a direita em baixo.
//		case ManterDireitaBaixo:
//			// Recorta a imagem.
//			novaImagem.getGraphics().drawImage(imagem, larguraRecorte - imagem.getWidth(null),
//					alturaRecorte - imagem.getHeight(null), null);
//			// Termina o recorte.
//			break;
//		// Se deve manter a direita em cima.
//		case ManterDireitaCima:
//			// Recorta a imagem.
//			novaImagem.getGraphics().drawImage(imagem, larguraRecorte - imagem.getWidth(null), 0, null);
//			// Termina o recorte.
//			break;
//		// Se deve manter o centro.
//		case ManterCentro:
//			// Recorta a imagem.
//			novaImagem.getGraphics().drawImage(imagem, (larguraRecorte - imagem.getWidth(null)) / 2,
//					(alturaRecorte - imagem.getHeight(null)) / 2, null);
//			// Termina o recorte.
//			break;
//		}
//		// Fecha o recorte.
//		novaImagem.flush();
//		// Retorna a imagem recortada.
//		return novaImagem;
//	}
//
//	/**
//	 * Tipo de redmimensionamento de uma imagem.
//	 */
//	public enum TipoRedimensionamento {
//
//		/**
//		 * Se a imagem deve ser apenas aumentada.
//		 */
//		APENAS_AUMENTAR,
//
//		/**
//		 * Se a imagem deve ser apenas diminuída.
//		 */
//		APENAS_DIMINUIR,
//
//		/**
//		 * Se a imagem pode tanto ser diminuida quanto aumentada.
//		 */
//		AUMENTAR_DIMINUIR;
//	}
//
//	/**
//	 * Tipo de proporção do redimensionamento das imagens.
//	 */
//	public enum TipoProporcaoRedimensionamento {
//
//		/**
//		 * As proporções da imagem original não devem ser mantidas.
//		 */
//		NAO_MANTER_PROPORCAO,
//
//		/**
//		 * As proporções da imagem original devem ser mantidas usando a menor dimensão relativa.
//		 */
//		MANTER_PROPORCAO_MINIMA,
//
//		/**
//		 * As proporções da imagem original devem ser mantidas usando a maior dimensão relativa.
//		 */
//		MANTER_PROPORCAO_MAXIMA;
//	}
//
//	/**
//	 * Algorítmo usado no redimensionamento das imagens.
//	 */
//	public enum AlgoritmoRedimensionamento {
//
//		/**
//		 * @see java.awt.Image#SCALE_FAST
//		 */
//		PADRAO(1),
//
//		/**
//		 * @see java.awt.Image#SCALE_FAST
//		 */
//		RAPIDO(2),
//
//		/**
//		 * @see java.awt.Image#SCALE_SMOOTH
//		 */
//		SUAVE(4),
//
//		/**
//		 * @see java.awt.Image#SCALE_REPLICATE
//		 */
//		REPLICAR(8),
//
//		/**
//		 * @see java.awt.Image#SCALE_AREA_AVERAGING
//		 */
//		AREA_MEDIA(16);
//
//		/**
//		 * Índice das opções.
//		 */
//		private Integer indice = 1;
//
//		/**
//		 * Construtor da opção do Enumeration.
//		 * 
//		 * @param indice
//		 *            Índice da opção nova.
//		 */
//		@SuppressWarnings("boxing")
//		private AlgoritmoRedimensionamento(int indice) {
//			this.indice = indice;
//		}
//
//		/**
//		 * Retorna o índice da opção do Enumeration.
//		 * 
//		 * @return Índice da opção do Enumeration.
//		 */
//		@SuppressWarnings("boxing")
//		public int getIndice() {
//			return indice;
//		}
//
//		/**
//		 * Retorna a opção do Enumeration correspondente ao índice desejado.<br>
//		 * Caso o índice não possua opção correspondente, é disparada uma exceção.
//		 * 
//		 * @param indice
//		 *            Índice desejado.
//		 * @return Opção do Enumeration correspondente.
//		 */
//		@SuppressWarnings("boxing")
//		public static AlgoritmoRedimensionamento getByIndice(Integer indice) {
//			switch (indice) {
//			case 1:
//				return PADRAO;
//			case 2:
//				return RAPIDO;
//			case 4:
//				return SUAVE;
//			case 8:
//				return REPLICAR;
//			case 16:
//				return AREA_MEDIA;
//			default:
//				throw new IndexOutOfBoundsException();
//			}
//		}
//	}
//
//	/**
//	 * Redimensiona a imagem passada. O último parâmetro define se a proporção deve ser mantida. Dessa forma,
//	 * os parâmetros da dimensão na verdade servem para definir a dimensão máxima da imagem.
//	 * 
//	 * @param imagem
//	 *            Imagem que deve ser redimensionada.
//	 * @param altura
//	 *            Nova altura para a imagem.
//	 * @param largura
//	 *            Nova largura para a imagem.
//	 * @param tipoRedimensionamento
//	 *            Tipo de redimensionamento da imagem. Por padrão (se passado nulo), o tipo é aumentar ou
//	 *            diminuir.
//	 * @param tipoProporcaoRedimensionamento
//	 *            Tipo da proporção de redimensionamento da imagem. Por padrão (se passado nulo), as
//	 *            proporções não serão mantidas.
//	 * @param algoritmoRedimensionamento
//	 *            Algorítmo usado no redimensionamento da imagem.
//	 * @return Retorna a imagem redimensionada.
//	 */
//	public static Image redimensionarImagem(Image imagem, Integer altura, Integer largura,
//			TipoRedimensionamento tipoRedimensionamento,
//			TipoProporcaoRedimensionamento tipoProporcaoRedimensionamento,
//			AlgoritmoRedimensionamento algoritmoRedimensionamento) {
//		// Se o algorítmo de redimensionamento for nulo.
//		if (algoritmoRedimensionamento == null) {
//			// O algorítimo padrão é utilizado.
//			algoritmoRedimensionamento = AlgoritmoRedimensionamento.PADRAO;
//		}
//		// Se a proporção deve ser mantida.
//		if ((tipoProporcaoRedimensionamento != null)
//				&& (tipoProporcaoRedimensionamento != TipoProporcaoRedimensionamento.NAO_MANTER_PROPORCAO)) {
//			// Recupera a relação entre a altura e a largura da imagem original.
//			Float proporcaoImagemOriginal = new Float(imagem.getHeight(null)) / imagem.getWidth(null);
//			// Recupera a mesma proporção da imagem com as dimensões passadas.
//			Float proporcaoImagemDimensoesPassadas = new Float(altura) / largura;
//			// Se a proporção máxima deve ser mantida.
//			if (tipoProporcaoRedimensionamento == TipoProporcaoRedimensionamento.MANTER_PROPORCAO_MAXIMA) {
//				// Se a porporção da imagem redimensionada é maior (ou igual)
//				// que a da imagem original.
//				if (proporcaoImagemOriginal >= proporcaoImagemDimensoesPassadas) {
//					// Então, a altura é a dimensão predominante no
//					// redimensionamento. E, portanto, a largura deve ser
//					// recalculada com base na proporção da imagem original.
//					largura = new Float(altura / proporcaoImagemOriginal).intValue();
//				}
//				// Caso contrário, a largura é a dimensão predominante, e a
//				// altura precisa ser recalculada.
//				else {
//					altura = new Float(largura * proporcaoImagemOriginal).intValue();
//				}
//			}
//			// Se a proporção mínima deve ser mantida.
//			else if (tipoProporcaoRedimensionamento == TipoProporcaoRedimensionamento.MANTER_PROPORCAO_MINIMA) {
//				// Se a porporção da imagem redimensionada é menor (ou igual)
//				// que a da imagem original.
//				if (proporcaoImagemOriginal <= proporcaoImagemDimensoesPassadas) {
//					// Então, a altura é a dimensão predominante no
//					// redimensionamento. E, portanto, a largura deve ser
//					// recalculada com base na proporção da imagem original.
//					largura = new Float(altura / proporcaoImagemOriginal).intValue();
//				}
//				// Caso contrário, a largura é a dimensão predominante, e a
//				// altura precisa ser recalculada.
//				else {
//					altura = new Float(largura * proporcaoImagemOriginal).intValue();
//				}
//			}
//		}
//		// Para cada tipo de redimensionamento.
//		switch (tipoRedimensionamento) {
//		case APENAS_AUMENTAR:
//			// Se a dimensão da imagem original for menor que a da imagem
//			// redimensionada.
//			if (imagem.getWidth(null) < largura) {
//				// Retorna a imagem redimensionada.
//				return imagem.getScaledInstance(largura, altura, algoritmoRedimensionamento.getIndice());
//			}
//			// Senão.
//			else {
//				// Retorna a imagem original.
//				return imagem;
//			}
//		case APENAS_DIMINUIR:
//			// Se a dimensão da imagem original for menor que a da imagem
//			// redimensionada.
//			if (imagem.getWidth(null) < largura) {
//				// Retorna a imagem original.
//				return imagem;
//			}
//			// Senão.
//			else {
//				// Retorna a imagem redimensionada.
//				return imagem.getScaledInstance(largura, altura, algoritmoRedimensionamento.getIndice());
//			}
//		default:
//			// Retorna a imagem redimensionada.
//			return imagem.getScaledInstance(largura, altura, algoritmoRedimensionamento.getIndice());
//		}
//	}
//
//	/**
//	 * Redimensiona e recorta uma imagem.
//	 * 
//	 * @param imagem
//	 *            Imagem que deve ser redimensionada.
//	 * @param altura
//	 *            Nova altura para a imagem.
//	 * @param largura
//	 *            Nova largura para a imagem.
//	 * @param tipoRedimensionamento
//	 *            Tipo de redimensionamento da imagem. Por padrão (se passado nulo), o tipo é aumentar ou
//	 *            diminuir.
//	 * @param tipoProporcaoRedimensionamento
//	 *            Tipo da proporção de redimensionamento da imagem. Por padrão (se passado nulo), as
//	 *            proporções não serão mantidas.
//	 * @param algoritmoRedimensionamento
//	 *            Algorítmo usado no redimensionamento da imagem.
//	 * @param tipoRecorte
//	 *            tipo de recorte da imagem.
//	 * @return Retorna a imagem redimensionada e recortada.
//	 */
//	public static Image redimensionarRecortarImagem(Image imagem, Integer altura, Integer largura,
//			TipoRedimensionamento tipoRedimensionamento,
//			TipoProporcaoRedimensionamento tipoProporcaoRedimensionamento,
//			AlgoritmoRedimensionamento algoritmoRedimensionamento, TipoRecorte tipoRecorte) {
//		// Redimensiona a imagem.
//		imagem = redimensionarImagem(imagem, altura, largura, tipoRedimensionamento,
//				tipoProporcaoRedimensionamento, algoritmoRedimensionamento);
//		// Recorta a imagem.
//		imagem = recortarImagem(imagem, altura, largura, tipoRecorte);
//		// Retorna a imagem.
//		return imagem;
//	}
// }

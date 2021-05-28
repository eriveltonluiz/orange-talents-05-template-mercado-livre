package br.com.zupacademy.erivelton.mercadolivre.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequisicao {
	
	@NotNull
	@Size(min = 1)
	private Set<MultipartFile> imagens = new HashSet<MultipartFile>();
	
	public ImagemRequisicao(Set<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public Set<MultipartFile> getImagens() {
		return imagens;
	}
}

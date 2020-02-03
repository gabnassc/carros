package com.carros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "carro")
@Data
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "url_foto")
	private String urlFoto;
	@Column(name = "url_video")
	private String urlVideo;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	

}

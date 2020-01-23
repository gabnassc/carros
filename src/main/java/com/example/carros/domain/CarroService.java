package com.example.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.exception.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {
		List<Carro> carros = rep.findAll();
		List<CarroDTO> list = carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());

		return list;
	}

	public CarroDTO getCarroById(Long id){

		Optional<Carro> carro = rep.findById(id);
		return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Nao foi possivel inserir o registro.");
		return CarroDTO.create(rep.save(carro));
	}

	public Optional<Carro> getCarroAuxiliar(Long id) {
		return rep.findById(id);
	}

	public CarroDTO update(Carro carro, Long id) {
//		Assert.isNull(id, "Não foi possível atualizar o registro!");

		// Busca o carro no BD
		Optional<Carro> optional = rep.findById(id);

		if (optional.isPresent()) {
			Carro db = optional.get();

			// copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());

			System.out.println("Carro id: " + db.getId());

			// Atualiza o carro
			rep.save(db);

			return CarroDTO.create(db);

		} else {
			return null;
		}

	}

	public void delete(Long id) {

		rep.deleteById(id);
	}

}

package com.example.carros;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.domain.exception.ObjectNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	public void testSave(){
		Carro carro = new Carro();

		carro.setNome("Ferrari");
		carro.setTipo("esportivo");

		CarroDTO c = service.insert(carro);
		assertNotNull(c);
		Long id = c.getId();

		assertNotNull(id);

//		Buscar  o Objeto
		c = service.getCarroById(id);
		assertNotNull(c);


		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivo", c.getTipo());

//		Deletar o objeto criado
		service.delete(id);

//		Verificar se deletou
		 try {
	            service.getCarroById(id);
	            fail("O carro não foi excluído");
	        } catch (ObjectNotFoundException e) {
	            // OK
	        }
	}

	

	@Test
	public void test2() {

		List<CarroDTO> carros = service.getCarros();

		assertEquals(30, carros.size());

	}

	@Test
	public void testGet() throws ObjectNotFoundException {

		CarroDTO carro = service.getCarroById(11L);

		assertNotNull(carro);
		
		assertEquals("Ferrari FF", carro.getNome());

	}
	
	
	@Test
	public void testListaPorTipo() {
		assertEquals(10,service.getCarroByTipo("esportivos").size());
		assertEquals(10 ,service.getCarroByTipo("classicos").size());
		assertEquals(10 ,service.getCarroByTipo("luxo").size());


	}
	
	
}

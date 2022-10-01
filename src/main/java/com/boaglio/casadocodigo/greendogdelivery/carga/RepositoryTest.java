package com.boaglio.casadocodigo.greendogdelivery.carga;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.domain.Cliente;
import com.boaglio.casadocodigo.greendogdelivery.domain.Item;
import com.boaglio.casadocodigo.greendogdelivery.domain.Pedido;
import com.boaglio.casadocodigo.greendogdelivery.repository.ClienteRepository;


@Component
public class RepositoryTest 
 implements ApplicationRunner 
{

	private static final long ID_CLIENTE_PEDRO = 11l;
	private static final long ID_CLIENTE_DAVI = 22l;
	
	private static final long ID_ITEM1 = 100l;
	private static final long ID_ITEM2 = 101l;
	private static final long ID_ITEM3 = 102l;
	
	private static final long ID_PEDIDO1 = 1000l;
	private static final long ID_PEDIDO2 = 1001l;
	private static final long ID_PEDIDO3 = 1002l;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    	System.out.println(">>> Iniciando carga de dados...");
    	Cliente Pedro = new Cliente(ID_CLIENTE_PEDRO,"Pedro Leite","Bairro de Fátima");
    	Cliente Davi = new Cliente(ID_CLIENTE_DAVI,"Davi Leite","Serrinha");    	
    	
    	Item burger1 = new Item(ID_ITEM1,"Burger tradicional",25d);
    	Item burger2 = new Item(ID_ITEM2,"Burger tradicional picante",27d);
		Item salada3 = new Item(ID_ITEM3,"Pedro's max salada",30d);
    	
    	List<Item> listaPedidoPedro1 = new ArrayList<Item>();
    	listaPedidoPedro1.add(burger1);

    	List<Item> listaPedidoDavi1 = new ArrayList<Item>();
    	listaPedidoDavi1.add(burger2);
    	listaPedidoDavi1.add(salada3);
    	
    	Pedido pedidoDoPedro = new Pedido(ID_PEDIDO1,Pedro,listaPedidoPedro1,burger1.getPreco());
    	Pedro.novoPedido(pedidoDoPedro);
    	
    	Pedido pedidoDoDavi = new Pedido(ID_PEDIDO2,Davi,listaPedidoDavi1, burger2.getPreco()+salada3.getPreco());
    	Davi.novoPedido(pedidoDoDavi);
    	
    	System.out.println(">>> Pedido 1 - Pedro : "+ pedidoDoPedro);
    	System.out.println(">>> Pedido 2 - Davi: " + pedidoDoDavi);
    	
       
		clienteRepository.saveAndFlush(Davi);
		System.out.println(">>> Gravado cliente 2: "+ Davi);

		List<Item> listaPedidoPedro2 = new ArrayList<Item>();
		listaPedidoPedro2.add(burger2);
    	Pedido pedido2DoPedro  = new Pedido(ID_PEDIDO3,Pedro,listaPedidoPedro2,burger2.getPreco());
    	Pedro.novoPedido(pedido2DoPedro);
    	clienteRepository.saveAndFlush(Pedro);
    	System.out.println(">>> Pedido 2 - Pedro : "+ pedido2DoPedro);
    	System.out.println(">>> Gravado cliente 1: "+Pedro);
		
    }
 
}
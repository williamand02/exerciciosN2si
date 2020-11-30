package br.edu.faculdadedelta.modelo;

import java.util.Date;

public class VendaWilliam {
	private Long id_venda;
	private String desc_cliente;
	private String desc_produto;
	private double valor_produto;
	private Date data_venda;

	public VendaWilliam() {
	}
	
	public VendaWilliam(Long id_venda, String desc_cliente, String desc_produto, int valor_produto, Date data_venda) {
		this.id_venda = id_venda;
		this.desc_cliente = desc_cliente;
		this.desc_produto = desc_produto;
		this.valor_produto = valor_produto;
		this.data_venda = data_venda;
	}

	public Long getId_venda() {
		return id_venda;
	}

	public void setId_venda(Long id_venda) {
		this.id_venda = id_venda;
	}

	public String getDesc_cliente() {
		return desc_cliente;
	}

	public void setDesc_cliente(String desc_cliente) {
		this.desc_cliente = desc_cliente;
	}

	public String getDesc_produto() {
		return desc_produto;
	}

	public void setDesc_produto(String desc_produto) {
		this.desc_produto = desc_produto;
	}

	public double getValor_produto() {
		return valor_produto;
	}

	public void setValor_produto(double d) {
		this.valor_produto = d;
	}

	public Date getData_venda() {
		return data_venda;
	}

	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}

}

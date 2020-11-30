package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.VendaDaoWilliam;
import br.edu.faculdadedelta.modelo.VendaWilliam;

@ManagedBean
@SessionScoped
public class VendaControllerWilliam {
	private VendaWilliam venda = new VendaWilliam();
	private VendaDaoWilliam dao = new VendaDaoWilliam();
	
	public VendaWilliam getVenda() {
		return venda;
	}
	public void setVenda(VendaWilliam venda) {
		this.venda = venda;
	}

	public void limparCampos() {
		venda = new VendaWilliam();
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String salvar() {
		try {
			Calendar c = Calendar.getInstance();
			c.set(2021, Calendar.JANUARY, 1);
			Date dataLimite = c.getTime();
					
			if (venda.getData_venda().after(new Date()) && venda.getData_venda().before(dataLimite)) {
				if (venda.getId_venda() == null) {
					// incluir
					dao.incluir(venda);
					FacesMessage mensagem = new FacesMessage("Inclusão realizada com sucesso!");
					FacesContext.getCurrentInstance().addMessage(null, mensagem);
					limparCampos();
				} else {
					// alterar
					dao.alterar(venda);
					FacesMessage mensagem = new FacesMessage("Alteração realizada com sucesso!");
					FacesContext.getCurrentInstance().addMessage(null, mensagem);
					limparCampos();
				}
			} else  {
				DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM);

				exibirMensagem("A data de execu��o deve ser maior que a data atual e menor que "+f.format(dataLimite)+"!");
			}
			

		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "cadastroVenda.xhtml";
	}
	

	
	public List<VendaWilliam> getLista() {
		List<VendaWilliam> listaRetorno = new ArrayList<VendaWilliam>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	
	public String editar() {
		return "cadastroVendas.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(venda);
			FacesMessage mensagem = new FacesMessage("Exclusão realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "listaVendas.xhtml";
	}
	
	


}

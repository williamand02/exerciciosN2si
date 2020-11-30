package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.VendaWilliam;
import br.edu.faculdadedelta.util.Conexao;

public class VendaDaoWilliam {
	
	public void incluir(VendaWilliam venda) throws SQLException, ClassNotFoundException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas (desc_cliente, desc_produto, valor_produto, data_venda) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getDesc_cliente().trim());
		ps.setString(2, venda.getDesc_produto().trim());
		ps.setDouble(3, venda.getValor_produto());
		ps.setDate(4, new java.sql.Date(venda.getData_venda().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(VendaWilliam venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE vendas SET "
				+ " desc_cliente = ?, "
				+ " desc_produto = ?, "
				+ " valor_produto  = ?, "
				+ " data_venda  = ? "
				+ " WHERE id_venda = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getDesc_cliente().trim());
		ps.setString(2, venda.getDesc_produto().trim());
		ps.setDouble(3, venda.getValor_produto());
		ps.setDate(4, new java.sql.Date(venda.getData_venda().getTime()));
		ps.setLong(5, venda.getId_venda());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}

	public List<VendaWilliam> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_venda, desc_cliente, desc_produto, "
				+ " valor_produto, data_venda FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<VendaWilliam> listaRetorno = new ArrayList<VendaWilliam>();
 		
		while (rs.next()) {
			VendaWilliam venda = new VendaWilliam();
			venda.setId_venda(rs.getLong("id_venda"));
			venda.setDesc_cliente(rs.getString("desc_cliente").trim());
			venda.setDesc_produto(rs.getString("desc_produto"));
			venda.setValor_produto(rs.getDouble("valor_produto"));
			venda.setData_venda(rs.getDate("data_venda"));
			listaRetorno.add(venda);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return listaRetorno;
	}
	
	public void excluir(VendaWilliam venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, venda.getId_venda());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}

}

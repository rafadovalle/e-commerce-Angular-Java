package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.Produto;

//Data Access Object de Produto. Realiza opera��es de BD com o produto. 
public class DaoProduto {

	// Insere um produto na tabela "produto" do banco de dados
	public static void inserir(Produto produto) throws SQLException, Exception {

		// Monta a string de inser��o de um cliente no BD,
		// utilizando os dados do produtos passados como par�metro
		String sql = "INSERT INTO produto (nome, tamanho, preco, qtdEstoque, categoria) " + " VALUES (?, ?, ?, ?, ?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getTamanho());
			preparedStatement.setDouble(3, produto.getPreco());
			preparedStatement.setInt(4, produto.getQtdEstoque());
			preparedStatement.setString(5, produto.getCategoria());

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} catch (SQLException se) {
			// log the exception

			se.printStackTrace();
			// re-throw the exception
			throw se;

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a atualiza��o dos dados de um produto, com ID e dados
	// fornecidos como par�metro atrav�s de um objeto da classe "Produto"
	public static void atualizar(Produto produto) throws SQLException, Exception {

		// Monta a string de atualiza��o do cliente no BD, utilizando
		// prepared statement
		String sql = "UPDATE produto SET nome=?, tamanho=?, preco=?, qtdEstoque=?, categoria=? " + "WHERE (id=?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"s
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getTamanho());
			preparedStatement.setDouble(3, produto.getPreco());
			preparedStatement.setInt(4, produto.getQtdEstoque());
			preparedStatement.setString(5, produto.getCategoria());
			preparedStatement.setInt(6, produto.getId());

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclus�o l�gica de um produto no BD, com ID fornecido
	// como par�metro. A exclus�o l�gica simplesmente "desliga" o
	// cliente, configurando um atributo espec�fico, a ser ignorado
	// em todas as consultas de cliente ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualiza��o do cliente no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM produto WHERE id=?";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			// preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(1, id);

			// Executa o comando no banco de dados
			preparedStatement.execute();

		} catch (SQLException se) {
			// log the exception

			se.printStackTrace();
			// re-throw the exception
			throw se;

		} finally {

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Lista todos os produtos da tabela produto
	public static List<Produto> listar() throws SQLException, Exception {

		// Monta a string de listagem de produtos no banco, considerando
		// apenas a coluna de ativa��o de produtos
		String sql = "SELECT * FROM produto";

		// Lista de clientes de resultado
		List<Produto> listaProdutos = null;

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenar� os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setBoolean(1, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista n�o foi inicializada, a inicializa
				if (listaProdutos == null) {
					listaProdutos = new ArrayList<Produto>();
				}

				// Cria uma inst�ncia de Produto e popula com os valores do BD
				Produto produto = new Produto();

				produto.setId(result.getInt("id"));
				produto.setNome(result.getString("nome"));
				produto.setTamanho(result.getString("tamanho"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQtdEstoque(result.getInt("qtdEstoque"));
				produto.setCategoria(result.getString("categoria"));

				// Adiciona a inst�ncia na lista
				listaProdutos.add(produto);

			}

		} finally {

			// Se o result ainda estiver aberto, realiza seu fechamento
			if (result != null && !result.isClosed()) {
				result.close();
			}

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de clientes do banco de dados
		return listaProdutos;

	}

	// Procura um cliente no banco de dados, de acordo com o nome
	// ou com o sobrenome, passado como par�metro
	/*
	 * public static List<Cliente> procurar(String valor) throws SQLException,
	 * Exception {
	 * 
	 * // Monta a string de consulta de clientes no banco, utilizando // o valor
	 * passado como par�metro para busca nas colunas de // nome ou sobrenome
	 * (atrav�s do "LIKE" e ignorando min�sculas // ou mai�sculas, atrav�s do
	 * "UPPER" aplicado � coluna e ao // par�metro). Al�m disso, tamb�m considera
	 * apenas os elementos // que possuem a coluna de ativa��o de clientes
	 * configurada com // o valor correto ("enabled" com "true") String sql =
	 * "SELECT * FROM cliente WHERE ((UPPER(nome) LIKE UPPER(?) " +
	 * "OR UPPER(cliente.sobrenome) LIKE UPPER(?)) AND enabled=?)";
	 * 
	 * // Lista de clientes de resultado List<Cliente> listaClientes = null;
	 * 
	 * // Conex�o para abertura e fechamento Connection connection = null; //
	 * Statement para obten��o atrav�s da conex�o, execu��o de // comandos SQL e
	 * fechamentos PreparedStatement preparedStatement = null; // Armazenar� os
	 * resultados do banco de dados ResultSet result = null;
	 * 
	 * try {
	 * 
	 * // Abre uma conex�o com o banco de dados connection =
	 * ConnectionUtils.getConnection();
	 * 
	 * // Cria um statement para execu��o de instru��es SQL preparedStatement =
	 * connection.prepareStatement(sql);
	 * 
	 * // Configura os par�metros do "PreparedStatement"
	 * preparedStatement.setString(1, "%" + valor + "%");
	 * preparedStatement.setString(2, "%" + valor + "%");
	 * preparedStatement.setBoolean(3, true);
	 * 
	 * // Executa a consulta SQL no banco de dados result =
	 * preparedStatement.executeQuery();
	 * 
	 * // Itera por cada item do resultado while (result.next()) {
	 * 
	 * // Se a lista n�o foi inicializada, a inicializa if (listaClientes == null) {
	 * listaClientes = new ArrayList<Cliente>(); }
	 * 
	 * // Cria uma inst�ncia de Cliente e popula com os valores do BD Cliente
	 * cliente = new Cliente(); cliente.setId(result.getInt("cliente_id"));
	 * cliente.setNomeCompleto(result.getString("nome"));
	 * cliente.setCpf(result.getString("cpf"));
	 * 
	 * // Adiciona a inst�ncia na lista listaClientes.add(cliente);
	 * 
	 * }
	 * 
	 * } finally {
	 * 
	 * // Se o result ainda estiver aberto, realiza seu fechamento if (result !=
	 * null && !result.isClosed()) { result.close(); }
	 * 
	 * // Se o statement ainda estiver aberto, realiza seu fechamento if
	 * (preparedStatement != null && !preparedStatement.isClosed()) {
	 * preparedStatement.close(); }
	 * 
	 * // Se a conex�o ainda estiver aberta, realiza seu fechamento if (connection
	 * != null && !connection.isClosed()) { connection.close(); }
	 * 
	 * }
	 * 
	 * // Retorna a lista de clientes do banco de dados return listaClientes;
	 * 
	 * }
	 */

	// Obt�m uma inst�ncia da classe "Cliente" atrav�s de dados do
	// banco de dados, de acordo com o ID fornecido como par�metro
	public static Produto obter(String nome) throws SQLException, Exception {

		// Comp�e uma String de consulta que considera apenas o cliente
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM produto WHERE (nome=?)";

		// Conex�o para abertura e fechamento
		Connection connection = null;
		// Statement para obten��o atrav�s da conex�o, execu��o de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenar� os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conex�o com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execu��o de instru��es SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os par�metros do "PreparedStatement"
			preparedStatement.setString(1, nome);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Verifica se h� pelo menos um resultado
			if (result.next()) {

				// Cria uma inst�ncia de Cliente e popula com os valores do BD
				Produto produto = new Produto();
				produto.setNome(result.getString("nome"));

				// Retorna o resultado
				return produto;

			}

		} finally {

			// Se o result ainda estiver aberto, realiza seu fechamento
			if (result != null && !result.isClosed()) {
				result.close();
			}

			// Se o statement ainda estiver aberto, realiza seu fechamento
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			// Se a conex�o ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Se chegamos aqui, o "return" anterior n�o foi executado porque
		// a pesquisa n�o teve resultados
		// Neste caso, n�o h� um elemento a retornar, ent�o retornamos "null"
		return null;

	}

}
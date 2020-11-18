package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.Produto;

//Data Access Object de Produto. Realiza operações de BD com o produto. 
public class DaoProduto {

	// Insere um produto na tabela "produto" do banco de dados
	public static void inserir(Produto produto) throws SQLException, Exception {

		// Monta a string de inserção de um cliente no BD,
		// utilizando os dados do produtos passados como parâmetro
		String sql = "INSERT INTO produto (nome, tamanho, preco, qtdEstoque, categoria) " + " VALUES (?, ?, ?, ?, ?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a atualização dos dados de um produto, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Produto"
	public static void atualizar(Produto produto) throws SQLException, Exception {

		// Monta a string de atualização do cliente no BD, utilizando
		// prepared statement
		String sql = "UPDATE produto SET nome=?, tamanho=?, preco=?, qtdEstoque=?, categoria=? " + "WHERE (id=?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"s
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Realiza a exclusão lógica de um produto no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// cliente, configurando um atributo específico, a ser ignorado
	// em todas as consultas de cliente ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do cliente no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM produto WHERE id=?";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;

		try {

			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	// Lista todos os produtos da tabela produto
	public static List<Produto> listar() throws SQLException, Exception {

		// Monta a string de listagem de produtos no banco, considerando
		// apenas a coluna de ativação de produtos
		String sql = "SELECT * FROM produto";

		// Lista de clientes de resultado
		List<Produto> listaProdutos = null;

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setBoolean(1, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista não foi inicializada, a inicializa
				if (listaProdutos == null) {
					listaProdutos = new ArrayList<Produto>();
				}

				// Cria uma instância de Produto e popula com os valores do BD
				Produto produto = new Produto();

				produto.setId(result.getInt("id"));
				produto.setNome(result.getString("nome"));
				produto.setTamanho(result.getString("tamanho"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQtdEstoque(result.getInt("qtdEstoque"));
				produto.setCategoria(result.getString("categoria"));

				// Adiciona a instância na lista
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Retorna a lista de clientes do banco de dados
		return listaProdutos;

	}

	// Procura um cliente no banco de dados, de acordo com o nome
	// ou com o sobrenome, passado como parâmetro
	/*
	 * public static List<Cliente> procurar(String valor) throws SQLException,
	 * Exception {
	 * 
	 * // Monta a string de consulta de clientes no banco, utilizando // o valor
	 * passado como parâmetro para busca nas colunas de // nome ou sobrenome
	 * (através do "LIKE" e ignorando minúsculas // ou maiúsculas, através do
	 * "UPPER" aplicado à coluna e ao // parâmetro). Além disso, também considera
	 * apenas os elementos // que possuem a coluna de ativação de clientes
	 * configurada com // o valor correto ("enabled" com "true") String sql =
	 * "SELECT * FROM cliente WHERE ((UPPER(nome) LIKE UPPER(?) " +
	 * "OR UPPER(cliente.sobrenome) LIKE UPPER(?)) AND enabled=?)";
	 * 
	 * // Lista de clientes de resultado List<Cliente> listaClientes = null;
	 * 
	 * // Conexão para abertura e fechamento Connection connection = null; //
	 * Statement para obtenção através da conexão, execução de // comandos SQL e
	 * fechamentos PreparedStatement preparedStatement = null; // Armazenará os
	 * resultados do banco de dados ResultSet result = null;
	 * 
	 * try {
	 * 
	 * // Abre uma conexão com o banco de dados connection =
	 * ConnectionUtils.getConnection();
	 * 
	 * // Cria um statement para execução de instruções SQL preparedStatement =
	 * connection.prepareStatement(sql);
	 * 
	 * // Configura os parâmetros do "PreparedStatement"
	 * preparedStatement.setString(1, "%" + valor + "%");
	 * preparedStatement.setString(2, "%" + valor + "%");
	 * preparedStatement.setBoolean(3, true);
	 * 
	 * // Executa a consulta SQL no banco de dados result =
	 * preparedStatement.executeQuery();
	 * 
	 * // Itera por cada item do resultado while (result.next()) {
	 * 
	 * // Se a lista não foi inicializada, a inicializa if (listaClientes == null) {
	 * listaClientes = new ArrayList<Cliente>(); }
	 * 
	 * // Cria uma instância de Cliente e popula com os valores do BD Cliente
	 * cliente = new Cliente(); cliente.setId(result.getInt("cliente_id"));
	 * cliente.setNomeCompleto(result.getString("nome"));
	 * cliente.setCpf(result.getString("cpf"));
	 * 
	 * // Adiciona a instância na lista listaClientes.add(cliente);
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
	 * // Se a conexão ainda estiver aberta, realiza seu fechamento if (connection
	 * != null && !connection.isClosed()) { connection.close(); }
	 * 
	 * }
	 * 
	 * // Retorna a lista de clientes do banco de dados return listaClientes;
	 * 
	 * }
	 */

	// Obtém uma instância da classe "Cliente" através de dados do
	// banco de dados, de acordo com o ID fornecido como parâmetro
	public static Produto obter(String nome) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o cliente
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM produto WHERE (nome=?)";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setString(1, nome);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Verifica se há pelo menos um resultado
			if (result.next()) {

				// Cria uma instância de Cliente e popula com os valores do BD
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

			// Se a conexão ainda estiver aberta, realiza seu fechamento
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		// Se chegamos aqui, o "return" anterior não foi executado porque
		// a pesquisa não teve resultados
		// Neste caso, não há um elemento a retornar, então retornamos "null"
		return null;

	}

}
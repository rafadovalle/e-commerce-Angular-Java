package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.Cliente;

//Data Access Object de Cliente. Realiza operações de BD com o cliente. 
public class DaoCliente {

	// Insere um cliente na tabela "cliente" do banco de dados
	public static void inserir(Cliente cliente) throws SQLException, Exception {

		// Monta a string de inserção de um cliente no BD,
		// utilizando os dados do clientes passados como parâmetro
		String sql = "INSERT INTO cliente (nomeCompleto, cpf, dataNasc, email, senha, sexo, endereco, complemento, formaPagamento) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
			preparedStatement.setString(1, cliente.getNomeCompleto());
			preparedStatement.setString(2, cliente.getCpf());

			// Converter DataNasc

			preparedStatement.setString(3, cliente.getDataNasc());
			preparedStatement.setString(4, cliente.getEmail());
			preparedStatement.setString(5, cliente.getSenha());
			preparedStatement.setString(6, cliente.getSexo());
			preparedStatement.setString(7, cliente.getEndereco());
			preparedStatement.setString(8, cliente.getComplemento());
			preparedStatement.setString(9, cliente.getFormaPagamento());

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

	// Realiza a atualização dos dados de um cliente, com ID e dados
	// fornecidos como parâmetro através de um objeto da classe "Cliente"
	public static void atualizar(Cliente cliente) throws SQLException, Exception {

		// Monta a string de atualização do cliente no BD, utilizando
		// prepared statement
		String sql = "UPDATE cliente SET nomeCompleto=?, cpf=?, dataNasc=?, email=?, senha =?, sexo=?, endereco=?, complemento=?, formaPagamento=? "
				+ "WHERE (id=?)";

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
			preparedStatement.setString(1, cliente.getNomeCompleto());
			preparedStatement.setString(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getDataNasc());
			preparedStatement.setString(4, cliente.getEmail());
			preparedStatement.setString(5, cliente.getSenha());
			preparedStatement.setString(6, cliente.getSexo());
			preparedStatement.setString(7, cliente.getEndereco());
			preparedStatement.setString(8, cliente.getComplemento());
			preparedStatement.setString(9, cliente.getFormaPagamento());
			preparedStatement.setInt(10, cliente.getId());

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


	
	
	// Realiza a exclusão lógica de um cliente no BD, com ID fornecido
	// como parâmetro. A exclusão lógica simplesmente "desliga" o
	// cliente, configurando um atributo específico, a ser ignorado
	// em todas as consultas de cliente ("enabled").
	public static void excluir(Integer id) throws SQLException, Exception {

		// Monta a string de atualização do cliente no BD, utilizando
		// prepared statement
		String sql = "DELETE FROM cliente WHERE id=?";

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
			//preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(1, id);

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

	// Lista todos os clientes da tabela clientes
	public static List<Cliente> listar() throws SQLException, Exception {

		// Monta a string de listagem de clientes no banco, considerando
		// apenas a coluna de ativação de clientes ("enabled")
		String sql = "SELECT * FROM cliente";

		// Lista de clientes de resultado
		List<Cliente> listaClientes = null;

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
				if (listaClientes == null) {
					listaClientes = new ArrayList<Cliente>();
				}

				// Cria uma instância de Cliente e popula com os valores do BD
				Cliente cliente = new Cliente();

				cliente.setId(result.getInt("id"));
				cliente.setNomeCompleto(result.getString("nomeCompleto"));
				cliente.setCpf(result.getString("cpf"));
				cliente.setDataNasc(result.getString("dataNasc"));
				cliente.setEmail(result.getString("email"));
				cliente.setSenha(result.getString("senha"));
				cliente.setSexo(result.getString("sexo"));
				cliente.setEndereco(result.getString("endereco"));
				cliente.setComplemento(result.getString("complemento"));
				cliente.setFormaPagamento(result.getString("formaPagamento"));

				// Adiciona a instância na lista
				listaClientes.add(cliente);

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
		return listaClientes;

	}

	// Procura um cliente no banco de dados, de acordo com o nome
	// ou com o sobrenome, passado como parâmetro
	public static List<Cliente> procurar(String valor) throws SQLException, Exception {

		// Monta a string de consulta de clientes no banco, utilizando
		// o valor passado como parâmetro para busca nas colunas de
		// nome ou sobrenome (através do "LIKE" e ignorando minúsculas
		// ou maiúsculas, através do "UPPER" aplicado à coluna e ao
		// parâmetro). Além disso, também considera apenas os elementos
		// que possuem a coluna de ativação de clientes configurada com
		// o valor correto ("enabled" com "true")
		String sql = "SELECT * FROM cliente WHERE ((UPPER(nome) LIKE UPPER(?) "
				+ "OR UPPER(cliente.sobrenome) LIKE UPPER(?)) AND enabled=?)";

		// Lista de clientes de resultado
		List<Cliente> listaClientes = null;

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
			preparedStatement.setString(1, "%" + valor + "%");
			preparedStatement.setString(2, "%" + valor + "%");
			preparedStatement.setBoolean(3, true);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();

			// Itera por cada item do resultado
			while (result.next()) {

				// Se a lista não foi inicializada, a inicializa
				if (listaClientes == null) {
					listaClientes = new ArrayList<Cliente>();
				}

				// Cria uma instância de Cliente e popula com os valores do BD
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("cliente_id"));
				cliente.setNomeCompleto(result.getString("nome"));
				cliente.setCpf(result.getString("cpf"));

				// Adiciona a instância na lista
				listaClientes.add(cliente);

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
		return listaClientes;

	}

	
	public static Cliente obterInserir(String cpf) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o cliente
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM cliente WHERE cpf=?";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;
		
		boolean retorno;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setString(1, cpf);

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();
			// Verifica se há pelo menos um resultado
			if (result.next()) {

				// Cria uma instância de Cliente e popula com os valores do BD
				Cliente clienteResult = new Cliente();
				clienteResult.setCpf(result.getString("cpf"));
				
				return clienteResult;

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
	
	
	
	
	// Obtém uma instância da classe "Cliente" através de dados do
	// banco de dados, de acordo com o ID fornecido como parâmetro
	public static Cliente obterAtualizar(Cliente cliente) throws SQLException, Exception {

		// Compõe uma String de consulta que considera apenas o cliente
		// com o ID informado e que esteja ativo ("enabled" com "true")
		String sql = "SELECT * FROM cliente WHERE id=? AND cpf=?";

		// Conexão para abertura e fechamento
		Connection connection = null;
		// Statement para obtenção através da conexão, execução de
		// comandos SQL e fechamentos
		PreparedStatement preparedStatement = null;
		// Armazenará os resultados do banco de dados
		ResultSet result = null;
		
		boolean retorno;

		try {
			// Abre uma conexão com o banco de dados
			connection = ConnectionUtils.getConnection();

			// Cria um statement para execução de instruções SQL
			preparedStatement = connection.prepareStatement(sql);

			// Configura os parâmetros do "PreparedStatement"
			preparedStatement.setInt(1, cliente.getId());
			preparedStatement.setString(2, cliente.getCpf());

			// Executa a consulta SQL no banco de dados
			result = preparedStatement.executeQuery();
			// Verifica se há pelo menos um resultado
			if (result.next()) {

				// Cria uma instância de Cliente e popula com os valores do BD
				Cliente clienteResult = new Cliente();
				clienteResult.setId(result.getInt("id"));
				clienteResult.setCpf(result.getString("cpf"));
				
				return clienteResult;

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
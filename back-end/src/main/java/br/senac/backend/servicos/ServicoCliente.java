package br.senac.backend.servicos;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoCliente;
import br.senac.backend.model.Cliente;

@Path("/cliente")
public class ServicoCliente {

	// private static List<Cliente> listaClientes = new ArrayList<Cliente>();
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Response inserirCliente(Cliente cliente) {

		Response response = null;

		try {

			if (!cliente.getNomeCompleto().isEmpty() && !cliente.getCpf().isEmpty() && !cliente.getDataNasc().isEmpty()
					&& !cliente.getEmail().isEmpty() && !cliente.getSenha().isEmpty() && !cliente.getSexo().isEmpty()
					&& !cliente.getEndereco().isEmpty() && !cliente.getFormaPagamento().isEmpty()) {

				if (cliente.getNomeCompleto().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O número máximo de caracteres para Nome é 50").build();
				} else if (cliente.getCpf().length() < 11 || cliente.getCpf().length() > 11) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O CPF deve conter 11 digitos").build();
				} else if (cliente.getDataNasc().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha uma data válida")
							.build();
				} else if (cliente.getEmail().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um e-mail válido")
							.build();
				} else if (cliente.getSenha().length() > 20) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha uma senha válida")
							.build();
				} else if (cliente.getSexo().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um sexo válido")
							.build();
				} else if (cliente.getEndereco().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um endereço válido")
							.build();
				} else if (cliente.getFormaPagamento().length() > 15) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("Preencha uma forma de pagamento válida").build();
				} else {

					Cliente teste = DaoCliente.obterInserir(cliente.getCpf());

					if (teste != null) {
						response = Response.status(Response.Status.OK.getStatusCode()).entity("Problemas com Id ou CPF")
								.build();
					} else {
						DaoCliente.inserir(cliente);
						response = Response.status(Response.Status.OK.getStatusCode()).entity("SUCESSO").build();
					}
					
					return response;
				}
			} else {
				response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
						.entity("Preencha todos os campos para prosseguir.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listarClientes() {

		try {
			return DaoCliente.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarCliente(Cliente cliente) {

		Response response = null;

		try {

			if (!cliente.getNomeCompleto().isEmpty() && !cliente.getCpf().isEmpty() && !cliente.getDataNasc().isEmpty()
					&& !cliente.getEmail().isEmpty() && !cliente.getSenha().isEmpty() && !cliente.getSexo().isEmpty()
					&& !cliente.getEndereco().isEmpty() && !cliente.getFormaPagamento().isEmpty()) {

				if (cliente.getNomeCompleto().length() > 49) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O número máximo de caracteres para Nome é 50").build();
				} else if (cliente.getCpf().length() < 11 || cliente.getCpf().length() > 11) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O CPF deve conter 11 digitos").build();
				} else if (cliente.getDataNasc().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha uma data válida")
							.build();
				} else if (cliente.getEmail().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um e-mail válido")
							.build();
				} else if (cliente.getSenha().length() > 20) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha uma senha válida")
							.build();
				} else if (cliente.getSexo().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um sexo válido")
							.build();
				} else if (cliente.getEndereco().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preencha um endereço válido")
							.build();
				} else if (cliente.getFormaPagamento().length() > 15) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("Preencha uma forma de pagamento válida").build();
				} else {

					//Cliente teste = DaoCliente.obterAtualizar(cliente);

					//if (teste != null) {
						//response = Response.status(Response.Status.OK.getStatusCode()).entity("CPF já cadastrado")
								//.build();
					//} else {
						DaoCliente.atualizar(cliente);
						response = Response.status(Response.Status.OK.getStatusCode()).entity("SUCESSO").build();
					//}
					return response;
				}
			} else {
				response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
						.entity("Preencha todos os campos para prosseguir.").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removerCliente(@PathParam("id") Integer id) {

		Response response = null;

		try {
			DaoCliente.excluir(id);
			response = Response.status(Response.Status.OK.getStatusCode()).entity("Deletado com sucesso").build();
		} catch (Exception e) {
			response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
					.entity("Cliente não encontrado").build();
			e.printStackTrace();
		}

		return response;

	}
}

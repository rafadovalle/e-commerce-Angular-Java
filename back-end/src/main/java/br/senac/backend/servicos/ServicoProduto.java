package br.senac.backend.servicos;

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

import br.senac.backend.db.dao.DaoProduto;
import br.senac.backend.model.Produto;

@Path("/produto")
public class ServicoProduto {

	// private static List<Produto> listaProdutos = new ArrayList<Produto>();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirProduto(Produto produto) {

		Response response = null;

		try {

			if (!produto.getNome().isEmpty() && !produto.getTamanho().isEmpty()
					&& !produto.getPreco().toString().isEmpty() && !produto.getQtdEstoque().toString().isEmpty()
					&& !produto.getCategoria().isEmpty()) {

				if (produto.getNome().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O número máximo de caracteres para Nome é 50").build();
				} else if (produto.getTamanho().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Tamanho inválido").build();
				} else if (produto.getPreco() < 0) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preço inválido").build();
				} else if (produto.getQtdEstoque() < 0) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Quantidade inválida")
							.build();
				} else if (produto.getCategoria().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Categoria inválida").build();
				} else {
					DaoProduto.inserir(produto);
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("Produto cadastrado com sucesso").build();
				}
			} else {
				response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
						.entity("Preencha todos os campos para prosseguir").build();
			}

		} catch (Exception e) {
			response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
					.entity("Produto já cadastrado").build();
			e.printStackTrace();
		}

		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProdutos() {

		try {
			return DaoProduto.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarProduto(Produto produto) {
		Response response = null;

		try {

			if (!produto.getNome().isEmpty() && !produto.getTamanho().isEmpty()
					&& !produto.getPreco().toString().isEmpty() && !produto.getQtdEstoque().toString().isEmpty()
					&& !produto.getCategoria().isEmpty()) {

				if (produto.getNome().length() > 50) {
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("O número máximo de caracteres para Nome é 50").build();
				}else if (produto.getTamanho().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Tamanho inválido").build();
				} else if (produto.getPreco() < 0) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Preço inválido").build();
				} else if (produto.getQtdEstoque() < 0) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Quantidade inválida")
							.build();
				} else if (produto.getCategoria().length() > 10) {
					response = Response.status(Response.Status.OK.getStatusCode()).entity("Categoria inválida").build();
				} else {

					DaoProduto.atualizar(produto);
					response = Response.status(Response.Status.OK.getStatusCode())
							.entity("Produto alterado com sucesso").build();
				}

			} else {
				response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
						.entity("Preencha todos os campos para prosseguir").build();
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
	public Response removerProduto(@PathParam("id") Integer id) {

		Response response = null;

		try {
			DaoProduto.excluir(id);
			response = Response.status(Response.Status.OK.getStatusCode()).entity("Deletado com sucesso").build();
		} catch (Exception e) {
			response = Response.status(Response.Status.EXPECTATION_FAILED.getStatusCode())
					.entity("Produto não encontrado").build();
			e.printStackTrace();
		}

		return response;

	}
}

import { Component, OnInit } from '@angular/core';
import { ProdutoService } from '../service/product.service';
import { Produto } from '../model/product';
import { ClienteService } from '../service/client.service';
import { Cliente } from '../model/client';
import { FormControl } from '@angular/forms';
import { VendaService } from './venda.service';
import { Venda } from '../model/venda';

@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {

  constructor(
    private produtoService: ProdutoService, private clienteService: ClienteService, private vendaService: VendaService
  ) {
    this.listarProdutos();
    this.listarClientes();
  }

  clienteControl = new FormControl();


  clientes: Array<Cliente>;
  cliSel = null;
  
  produtos: Array<Produto>;
  listaProdutos: Array<Produto>;
  proSel = null;
  vendaSel = null;
  inserir = false;
  enabled: boolean = false;

  ngOnInit() {
    this.listarProdutos();
  }

  onSelect(pro: Produto): void {
    this.proSel = pro;
    this.inserir = false;
  }

  onSelectClient(cli: Cliente): void {
    this.enabled = true;
    this.cliSel = cli;
  }

  onSelectProduct(pro: Produto): void {
    this.enabled = true;
    this.proSel = pro;
  }

  listarProdutos(): void {
    this.produtoService.listarProdutos()
      .subscribe(produtos => {
        this.produtos = produtos;
        this.proSel = null;
        this.inserir = false;

      });
  }


  listarClientes(): void {
    this.clienteService.listarClientes()
      .subscribe(clientes => {
        this.clientes = clientes;
        this.cliSel = null;
        this.inserir = false;
        this.enabled = false;
      });
  }

  novaVenda(): void {
    this.vendaSel = new Venda();
    this.inserir = true;
  }

  salvarVenda(): void {
      debugger;
      this.vendaSel = new Venda();
      this.vendaSel.idCliente = this.cliSel.id;
      this.vendaSel.idProduto = this.proSel.id;
      this.vendaSel.qtdProduto = this.proSel.qtdEstoque;
      this.vendaSel.totalVenda = this.vendaSel.qtdProduto * this.proSel.preco;
      this.vendaSel.dataVenda = '06/12/2019';
      this.vendaService.inserirVenda(this.vendaSel)
        .subscribe(() => {
          // this.listarProdutos();
        });
      if(this.vendaSel.idCliente && this.vendaSel.idProduto != null) {
        alert('Venda realizada');
      } else {
        alert('Faça a seleção');
      }
  }
  
  armazenarProduto(produto: Produto) {
    this.listaProdutos.push(produto);
  }



}

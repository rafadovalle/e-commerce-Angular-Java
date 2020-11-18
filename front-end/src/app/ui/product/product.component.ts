import { Component, OnInit } from '@angular/core';
import { ProdutoService } from '../../service/product.service';
import { Produto } from 'src/app/model/product';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(
    private produtoService: ProdutoService
  ) {
    this.listarProdutos();
  }

  public paginaAtual = 1;

  produtos: Array<Produto>;
  proSel = null;
  inserir = false;

  nomeControl = new FormControl();
  tamanhoControl = new FormControl();
  tamanhos: any[] = [
    { name: '250g', valor: 1 },
    { name: '500g', valor: 2 },
    { name: '750g', valor: 3 }
  ];

  precoControl = new FormControl();

  qtdEstoqueControl = new FormControl();
  categoriaControl = new FormControl();



  



  ngOnInit() {
    this.listarProdutos();
  }



  onSelect(pro: Produto): void {
    this.proSel = pro;
    this.inserir = false;
  }

  novoProduto(): void {
    this.proSel = new Produto();
    this.inserir = true;
  }

  salvarProduto(): void {
    if (this.inserir) {
      this.produtoService.inserirProduto(this.proSel)
        .subscribe(() => {
          this.listarProdutos();
        });
      alert('Produto inserido');
      location.reload();
      this.inserir = false;
    } else {
      this.produtoService.atualizarProduto(this.proSel)
        .subscribe(() => {
          this.listarProdutos();
        });
      alert('Produto atualizado');
      location.reload();
    }
  }

  removerProduto(Produto: Produto): void {
    this.inserir = false;
    //const id = Produto.id;
    debugger;
    this.produtoService.removerProduto(Produto)
      .subscribe(() => {
        
        this.listarProdutos();
      });
      alert('Produto removido');
      location.reload();
  }

  listarProdutos(): void {
    this.produtoService.listarProdutos()
      .subscribe(produtos => {
        this.produtos = produtos;
        this.proSel = null;
        this.inserir = false;

      });



  }




  cancelar(): void {
    this.proSel = null;
    this.inserir = false;
  }

}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Produto } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(
    private http: HttpClient
  ) {

  }

  listarProdutos(): Observable<Produto[]> {
    return this.http.get<Produto[]>('http://localhost:8080'
      + '/base-back-end/servicos/produto');
  }

  inserirProduto(produto: Produto): Observable<any> {
    return this.http.post('http://localhost:8080'
      + '/base-back-end/servicos/produto', produto);
  }

  atualizarProduto(produto: Produto): Observable<any> {
    return this.http.put('http://localhost:8080'
      + '/base-back-end/servicos/produto', produto);
  }

  removerProduto(produto: Produto): Observable<any> {
    return this.http.delete('http://localhost:8080'
      + '/base-back-end/servicos/produto/' + produto);
  }


}

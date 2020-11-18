import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venda } from '../model/venda';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  constructor(
    private http: HttpClient
  ) {

  }


  inserirVenda(venda: Venda): Observable<any> {
    return this.http.post('http://localhost:8080'
      + '/base-back-end/servicos/venda', venda);
  }

  listarVendas(): Observable<Venda[]> {
    return this.http.get<Venda[]>('http://localhost:8080'
      + '/base-back-end/servicos/venda');
  }

}

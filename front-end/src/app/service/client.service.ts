import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '../model/client';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  constructor(
    private http: HttpClient
  ) {

  }

  listarClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>('http://localhost:8080'
      + '/base-back-end/servicos/cliente');
  }

  inserirCliente(cliente: Cliente): Observable<any> {
    return this.http.post('http://localhost:8080'
      + '/base-back-end/servicos/cliente', cliente);
  }

  atualizarCliente(cliente: Cliente): Observable<any> {
    return this.http.put('http://localhost:8080'
      + '/base-back-end/servicos/cliente', cliente);
  }

  removerCliente(cliente: Cliente): Observable<any> {
    return this.http.delete('http://localhost:8080'
      + '/base-back-end/servicos/cliente/' + cliente);
  }

  

}


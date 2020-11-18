import { Component, OnInit, ViewChild } from '@angular/core';
import { Cliente } from '../../model/client';
import { ClienteService } from '../../service/client.service';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { FormControl, Validators, MinLengthValidator } from '@angular/forms';
import { ResourceLoader } from '@angular/compiler';



export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079 },
  { position: 2, name: 'Helium', weight: 4.0026 },
  { position: 3, name: 'Lithium', weight: 6.941 },
  { position: 4, name: 'Beryllium', weight: 9.0122 },
];

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  constructor(
    private clienteService: ClienteService
  ) {
    this.listarClientes();
    this.sexoControl = new FormControl();
    this.formaPagamentoControl = new FormControl();
  }

  public paginaAtual = 1;

  clientes: Array<Cliente>;
  cliSel = null;
  inserir = false;

  nomeCompletoControl = new FormControl();
  cpfControl = new FormControl();
  senhaControl = new FormControl();
  enderecoControl = new FormControl();

  sexoControl = new FormControl('', [Validators.required]);
  generos: any[] = [
    { name: 'Masculino', valor: 1 },
    { name: 'Feminino', valor: 2 },
    { name: 'Outros', valor: 3 }
  ];

  datemask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  formaPagamentoControl = new FormControl('', [Validators.required]);
  pagamentos: any[] = [
    { name: 'Crédito', valor: 1 },
    { name: 'Débito', valor: 2 },
    { name: 'Boleto', valor: 3 }
  ];

  dataNascControl = new FormControl();

  emailControl = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessageEmail() {
    return this.emailControl.hasError('required') ? 'É necessário informar um e-mail' :
      this.emailControl.hasError('email') ? 'Email inválido' :
        '';
  }

  ngOnInit() {
    this.listarClientes();
  }

  onSelect(cli: Cliente): void {
    this.cliSel = cli;
    this.inserir = false;
  }

  novoCliente(): void {
    this.cliSel = new Cliente();
    this.inserir = true;
  }

  salvarCliente(): void {
    if (this.inserir) {
      this.cliSel.sexo = this.sexoControl.value;
      this.cliSel.formaPagamento = this.formaPagamentoControl.value;
      this.clienteService.inserirCliente(this.cliSel)
        .subscribe(() => {
          this.listarClientes();
        });
      if (this.cliSel.formaPagamento != null) {
        alert('Cliente inserido');
        location.reload();
        this.inserir = false;
      } else {
        alert('Preencha os campos');
      }
    } else {
      this.clienteService.atualizarCliente(this.cliSel)
        .subscribe(() => {
          this.listarClientes();
        });
      alert('Cliente atualizado');
      location.reload();
    }
  }

  removerCliente(cliente: Cliente): void {
    this.inserir = false;
    debugger;
    this.clienteService.removerCliente(cliente)
      .subscribe(() => {
        location.reload();
        this.listarClientes();
      });
    alert('Cliente removido');
    location.reload();
  }

  listarClientes(): void {
    this.clienteService.listarClientes()
      .subscribe(clientes => {
        this.clientes = clientes;
        this.cliSel = null;
        this.inserir = false;
      });
  }

  cancelar(): void {
    this.cliSel = null;
    this.inserir = false;
  }
}

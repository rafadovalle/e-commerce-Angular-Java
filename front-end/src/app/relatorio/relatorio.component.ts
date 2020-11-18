import { Component, OnInit } from '@angular/core';
import { VendaService } from '../venda/venda.service';
import { Venda } from '../model/venda';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {

  constructor(
    private vendaService: VendaService
  ) {
    this.listarVendas();
  }

  vendas: Array<Venda>;

  ngOnInit() {
    this.listarVendas();
  }


  listarVendas(): void {
    this.vendaService.listarVendas()
      .subscribe(vendas => {
        this.vendas = vendas;
      });
    }
}


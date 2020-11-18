import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientComponent } from '../ui/client/client.component';
import { ProductComponent } from '../ui/product/product.component';
import { RelatorioComponent } from '../relatorio/relatorio.component';
import { VendaComponent } from '../venda/venda.component';



const routes: Routes = [ 
  { path: 'client', component: ClientComponent },
  { path: 'product', component: ProductComponent },
  { path: 'venda', component: VendaComponent },
  { path: 'relatorio', component: RelatorioComponent } ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

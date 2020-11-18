import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GaleriaComponent } from '../galeria/galeria.component';
import { HomeComponent } from '../home/home.component';
import { GrupoComponent } from '../grupo/grupo.component';



const routes: Routes = [ 
  { path: 'galeria', component: GaleriaComponent },
  { path: 'home', component: HomeComponent },
  { path: 'grupo', component: GrupoComponent } ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class HeaderRoutingModule { }

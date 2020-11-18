import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientComponent } from '../ui/client/client.component';
import { ProductComponent } from '../ui/product/product.component';
import { HomeComponent } from '../home/home.component';
import { HighPermGuard } from '../guards/high-perm.guard';



const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [HighPermGuard] }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }

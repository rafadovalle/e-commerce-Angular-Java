import { Component, OnInit } from '@angular/core';
import { AuthService } from '../login/auth.service';
import { LocalStorageService } from '../service/local-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  mostrarMenu: boolean = false;

  constructor(private authService: AuthService, private localStorageService: LocalStorageService) {

  }

  ngOnInit() {
    this.authService.mostrarMenuEmitter.subscribe(
      mostrar => this.mostrarMenu = mostrar);
  }

  logout() {
    this.localStorageService.deleteUserLogged();
  }
}

import { Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Router } from '@angular/router';

import { EventEmitter } from '@angular/core';

// Services
import { LocalStorageService } from '../service/local-storage.service';

@Injectable()
export class AuthService {

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private localStorageService: LocalStorageService) { }

  fazerLogin(usuario: Usuario) {
    if (usuario.nome === 'usuario@email.com' && usuario.senha === '123456') {
      this.localStorageService.setUserLogged();
      this.router.navigate(['/home']);
    } else {
      this.localStorageService.deleteUserLogged();
      this.mostrarMenuEmitter.emit(false);
      alert('User admin incorreto');
    }
  }

  logout() {
    this.localStorageService.deleteUserLogged();
  }

}

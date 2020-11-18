import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  setUserLogged() {
    localStorage.setItem('logged', 'true');
  }

  getUserLogged(): string {
    return localStorage.getItem('logged');
  }

  deleteUserLogged() {
    localStorage.removeItem('logged');
  }

}

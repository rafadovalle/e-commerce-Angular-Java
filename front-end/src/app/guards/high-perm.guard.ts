import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { LocalStorageService } from '../service/local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class HighPermGuard implements CanActivate {

  constructor(private localStorageService: LocalStorageService) {

  }

  canActivate() {
    return this.localStorageService.getUserLogged() === 'true' ? true : false;
  }
}

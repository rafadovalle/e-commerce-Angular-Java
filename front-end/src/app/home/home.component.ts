import { Component } from '@angular/core';

// Services
import { LocalStorageService } from '../service/local-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  ngOnInit() {

  }

  constructor(private localStorageService: LocalStorageService) {

  }

  logout() {
    this.localStorageService.deleteUserLogged();
  }

}

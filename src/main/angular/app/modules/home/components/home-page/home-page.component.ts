import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Notiflix from "notiflix-angular";
import { AuthenService } from '@core/service/authen/authen.service';
import { Profile } from '@modules/home/models/profile';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  profile: Profile;

  constructor(
    private router: Router,
    private authenService: AuthenService
  ) { }

  ngOnInit() {
    Notiflix.Loading.Init({ svgSize: "200px", svgColor: "#ffffff" });
    Notiflix.Loading.Hourglass();
    this.authenService.getUserMe().subscribe((res: Profile) => {
      Notiflix.Loading.Remove();
      this.profile = res;
      console.log('profile:', this.profile)
    })
  }

  logout() {
    sessionStorage.clear();
    Notiflix.Loading.Init({ svgSize: "200px", svgColor: "#ffffff" });
    Notiflix.Loading.Hourglass();
    setTimeout(() => {
      Notiflix.Loading.Remove();
      this.router.navigate(['login']);
    }, 3000);
  }

}

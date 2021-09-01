import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  usuarioAutenticado: boolean = false;
  constructor(
    private router: Router,
    ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ) : boolean {
    if(this.usuarioAutenticado == true){
    // this.router.navigate['/salas']
      console.log("True", this.usuarioAutenticado)
      return true
    }
    console.log("Aquii", this.usuarioAutenticado)
    this.router.navigate['main']
    return false;
  }
}

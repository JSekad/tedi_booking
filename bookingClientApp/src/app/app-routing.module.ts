import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "./components/main/main.component";
import {TestComponent} from "./components/test/test.component";


const routes: Routes = [
  { path: 'test', component: TestComponent},
  // { path: 'signup', component: SignUpComponent, canActivate: [IsNotAuthGuard]},
  // { path: 'map', component: MapComponent},
  // { path: 'pois', component: PoisComponent, canActivate: [IsAuthGuard, RolesGuard], data:{roles: ['POI_OWNER']}},
  // { path: 'profile', component: ProfileComponent, canActivate: [IsAuthGuard]},
  // { path: 'accounts', component: AccountsComponent, canActivate: [IsAuthGuard, RolesGuard], data:{roles: ['ADMIN']}},
  // { path: 'reservations', component: ReservationsComponent, canActivate: [IsAuthGuard, RolesGuard], data:{roles: ['POI_OWNER']}},
  // { path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}

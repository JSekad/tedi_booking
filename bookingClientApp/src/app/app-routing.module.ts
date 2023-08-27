import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "./components/main/main.component";
import {TestComponent} from "./components/test/test.component";
import {SearchComponent} from "./components/search/search.component";
import {ReservationComponent} from "./components/reservation/reservation.component";
import { AdminComponent } from './components/admin/admin.component';


const routes: Routes = [
  { path: 'test', component: TestComponent},
  { path: 'search', component: SearchComponent},
  { path: 'reservation', component: ReservationComponent},
  { path: 'admin', component: AdminComponent}
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

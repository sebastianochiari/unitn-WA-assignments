import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MemberComponent } from './components/member/member.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ParliamentComponent } from './components/parliament/parliament.component';

const routes: Routes = [
  { path: 'parliament', component: ParliamentComponent},
  { path: 'member', component: MemberComponent},
  { path: '', redirectTo: 'parliament', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

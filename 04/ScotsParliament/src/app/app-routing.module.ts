import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ParliamentComponent } from './components/parliament/parliament.component';
import { MemberComponent } from './components/member/member.component';

const routes: Routes = [
  { path: 'parliament', component: ParliamentComponent },
  { path: 'member', component: MemberComponent},
  { path: '', redirectTo: 'parliament', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

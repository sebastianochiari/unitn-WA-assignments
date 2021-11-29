import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { ParliamentComponent } from './components/parliament/parliament.component';
import { MemberCardComponent } from './components/member-card/member-card.component';
import { MemberComponent } from './components/member/member.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

import { NameFormatterPipe } from './pipes/name-formatter/name-formatter.pipe';
import { PhotoFormatterPipe } from './pipes/photo-formatter/photo-formatter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ParliamentComponent,
    MemberCardComponent,
    MemberComponent,
    PageNotFoundComponent,
    NameFormatterPipe,
    PhotoFormatterPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

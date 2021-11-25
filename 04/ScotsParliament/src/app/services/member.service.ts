import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Member } from '../Member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private baseURL = 'https://data.parliament.scot/api/';

  constructor(private http:HttpClient) { }

  getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(this.baseURL + 'members');
  }

  getMemberGivenID(id: number): Observable<Member> {
    return this.http.get<Member>(this.baseURL + '/members/' + id);
  }

  getMemberParties(): Observable<any> {
    return this.http.get(this.baseURL + 'memberparties');
  }

  getPartyGivenID(id: number): Observable<any> {
    return this.http.get(this.baseURL + '/parties/' + id);
  }

  getWebsites(): Observable<any> {
    return this.http.get(this.baseURL + '/websites');
  }
}

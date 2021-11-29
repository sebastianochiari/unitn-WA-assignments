import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Member } from '../models/member.model';
import { MemberParty } from '../models/memberparty.model';
import { Party } from '../models/party.model';
import { Website } from '../models/website.model';

import { Observable, throwError } from 'rxjs';
import { catchError, map, switchMap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private readonly baseURL = 'https://data.parliament.scot/api/';

  constructor(private http:HttpClient) { }

  // DIRECT API CALLS

  getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(this.baseURL + 'members');
  }

  getMemberGivenID(id: number): Observable<Member> {
    return this.http.get<Member>(this.baseURL + 'members/' + id)
      .pipe(
        catchError(this.handleError)
      );
  }

  getMemberParties(): Observable<MemberParty[]> {
    return this.http.get<MemberParty[]>(this.baseURL + 'memberparties');
  }

  getPartyByID(id: number): Observable<Party> {
    return this.http.get<Party>(this.baseURL + 'parties/' + id);
  }

  getWebsites(): Observable<Website[]> {
    return this.http.get<Website[]>(this.baseURL + 'websites');
  }

  // COMPLEX API CALLS

  getPartyGivenMember(id: number): Observable<Party> {  
    return this.getMemberParties().pipe(
      map(memberParties => memberParties.filter(memberParty => memberParty.PersonID == id)),
      switchMap((memberParties) => {
        return this.getPartyByID(memberParties[0].PartyID);
      })
    );
  }

  getWebsitesGivenMember(id: number): Observable<Website[]> {
    return this.getWebsites().pipe(
      map(websites => websites.filter(website => website.PersonID == id))
    );
  }

  // ERROR HANDLING
  
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError( () => new Error('Something bad happened; please try again later.'));
  }
}

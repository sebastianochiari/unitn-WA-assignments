import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

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
}

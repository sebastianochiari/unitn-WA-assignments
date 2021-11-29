import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Member } from 'src/app/models/member.model';
import { Party } from 'src/app/models/party.model';
import { Website } from 'src/app/models/website.model';

import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {


  member!: Member;
  party!: Party;
  websites!: Website[];

  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      if(id === undefined) {
        this.router.navigate(['/error-page']);
      } else {
        this.getMemberById(id);
        this.getPartyByMember(id);
        this.getWebsitesByMember(id);
      }
    });
  }

  getMemberById(id: number): void {
    this.memberService.getMemberGivenID(id).subscribe({
      next: (member) => {
        this.member = member;
      }, 
      error: (error) => {
        this.router.navigate(['/error-page']);
      }
    });
  }

  getPartyByMember(id: number): void {
    this.memberService.getPartyGivenMember(id).subscribe((party) => {
      this.party = party;
    });
  }

  getWebsitesByMember(id: number): void {
    this.memberService.getWebsitesGivenMember(id).subscribe((websites) => {
      this.websites = websites;
    })
  }
}

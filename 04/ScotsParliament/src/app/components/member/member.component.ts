import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { Member } from 'src/app/Member';

import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  member: Member | undefined;
  party: String;
  websites: String[];

  months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
  ]

  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.party = '';
    this.websites = [];
  }

  ngOnInit(): void {
    // retrieve parameter
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      console.log("ID: " + id);
      if(id === undefined) {
        // add redirect to error page
        this.router.navigate(['/error404']);
      } else {
        // request member with a given ID
        this.getMemberById(id);
        this.getMemberPartyByMemberId(id);
        this.getWebsitesById(id);
      }
    });
  }

  getMemberById(id: number): void {
    this.memberService.getMemberGivenID(id).subscribe({
      next: (member) => {
        if(member === undefined) {
          this.router.navigate(['/error404']);
        }
        
        // name refactor
        let name = member.ParliamentaryName;
        let array = name.split(', ');
        name = array[1] + ' ' + array[0];
        member.ParliamentaryName = name;
  
        // date refactor
        if(member.BirthDateIsProtected === false) {
          let date = new Date(member.BirthDate);
          member.BirthDate = this.months[date.getMonth()] + ' ' + date.getDate() + ', ' + date.getFullYear(); 
        }
  
        // photo refactor
        if(member.PhotoURL === "") {
          if(member.GenderTypeID === 1) {
            member.PhotoURL = "assets/placeholder-gender1.jpg";
          } else if (member.GenderTypeID === 2) {
            member.PhotoURL = "assets/placeholder-gender2.jpg";
          }
        }
  
        this.member = member;
      },
      error: (error) => {
        console.log('Something went wrong with getMemberById()');
        this.router.navigate(['/error404']);
      }
    });
  }

  getMemberPartyByMemberId(id: number): void {
    // retrieve all member parties
    this.memberService.getMemberParties().subscribe({
      next: (members) => {
        let entry;
        for (let member of members) {
          if(member.PersonID == id) {
            entry = member;
            break;
          }
        }
        this.memberService.getPartyGivenID(entry.PartyID).subscribe((party) => {
          this.party = party.ActualName;
        })
      },
      error: (error) => {
        console.log('Something went wrong with getMemberPartyByMemberId()');
      }
    });
  }

  getWebsitesById(id: number): void {
    this.memberService.getWebsites().subscribe({
      next: (websites) => {
        for (let website of websites) {
          if(website.PersonID == id) {
            (this.websites).push(website.WebURL);
          }
        }
      },
      error: (error) => {
        console.log('Something went wrong with getWebsitesById()');
      }
    });
  }
}
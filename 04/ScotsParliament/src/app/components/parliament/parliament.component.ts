import { Component, OnInit } from '@angular/core';

import { Member } from 'src/app/models/member.model';

import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-parliament',
  templateUrl: './parliament.component.html',
  styleUrls: ['./parliament.component.css']
})
export class ParliamentComponent implements OnInit {

  members?: Member[];

  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    this.memberService.getMembers().subscribe((members) => {
      members.sort(function(a,b) {
        return ((a.ParliamentaryName > b.ParliamentaryName) ? 1 : -1);
      });
      this.members = members;
    });
  }

}

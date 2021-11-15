import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { MemberService } from 'src/app/services/member.service';

import { Member } from 'src/app/Member';

@Component({
  selector: 'app-parliament',
  templateUrl: './parliament.component.html',
  styleUrls: ['./parliament.component.css']
})

export class ParliamentComponent implements OnInit {

  members: ParliamentMember[] = [];

  constructor(private memberService: MemberService) {
  }

  ngOnInit(): void {
    this.getMembers();
  }

  getMembers(): void {
    this.memberService.getMembers().subscribe((members) => {
      members.sort(function(a,b) {
        return ((a.ParliamentaryName > b.ParliamentaryName) ? 1 : -1);
      });
      this.members = [];
      for (let entry of members) {
        let id = entry.PersonID;

        let name = entry.ParliamentaryName;
        let array = name.split(', ');
        name = array[1] + ' ' + array[0];

        let gender = entry.GenderTypeID;

        // fix if not existing photoURL
        let photoURL = entry.PhotoURL;
        if(photoURL === "") {
          if(gender === 1) {
            photoURL = "assets/placeholder-gender1.jpg";
          } else if(gender === 2) {
            photoURL = "assets/placeholder-gender2.jpg";
          }
        }

        let member = new ParliamentMember(id, name, photoURL);
        this.members.push(member);
      }
    });
  }
}

class ParliamentMember {
  id: number;
  name: string;
  photoURL: string;

  constructor(id:number, name: string, photoURL: string) {
    this.id = id;
    this.name = name;
    this.photoURL = photoURL;
  }
}
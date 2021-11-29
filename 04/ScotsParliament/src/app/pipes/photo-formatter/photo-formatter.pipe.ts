import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'photoFormatter'
})
export class PhotoFormatterPipe implements PipeTransform {

  transform(value: string, gender: number) {
    if(value === "") {
      let photoURL;
      if(gender === 1) {
        photoURL = "assets/placeholder-gender1.jpg";
      } else if(gender === 2) {
        photoURL = "assets/placeholder-gender2.jpg";
      }
      return photoURL;
    } else {
      return value;
    }
  }

}

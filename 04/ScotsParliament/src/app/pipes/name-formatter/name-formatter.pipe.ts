import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'nameFormatter'
})
export class NameFormatterPipe implements PipeTransform {

  transform(value: string) {
    let array = value.split(', ');
    return array[1] + ' ' + array[0];
  }

}

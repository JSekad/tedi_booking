import { Component, EventEmitter, Input, Output } from '@angular/core';
import { proj } from 'openlayers';

@Component({
  selector: 'openstreetmap',
  templateUrl: './openstreetmap.component.html',
  styleUrls: ['./openstreetmap.component.css']
})
export class OpenstreetmapComponent {

  @Input()
  zoom = 14;
  @Input()
  opacity = 1;

  @Input()
  latitude = 37.9838000;
  @Input()
  longitude = 23.727500;

  @Input()
  pointerLatitude: number | null = null;
  @Input()
  pointerLongitude: number | null = null;

  @Input()
  pointerSelection = false;

  @Input()
  width: string = "40%";
  @Input()
  height: string = "40%";

  @Output()
  coordinatesSelected = new EventEmitter<number[]>;

  image = "https://static.vecteezy.com/system/resources/previews/010/160/458/original/pin-location-icon-sign-symbol-design-free-png.png";

  setPointerLocation(event: any){
    if(!this.pointerSelection)
      return;

    const coordinates = proj.transform(event.coordinate, 'EPSG:3857', 'EPSG:4326');
    this.pointerLatitude = coordinates[1];
    this.pointerLongitude = coordinates[0];
    this.coordinatesSelected.emit([this.pointerLatitude, this.pointerLongitude]);
  }
}

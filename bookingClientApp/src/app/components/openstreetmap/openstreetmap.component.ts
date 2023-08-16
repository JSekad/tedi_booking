import { Component, Input } from '@angular/core';
// import { proj, View } from 'openlayers';

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
  showControlsZoom = true; 
  @Input()
  titleZoomIn = 'Zoom in';
  @Input()
  titleZoomOut = 'Zoom out';
  // @Input()
  // showControlsCurrentLocation: boolean
  @Input()
  titleCurrentLocation = 'Current location';

  image = "https://static.vecteezy.com/system/resources/previews/010/160/458/original/pin-location-icon-sign-symbol-design-free-png.png";

  increaseZoom() {
    this.zoom++;
  }

  decreaseZoom() {
    this.zoom--;
  }
}
